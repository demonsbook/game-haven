package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;

import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.ACCEPTED;
import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.DISCARDED;
import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.NEW;

public class Offer extends Entity<OfferId> {

	private Status status = NEW;

	public Offer() {
		super(new OfferId());
	}

	protected Offer(OfferId id) {
		super(id);
	}

	public OfferDetails getDetails() {
		return new OfferDetails(id);
	}

	public void accept() {
		status = ACCEPTED;
	}

	public void discard() {
		status = DISCARDED;
	}

	public Status status() {
		return status;
	}

	public enum Status {
		NEW, ACCEPTED, DISCARDED
	}
}
