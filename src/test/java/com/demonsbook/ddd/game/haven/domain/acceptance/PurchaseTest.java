package com.demonsbook.ddd.game.haven.domain.acceptance;

import com.demonsbook.ddd.game.haven.domain.Game;
import com.demonsbook.ddd.game.haven.domain.GameId;
import com.demonsbook.ddd.game.haven.domain.Product;
import com.demonsbook.ddd.game.haven.domain.User;
import com.demonsbook.ddd.game.haven.domain.UserId;
import com.demonsbook.ddd.game.haven.application.services.PurchaseService;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.infrastructure.InMemoryGameRepository;
import com.demonsbook.ddd.game.haven.infrastructure.InMemoryUserRepository;
import org.junit.Test;

public class PurchaseTest {

	private GameId gameId;
	private UserId userId;
	private GameRepository gameRepository = new InMemoryGameRepository();
	private UserRepository userRepository = new InMemoryUserRepository();
	private PurchaseService purchaseService = new PurchaseService(userRepository, gameRepository);

	@Test
	public void shouldBeAbleToBuyAProduct() {
		givenAGameInTheCatalog();
		givenANewUser();

		Product product = purchaseService.getProduct(gameId, userId);
		purchaseService.addToUsersBasket(userId, product);
//		OfferDetails offerDetails = purchaseService.generateOfferFor(userId);
//		PurchaseDetails purchaseDetails = purchaseService.acceptOffer(offerDetails.offerId());
//		purchaseService.confirmPurchase(purchaseDetails.purchaseId());
//
//		assertThat(purchaseService.getGamesOf(userId)).contains(gameId);
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
