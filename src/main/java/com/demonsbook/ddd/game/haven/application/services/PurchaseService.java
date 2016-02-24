package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.ProductFactory;
import com.demonsbook.ddd.game.haven.domain.Game;
import com.demonsbook.ddd.game.haven.domain.GameId;
import com.demonsbook.ddd.game.haven.domain.Product;
import com.demonsbook.ddd.game.haven.domain.User;
import com.demonsbook.ddd.game.haven.domain.UserId;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.infrastructure.InMemoryGameRepository;
import com.demonsbook.ddd.game.haven.infrastructure.InMemoryUserRepository;

public class PurchaseService {

	private final ProductFactory productFactory = new ProductFactory();
	private final UserRepository userRepository;
	private final GameRepository gameRepository;

	public PurchaseService(UserRepository userRepository, GameRepository gameRepository) {
		this.userRepository = userRepository;
		this.gameRepository = gameRepository;
	}

	public Product getProduct(GameId gameId, UserId userId) {
		User user = userRepository.getForId(userId);
		Game game = gameRepository.getForId(gameId);
		return productFactory.createFor(game, user);
	}

	public void addToUsersBasket(UserId userId, Product product) {
		User user = userRepository.getForId(userId);
		user.addToBasket(product);
	}
}
