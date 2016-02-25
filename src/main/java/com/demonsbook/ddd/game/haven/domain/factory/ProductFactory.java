package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;

public class ProductFactory {

	public Product createFor(Game game, User user) {
		return new Product();
	}
}
