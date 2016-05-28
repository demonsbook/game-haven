package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Aggregate;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.Money;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.ACCEPTED;
import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.DISCARDED;
import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.NEW;

public class Offer extends Aggregate<OfferId> {

	private Status status = NEW;
	private ClientId clientId;
	private Set<Product> products;
	private Money price;
	private DeliveryMethodId deliveryMethodId;
	private PaymentMethodId paymentMethodId;

	public Offer(ClientId clientId, Set<Product> products, Money price, DeliveryMethodId deliveryMethodId, PaymentMethodId paymentMethodId) {
		super(new OfferId());
		this.clientId = clientId;
		this.products = ImmutableSet.copyOf(products);
		this.price = price;
		this.deliveryMethodId = deliveryMethodId;
		this.paymentMethodId = paymentMethodId;
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

	void discard() {
		status = DISCARDED;
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

	public DeliveryMethodId deliveryMethodId() {
		return deliveryMethodId;
	}

	public PaymentMethodId paymentMethodId() {
		return paymentMethodId;
	}

	public Money price() {
		return price;
	}

	public enum Status {
		NEW, ACCEPTED, DISCARDED
	}
}
