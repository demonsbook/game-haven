package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity<UserId> {

	List<GameId> library = new ArrayList<>();
	private BasketDetails basketDetails = new BasketDetails();

	public User() {
		super(new UserId());
	}

	public void addGameToLibrary(Game game) {
		library.add(game.id());
	}

	public boolean owns(GameId id) {
		return library.contains(id);
	}

	public BasketDetails getBasketDetails() {
		return basketDetails;
	}
}