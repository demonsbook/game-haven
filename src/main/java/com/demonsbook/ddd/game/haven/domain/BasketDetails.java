package com.demonsbook.ddd.game.haven.domain;

import java.util.HashSet;
import java.util.Set;

public class BasketDetails {
	private Set<ProductId> products = new HashSet<>();

	public Set<ProductId> getProducts() {
		return products;
	}
}
