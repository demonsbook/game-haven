package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.exception.PhysicalVersionNotAvaliableExeption;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL_AND_PHYSICAL;

@Component
public class ProductFactory {

	@Autowired private UserRepository userRepository;
	@Autowired private GameRepository gameRepository;

	public Product createFor(UserId userId, GameId gameId, Product.Version version) throws ProductAlreadyPurchasedException {
		User user = userRepository.getForId(userId);
		Game game = gameRepository.getForId(gameId);

		return createFor(user, game, version);
	}

	private Product createFor(User user, Game game, Product.Version version) throws ProductAlreadyPurchasedException {
		verifyProductHasNotBeenAlreadyPurchased(user, game);
		verifyRequiredVersionIsAvailable(game, version);
		return new Product(user.id(), game.id(), version);
	}

	private void verifyRequiredVersionIsAvailable(Game game, Product.Version version) {
		if (version == DIGITAL_AND_PHYSICAL && !game.hasAPhysicalVersion()) {
			throw new PhysicalVersionNotAvaliableExeption(game.id());
		}
	}

	private void verifyProductHasNotBeenAlreadyPurchased(User user, Game game) throws ProductAlreadyPurchasedException {
		if (user.owns(game.id())) {
			throw new ProductAlreadyPurchasedException(user.id(), game.id());
		}
	}
}
