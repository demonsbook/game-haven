package com.demonsbook.ddd.game.haven.domain;

public class OfferFactory {
	public Offer createFor(User user) {
		return new Offer();
	}
}
