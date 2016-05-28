package com.demonsbook.ddd.game.haven.acceptance;

import com.demonsbook.ddd.game.haven.application.services.PaymentService;
import com.demonsbook.ddd.game.haven.application.services.OfferService;
import com.demonsbook.ddd.game.haven.application.services.PurchaseService;
import com.demonsbook.ddd.game.haven.config.GameHavenConfig;
import com.demonsbook.ddd.game.haven.domain.entity.DeliveryMethod;
import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.PaymentMethod;
import com.demonsbook.ddd.game.haven.domain.entity.Client;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.factory.ProductFactory;
import com.demonsbook.ddd.game.haven.domain.repository.DeliveryMethodRepository;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.PaymentMethodRepository;
import com.demonsbook.ddd.game.haven.domain.repository.ClientRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
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
	@Autowired private ClientRepository clientRepository;
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
		ClientId clientId = givenANewClient();
		Product product = productFactory.aProduct().forGame(gameId).forClient(clientId).inVersion(DIGITAL).create();

		whenClientAddsAProductToHisBasket(clientId, product);
		whenClientGeneratesAndAcceptsTheOffer(clientId);
		whenPaymentIsMade(clientId);

		assertThat(purchaseService.getClientDetails(clientId).getGames()).contains(gameId);
	}

	@Test
	public void shouldBeAbleToBuyADigitalAndPhysicalCopyOfTheProduct() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameWithPhysicalVersionInTheCatalog();
		ClientId clientId = givenANewClient();
		Product product = productFactory.aProduct().forGame(gameId).forClient(clientId).inVersion(DIGITAL_AND_PHYSICAL).create();

		whenClientAddsAProductToHisBasket(clientId, product);
		whenClientGeneratesAndAcceptsTheOffer(clientId);
		whenPaymentIsMade(clientId);

		assertThat(purchaseService.getClientDetails(clientId).getGames()).contains(gameId);
	}

	private void whenClientAddsAProductToHisBasket(ClientId clientId, Product product) {
		purchaseService.addProductToClientsBasket(clientId, product);
	}

	@Test
	public void shouldBeAbleToBuyAProductForSomeoneElse() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameInTheCatalog();
		ClientId clientId = givenANewClient();
		ClientId otherClientId = givenANewClient();
		Product product = productFactory.aProduct().forGame(gameId).forClient(otherClientId).inVersion(DIGITAL).create();

		whenClientAddsAProductToHisBasket(clientId, product);
		whenClientGeneratesAndAcceptsTheOffer(clientId);
		whenPaymentIsMade(clientId);

		assertThat(purchaseService.getClientDetails(otherClientId).getGames()).contains(gameId);
		assertThat(purchaseService.getClientDetails(clientId).getGames()).doesNotContain(gameId);
	}

	@Test
	public void shouldNotBeAbleToGenerateAProductIfItHadBeenAlreadyPurchased() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameInTheCatalog();
		ClientId clientId = givenANewClient();
		Product product = productFactory.aProduct().forGame(gameId).forClient(clientId).inVersion(DIGITAL).create();

		whenClientAddsAProductToHisBasket(clientId, product);
		whenClientGeneratesAndAcceptsTheOffer(clientId);
		whenPaymentIsMade(clientId);

		assertThatThrownBy(
				() -> productFactory.aProduct().forGame(gameId).forClient(clientId).inVersion(DIGITAL).create()
		).isInstanceOf(ProductAlreadyPurchasedException.class);
	}

	@Test
	public void shouldSetUpAPaymentWhenPurchaseIsGenerated() throws ProductAlreadyPurchasedException {
		GameId gameId = givenAGameInTheCatalog();
		ClientId clientId = givenANewClient();
		Product product = productFactory.aProduct().forGame(gameId).forClient(clientId).inVersion(DIGITAL).create();

		whenClientAddsAProductToHisBasket(clientId, product);
		whenClientGeneratesAndAcceptsTheOffer(clientId);

		PurchaseDetails purchaseDetails = getOnlyElement(purchaseService.getPurchasesOfClient(clientId));
		assertThat(paymentService.isPaymentOpen(purchaseDetails.purchaseId())).isTrue();
	}

	private ClientId givenANewClient() {
		Client client = new Client();
		clientRepository.save(client);
		return client.id();
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

	private void whenPaymentIsMade(ClientId clientId) {
		PurchaseDetails purchaseDetails = getOnlyElement(purchaseService.getPurchasesOfClient(clientId));
		paymentService.payFor(purchaseDetails.purchaseId());
	}

	private void whenClientGeneratesAndAcceptsTheOffer(ClientId clientId) {
		offerService.generateOfferFor(clientId, deliveryMethodId, paymentMethodId);
		OfferDetails offerDetails = getOnlyElement(offerService.getOffersFor(clientId));
		offerService.acceptOffer(offerDetails.offerId());
	}
}
