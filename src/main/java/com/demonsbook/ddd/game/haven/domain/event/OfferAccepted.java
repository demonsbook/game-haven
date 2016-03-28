package com.demonsbook.ddd.game.haven.domain.event;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEvent;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;

public class OfferAccepted implements DomainEvent {
	private OfferId offerId;

	public OfferAccepted(OfferId offerId) {
		this.offerId = offerId;
	}

	public OfferId offerId() {
		return offerId;
	}
}
