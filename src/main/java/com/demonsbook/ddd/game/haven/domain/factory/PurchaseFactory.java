package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import org.springframework.stereotype.Component;

@Component
public class PurchaseFactory {
	public Purchase createFor(Offer offer) {
		return new Purchase(offer);
	}
}
