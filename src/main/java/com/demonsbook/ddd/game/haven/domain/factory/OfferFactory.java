package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.User;

public class OfferFactory {
	public Offer createFor(User user) {
		return new Offer();
	}
}
