package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.ImmutableSet.copyOf;

public class Basket extends Entity<BasketId> {

	private Set<Product> products = new HashSet<>();

	public Basket() {
		super(new BasketId());
	}

	public Basket(BasketId id) {
		super(id);
	}

	public void add(Product product) {
		products.add(product);
	}

	public BasketDetails getDetails() {
		return new BasketDetails(copyOf(products));
	}
}
