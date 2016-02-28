package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyInTheBasketException;
import com.demonsbook.ddd.game.haven.domain.value.object.BasketId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;

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
		if (products.contains(product)) {
			throw new ProductAlreadyInTheBasketException();
		}
		products.add(product);
	}

	public Set<Product> getProducts() {
		return copyOf(products);
	}
}
