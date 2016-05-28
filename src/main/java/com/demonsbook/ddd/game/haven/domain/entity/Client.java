package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Aggregate;
import com.demonsbook.ddd.game.haven.domain.value.object.BasketDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;

import java.util.HashSet;
import java.util.Set;

public class Client extends Aggregate<ClientId> {

	private Set<GameId> library = new HashSet<>();
	private Basket basket = new Basket();

	public Client() {
		super(new ClientId());
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

	public ClientDetails details() {
		return new ClientDetails(id, library);
	}
}
