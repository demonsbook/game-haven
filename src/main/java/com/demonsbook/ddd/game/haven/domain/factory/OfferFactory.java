package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class OfferFactory {
	public Offer createFor(User user) {
		return new Offer();
	}
}
