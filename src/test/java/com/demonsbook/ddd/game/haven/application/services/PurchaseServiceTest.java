package com.demonsbook.ddd.game.haven.application.services;


import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.factory.ProductFactory;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceTest {

	private final static Product PRODUCT = new Product();

	private Offer offer = new Offer();
	private User user = new User();
	private Game game = new Game();

	@Mock private ProductFactory productFactory;
	@Mock private OfferFactory offerFactory;
	@Mock private UserRepository userRepository;
	@Mock private GameRepository gameRepository;
	@Mock private OfferRepository offerRepository;
	@InjectMocks private PurchaseService purchaseService;

	@Test
	public void shouldObtainProductDetailsForGivenGameAndUserCombination() {
		given(userRepository.getForId(user.id())).willReturn(user);
		given(gameRepository.getForId(game.id())).willReturn(game);
		given(productFactory.createFor(game, user)).willReturn(PRODUCT);

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
		given(userRepository.getForId(user.id())).willReturn(user);
		given(offerFactory.createFor(user)).willReturn(offer);
		given(offerRepository.getForId(offer.id())).willReturn(offer);

		PurchaseDetails purchaseDetails = purchaseService.acceptOffer(offer.id());

		assertThat(purchaseDetails).isNotNull();
	}

}