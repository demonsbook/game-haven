package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.ACCEPTED;
import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.DISCARDED;
import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.NEW;

public class Offer extends Entity<OfferId> {

	private Status status = NEW;
	private UserId userId;
	private Set<Product> products;

	public Offer(UserId userId, Set<Product> products) {
		super(new OfferId());
		this.userId = userId;
		this.products = ImmutableSet.copyOf(products);
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

	public UserId userId() {
		return userId;
	}

	public Set<Product> products() {
		return ImmutableSet.copyOf(products);
	}

	public enum Status {
		NEW, ACCEPTED, DISCARDED
	}
}
