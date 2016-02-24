package com.demonsbook.ddd.game.haven.domain;

public class ProductFactory {

	public Product createFor(Game game, User user) {
		return new Product();
	}
}
