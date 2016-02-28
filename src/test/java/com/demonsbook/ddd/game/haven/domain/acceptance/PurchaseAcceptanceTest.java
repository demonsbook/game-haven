package com.demonsbook.ddd.game.haven.domain.acceptance;

import com.demonsbook.ddd.game.haven.application.services.PurchaseService;
import com.demonsbook.ddd.game.haven.config.GameHavenConfig;
import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import com.demonsbook.ddd.game.haven.infrastructure.storage.InMemoryGameRepository;
import com.demonsbook.ddd.game.haven.infrastructure.storage.InMemoryUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GameHavenConfig.class)
public class PurchaseAcceptanceTest {

	private GameId gameId;
	private UserId userId;

	@Autowired private GameRepository gameRepository = new InMemoryGameRepository();
	@Autowired private UserRepository userRepository = new InMemoryUserRepository();
	@Autowired private PurchaseService purchaseService = new PurchaseService();

	@Test
	public void shouldBeAbleToBuyAProduct() throws ProductAlreadyPurchasedException {
		givenAGameInTheCatalog();
		givenANewUser();

		Product product = purchaseService.getProduct(gameId, userId);
		purchaseService.addToUsersBasket(userId, product);
		OfferDetails offerDetails = purchaseService.generateOfferFor(userId);
		PurchaseDetails purchaseDetails = purchaseService.acceptOffer(offerDetails.offerId());
		purchaseService.confirmPurchase(purchaseDetails.purchaseId());

		assertThat(purchaseService.getUserDetails(userId).getGames()).contains(gameId);
	}

	@Test
	public void shouldNotBeAbleToGenerateAProductIfItHadBeenAlreadyPurchased() throws ProductAlreadyPurchasedException {
		givenAGameInTheCatalog();
		givenANewUser();
		Product product = purchaseService.getProduct(gameId, userId);
		purchaseService.addToUsersBasket(userId, product);
		OfferDetails offerDetails = purchaseService.generateOfferFor(userId);
		PurchaseDetails purchaseDetails = purchaseService.acceptOffer(offerDetails.offerId());
		purchaseService.confirmPurchase(purchaseDetails.purchaseId());

		assertThatThrownBy(() -> purchaseService.getProduct(gameId, userId)).isInstanceOf(ProductAlreadyPurchasedException.class);
	}

	private void givenANewUser() {
		User user = new User();
		userRepository.save(user);
		userId = user.id();
	}

	private void givenAGameInTheCatalog() {
		Game game =  new Game();
		gameRepository.save(game);
		gameId = game.id();
	}
}
