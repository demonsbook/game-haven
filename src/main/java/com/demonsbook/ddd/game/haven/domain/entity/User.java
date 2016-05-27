package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Aggregate;
import com.demonsbook.ddd.game.haven.domain.value.object.BasketDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.UserDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;

import java.util.HashSet;
import java.util.Set;

public class User extends Aggregate<UserId> {

	private Set<GameId> library = new HashSet<>();
	private Basket basket = new Basket();

	public User() {
		super(new UserId());
	}

	public void addGameToLibrary(GameId gameId) {
		library.add(gameId);
	}

	public boolean owns(GameId id) {
		return library.contains(id);
	}

	public BasketDetails getBasketDetails() {
		return new BasketDetails(basket.getProducts(), id);
	}

	public void addToBasket(Product product) {
		basket.add(product);
	}

	public UserDetails details() {
		return new UserDetails(id, library);
	}
}
