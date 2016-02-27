package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;

public class Offer extends Entity<OfferId> {

	public Offer() {
		super(new OfferId());
	}

	protected Offer(OfferId id) {
		super(id);
	}

	public OfferDetails getDetails() {
		return new OfferDetails(id);
	}

	public Purchase accept() {
		return new Purchase();
	}
}
