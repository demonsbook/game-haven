package com.demonsbook.ddd.game.haven.domain;

import java.util.HashSet;
import java.util.Set;

public class BasketDetails {
	private Set<Product> products = new HashSet<>();

	public BasketDetails(Set<Product> products) {
		this.products = products;
	}

	public Set<Product> getProducts() {
		return products;
	}
}
