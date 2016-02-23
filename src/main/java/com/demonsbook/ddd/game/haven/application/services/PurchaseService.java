package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.GameId;
import com.demonsbook.ddd.game.haven.domain.Product;
import com.demonsbook.ddd.game.haven.domain.UserId;

public class PurchaseService {

	private final ProductFactory productFactory = new ProductFactory();

	public Product getProduct(GameId gameId, UserId userId) {
		return productFactory.createFor(gameId, userId);
	}

}
