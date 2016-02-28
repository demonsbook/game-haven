package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory {

	public Product createFor(User user, Game game) throws ProductAlreadyPurchasedException {
		verifyProductHasNotBeenAlreadyPurchased(user, game);
		return new Product(user.id(), game.id());
	}

	private void verifyProductHasNotBeenAlreadyPurchased(User user, Game game) throws ProductAlreadyPurchasedException {
		if (user.owns(game.id())) {
			throw new ProductAlreadyPurchasedException(user.id(), game.id());
		}
	}
}
