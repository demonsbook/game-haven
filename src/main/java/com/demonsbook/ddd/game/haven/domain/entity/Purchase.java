package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Aggregate;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import com.demonsbook.ddd.game.haven.domain.value.object.Money;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseId;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.CONFIRMED;
import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.NEW;

public class Purchase extends Aggregate<PurchaseId> {

	private Status status = NEW;
	private ClientId clientId;
	private Money price;
	private Set<Product> products;

	public Purchase(Offer offer) {
		super(new PurchaseId());
		this.clientId = offer.clientId();
		this.products = offer.products();
		this.price = offer.price();
	}

	protected Purchase(PurchaseId id) {
		super(id);
	}

	public PurchaseDetails getDetails() {
		return new PurchaseDetails(id);
	}

	public void confirm() {
		status = CONFIRMED;
	}

	public Status status() {
		return status;
	}

	public ClientId clientId() {
		return clientId;
	}

	public Set<Product> products() {
		return ImmutableSet.copyOf(products);
	}

	public Money price() {
		return price;
	}

	public enum Status {
		NEW, CONFIRMED
	}
}
