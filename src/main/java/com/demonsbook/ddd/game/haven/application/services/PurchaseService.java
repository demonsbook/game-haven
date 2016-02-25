package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.factory.ProductFactory;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;

public class PurchaseService {

	private final ProductFactory productFactory;
	private final OfferFactory offerFactory;
	private final UserRepository userRepository;
	private final GameRepository gameRepository;

	public PurchaseService(ProductFactory productFactory, OfferFactory offerFactory, UserRepository userRepository, GameRepository gameRepository) {
		this.productFactory = productFactory;
		this.offerFactory = offerFactory;
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

	public OfferDetails generateOfferFor(UserId userId) {
		User user = userRepository.getForId(userId);
		return offerFactory.createFor(user).getDetails();
	}
}
