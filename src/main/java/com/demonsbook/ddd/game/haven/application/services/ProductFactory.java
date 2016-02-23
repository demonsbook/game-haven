package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.GameId;
import com.demonsbook.ddd.game.haven.domain.Product;
import com.demonsbook.ddd.game.haven.domain.UserId;

public class ProductFactory {

	public Product createFor(GameId gameId, UserId userId) {
		return new Product();
	}
}
