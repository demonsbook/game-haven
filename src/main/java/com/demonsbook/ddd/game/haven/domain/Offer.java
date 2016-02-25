package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;

public class Offer extends Entity<OfferId> {

	public Offer() {
		super(new OfferId());
	}

	protected Offer(OfferId id) {
		super(id);
	}

	public OfferDetails getDetails() {
		return new OfferDetails();
	}
}
