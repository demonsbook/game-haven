package com.demonsbook.ddd.game.haven.application.services;


import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.factory.ProductFactory;
import com.demonsbook.ddd.game.haven.domain.factory.PurchaseFactory;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserDetails;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.ACCEPTED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceTest {

	private User user = new User();
	private Game game = new Game();
	private Product product = new Product(user.id(), game.id());
	private Offer offer = new Offer(user.id(), ImmutableSet.of(product));
	private Purchase purchase = new Purchase(offer);

	private Product PRODUCT = new Product(user.id(), game.id());

	@Mock private ProductFactory productFactory;
	@Mock private PurchaseFactory purchaseFactory;
	@Mock private OfferFactory offerFactory;
	@Mock private UserRepository userRepository;
	@Mock private GameRepository gameRepository;
	@Mock private OfferRepository offerRepository;
	@Mock private PurchaseRepository purchaseRepository;
	@Mock private DomainEventPublisher domainEventPublisher;
	@InjectMocks private PurchaseService purchaseService;

	@Test
	public void shouldObtainProductDetailsForGivenGameAndUserCombination() {
		given(userRepository.getForId(user.id())).willReturn(user);
		given(gameRepository.getForId(game.id())).willReturn(game);
		given(productFactory.createFor(user, game)).willReturn(PRODUCT);

		Product product = purchaseService.getProduct(game.id(), user.id());

		assertThat(product).isSameAs(PRODUCT);
	}

	@Test
	public void shouldAddProductsToBasker() {
		given(userRepository.getForId(user.id())).willReturn(user);

		purchaseService.addToUsersBasket(user.id(), PRODUCT);

		assertThat(user.getBasketDetails().getProducts()).contains(PRODUCT);
	}

	@Test
	public void shouldGenerateAndStoreAnOfferForUser() {
		given(userRepository.getForId(user.id())).willReturn(user);
		given(offerFactory.createFor(user)).willReturn(offer);

		assertThat(purchaseService.generateOfferFor(user.id())).isNotNull();
		then(offerRepository).should().save(offer);
	}

	@Test
	public void shouldAllowAcceptanceOfGeneratedOffer() {
		given(offerRepository.getForId(offer.id())).willReturn(offer);
		given(purchaseFactory.createFor(offer)).willReturn(purchase);

		purchaseService.acceptOffer(offer.id());

		assertThat(offer.status()).isSameAs(ACCEPTED);
	}

	@Test
	public void shouldGenerateAndStorePurchaseAfterOfferHadBeenSuccesfullyAccepted() {
		given(offerRepository.getForId(offer.id())).willReturn(offer);
		given(purchaseFactory.createFor(offer)).willReturn(purchase);

		PurchaseDetails purchaseDetails = purchaseService.acceptOffer(offer.id());

		assertThat(purchaseDetails).isNotNull();
		then(purchaseRepository).should().save(purchase);
	}

	@Test
	public void shouldConfirmAPurchase() {
		given(purchaseRepository.getForId(purchase.id())).willReturn(purchase);

		purchaseService.confirmPurchase(purchase.id());

		assertThat(purchase.status()).isSameAs(Purchase.Status.CONFIRMED);
	}

	@Test
	public void shouldReturnUserDetails() {
		given(userRepository.getForId(user.id())).willReturn(user);

		UserDetails userDetails = purchaseService.getUserDetails(user.id());

		assertThat(userDetails).isNotNull();
	}

}