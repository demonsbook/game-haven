package com.demonsbook.ddd.game.haven.domain.acceptance;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.factory.ProductFactory;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import com.demonsbook.ddd.game.haven.application.services.PurchaseService;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.infrastructure.InMemoryGameRepository;
import com.demonsbook.ddd.game.haven.infrastructure.InMemoryUserRepository;
import org.junit.Test;

public class PurchaseTest {

	private GameId gameId;
	private UserId userId;
	private ProductFactory productFactory = new ProductFactory();
	private OfferFactory offerFactory = new OfferFactory();
	private GameRepository gameRepository = new InMemoryGameRepository();
	private UserRepository userRepository = new InMemoryUserRepository();
	private PurchaseService purchaseService = new PurchaseService(productFactory, offerFactory, userRepository, gameRepository);

	@Test
	public void shouldBeAbleToBuyAProduct() {
		givenAGameInTheCatalog();
		givenANewUser();

		Product product = purchaseService.getProduct(gameId, userId);
		purchaseService.addToUsersBasket(userId, product);
		OfferDetails offerDetails = purchaseService.generateOfferFor(userId);
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
