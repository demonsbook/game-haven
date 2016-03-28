package com.demonsbook.ddd.game.haven.acceptance;

import com.demonsbook.ddd.game.haven.application.services.PurchaseService;
import com.demonsbook.ddd.game.haven.config.GameHavenConfig;
import com.demonsbook.ddd.game.haven.domain.entity.DeliveryMethod;
import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.PaymentMethod;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.repository.DeliveryMethodRepository;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.PaymentMethodRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL;
import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL_AND_PHYSICAL;
import static com.google.common.collect.Iterables.getOnlyElement;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GameHavenConfig.class)
public class PurchaseAcceptanceTest {

	@Autowired private GameRepository gameRepository;
	@Autowired private UserRepository userRepository;
	@Autowired private DeliveryMethodRepository deliveryMethodRepository;
	@Autowired private PaymentMethodRepository paymentMethodRepository;
	@Autowired private PurchaseService purchaseService;

	private DeliveryMethodId deliveryMethodId;
	private PaymentMethodId paymentMethodId;

	@Before
	public void init() {
		deliveryMethodId = givenADeliveryMethod();
		paymentMethodId = givenAPaymentMethod();
	}

	@Test
	public void shouldBeAbleToBuyAProduct() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameInTheCatalog();
		UserId userId = givenANewUser();

		Product product = purchaseService.getProduct(gameId, userId, DIGITAL);
		purchaseService.addToUsersBasket(userId, product);
		OfferDetails offerDetails = purchaseService.generateOfferFor(purchaseService.getUserBasketDetails(userId), deliveryMethodId, paymentMethodId);
		purchaseService.acceptOffer(offerDetails.offerId());
		PurchaseDetails purchaseDetails = getOnlyElement(purchaseService.getPurchasesOfUser(userId));
		purchaseService.confirmPurchase(purchaseDetails.purchaseId());

		assertThat(purchaseService.getUserDetails(userId).getGames()).contains(gameId);
	}

	@Test
	public void shouldBeAbleToBuyADigitalAndPhysicalCopyOfTheProduct() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameWithPhysicalVersionInTheCatalog();
		UserId userId = givenANewUser();

		Product product = purchaseService.getProduct(gameId, userId, DIGITAL_AND_PHYSICAL);
		purchaseService.addToUsersBasket(userId, product);
		OfferDetails offerDetails = purchaseService.generateOfferFor(purchaseService.getUserBasketDetails(userId), deliveryMethodId, paymentMethodId);
		purchaseService.acceptOffer(offerDetails.offerId());
		PurchaseDetails purchaseDetails = getOnlyElement(purchaseService.getPurchasesOfUser(userId));
		purchaseService.confirmPurchase(purchaseDetails.purchaseId());

		assertThat(purchaseService.getUserDetails(userId).getGames()).contains(gameId);
	}

	@Test
	public void shouldBeAbleToBuyAProductForSomeoneElse() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameInTheCatalog();
		UserId userId = givenANewUser();
		UserId otherUserId = givenANewUser();

		Product product = purchaseService.getProduct(gameId, otherUserId, DIGITAL);
		purchaseService.addToUsersBasket(userId, product);
		OfferDetails offerDetails = purchaseService.generateOfferFor(purchaseService.getUserBasketDetails(userId),deliveryMethodId,  paymentMethodId);
		purchaseService.acceptOffer(offerDetails.offerId());
		PurchaseDetails purchaseDetails = getOnlyElement(purchaseService.getPurchasesOfUser(userId));
		purchaseService.confirmPurchase(purchaseDetails.purchaseId());

		assertThat(purchaseService.getUserDetails(otherUserId).getGames()).contains(gameId);
		assertThat(purchaseService.getUserDetails(userId).getGames()).doesNotContain(gameId);
	}

	@Test
	public void shouldNotBeAbleToGenerateAProductIfItHadBeenAlreadyPurchased() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameInTheCatalog();
		UserId userId = givenANewUser();
		Product product = purchaseService.getProduct(gameId, userId, DIGITAL);
		purchaseService.addToUsersBasket(userId, product);
		OfferDetails offerDetails = purchaseService.generateOfferFor(purchaseService.getUserBasketDetails(userId), deliveryMethodId, paymentMethodId);
		purchaseService.acceptOffer(offerDetails.offerId());
		PurchaseDetails purchaseDetails = getOnlyElement(purchaseService.getPurchasesOfUser(userId));
		purchaseService.confirmPurchase(purchaseDetails.purchaseId());

		assertThatThrownBy(() -> purchaseService.getProduct(gameId, userId, DIGITAL)).isInstanceOf(ProductAlreadyPurchasedException.class);
	}

	private UserId givenANewUser() {
		User user = new User();
		userRepository.save(user);
		return user.id();
	}

	private GameId givenAGameInTheCatalog() {
		Game game =  new Game();
		gameRepository.save(game);
		return game.id();
	}

	private GameId givenAGameWithPhysicalVersionInTheCatalog() {
		Game game =  new Game();
		game.physicalVersionIsAvailable();
		gameRepository.save(game);
		return game.id();
	}

	private DeliveryMethodId givenADeliveryMethod() {
		DeliveryMethod deliveryMethod = new DeliveryMethod();
		deliveryMethodRepository.save(deliveryMethod);
		return deliveryMethod.id();
	}

	private PaymentMethodId givenAPaymentMethod() {
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethodRepository.save(paymentMethod);
		return paymentMethod.id();
	}
}
