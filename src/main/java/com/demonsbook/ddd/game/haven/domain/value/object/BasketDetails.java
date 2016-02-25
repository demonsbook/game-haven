package com.demonsbook.ddd.game.haven.domain.value.object;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public final class BasketDetails {
	private final UserId userId;
	private final Set<Product> products;

	public BasketDetails(Set<Product> products, UserId userId) {
		this.products = ImmutableSet.copyOf(products);
		this.userId = userId;
	}

	public UserId getUserId() {
		return userId;
	}

	public Set<Product> getProducts() {
		return ImmutableSet.copyOf(products);
	}
}
