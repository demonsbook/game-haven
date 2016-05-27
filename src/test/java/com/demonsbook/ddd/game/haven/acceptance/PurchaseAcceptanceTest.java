package com.demonsbook.ddd.game.haven.acceptance;

import com.demonsbook.ddd.game.haven.application.services.PaymentService;
import com.demonsbook.ddd.game.haven.application.services.OfferService;
import com.demonsbook.ddd.game.haven.application.services.PurchaseService;
import com.demonsbook.ddd.game.haven.config.GameHavenConfig;
import com.demonsbook.ddd.game.haven.domain.entity.DeliveryMethod;
import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.PaymentMethod;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.factory.ProductFactory;
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
	@Autowired private ProductFactory productFactory;
	@Autowired private PurchaseService purchaseService;
	@Autowired private PaymentService paymentService;
	@Autowired private OfferService offerService;

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
		Product product = productFactory.aProduct().forGame(gameId).forUser(userId).inVersion(DIGITAL).create();

		whenUserAddsAProductToHisBasket(userId, product);
		whenUserGeneratesAndAcceptsTheOffer(userId);
		whenPaymentIsMade(userId);

		assertThat(purchaseService.getUserDetails(userId).getGames()).contains(gameId);
	}

	@Test
	public void shouldBeAbleToBuyADigitalAndPhysicalCopyOfTheProduct() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameWithPhysicalVersionInTheCatalog();
		UserId userId = givenANewUser();
		Product product = productFactory.aProduct().forGame(gameId).forUser(userId).inVersion(DIGITAL_AND_PHYSICAL).create();

		whenUserAddsAProductToHisBasket(userId, product);
		whenUserGeneratesAndAcceptsTheOffer(userId);
		whenPaymentIsMade(userId);

		assertThat(purchaseService.getUserDetails(userId).getGames()).contains(gameId);
	}

	private void whenUserAddsAProductToHisBasket(UserId userId, Product product) {
		purchaseService.addProductToUsersBasket(userId, product);
	}

	@Test
	public void shouldBeAbleToBuyAProductForSomeoneElse() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameInTheCatalog();
		UserId userId = givenANewUser();
		UserId otherUserId = givenANewUser();
		Product product = productFactory.aProduct().forGame(gameId).forUser(otherUserId).inVersion(DIGITAL).create();

		whenUserAddsAProductToHisBasket(userId, product);
		whenUserGeneratesAndAcceptsTheOffer(userId);
		whenPaymentIsMade(userId);

		assertThat(purchaseService.getUserDetails(otherUserId).getGames()).contains(gameId);
		assertThat(purchaseService.getUserDetails(userId).getGames()).doesNotContain(gameId);
	}

	@Test
	public void shouldNotBeAbleToGenerateAProductIfItHadBeenAlreadyPurchased() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameInTheCatalog();
		UserId userId = givenANewUser();
		Product product = productFactory.aProduct().forGame(gameId).forUser(userId).inVersion(DIGITAL).create();

		whenUserAddsAProductToHisBasket(userId, product);
		whenUserGeneratesAndAcceptsTheOffer(userId);
		whenPaymentIsMade(userId);

		assertThatThrownBy(
				() -> productFactory.aProduct().forGame(gameId).forUser(userId).inVersion(DIGITAL).create()
		).isInstanceOf(ProductAlreadyPurchasedException.class);
	}

	@Test
	public void shouldSetUpAPaymentWhenPurchaseIsGenerated() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameInTheCatalog();
		UserId userId = givenANewUser();
		Product product = productFactory.aProduct().forGame(gameId).forUser(userId).inVersion(DIGITAL).create();

		whenUserAddsAProductToHisBasket(userId, product);
		whenUserGeneratesAndAcceptsTheOffer(userId);

		PurchaseDetails purchaseDetails = getOnlyElement(purchaseService.getPurchasesOfUser(userId));
		assertThat(paymentService.isPaymentOpen(purchaseDetails.purchaseId())).isTrue();
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

	private void whenPaymentIsMade(UserId userId) {
		PurchaseDetails purchaseDetails = getOnlyElement(purchaseService.getPurchasesOfUser(userId));
		paymentService.payFor(purchaseDetails.purchaseId());
	}

	private void whenUserGeneratesAndAcceptsTheOffer(UserId userId) {
		offerService.generateOfferFor(userId, deliveryMethodId, paymentMethodId);
		OfferDetails offerDetails = getOnlyElement(offerService.getOffersFor(userId));
		offerService.acceptOffer(offerDetails.offerId());
	}
}
