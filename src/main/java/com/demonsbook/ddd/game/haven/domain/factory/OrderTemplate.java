package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.value.object.BasketDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;

public class OrderTemplate {
	private final BasketDetails basketDetails;
	private final DeliveryMethodId deliveryMethodId;
	private final PaymentMethodId paymentMethodId;

	public OrderTemplate(BasketDetails basketDetails, DeliveryMethodId deliveryMethodId, PaymentMethodId paymentMethodId) {
		this.basketDetails = basketDetails;
		this.deliveryMethodId = deliveryMethodId;
		this.paymentMethodId = paymentMethodId;
	}

	public BasketDetails getBasketDetails() {
		return basketDetails;
	}

	public DeliveryMethodId getDeliveryMethod() {
		return deliveryMethodId;
	}

	public PaymentMethodId getPaymentMethodId() {
		return paymentMethodId;
	}
}
