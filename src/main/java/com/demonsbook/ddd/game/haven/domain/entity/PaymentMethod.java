package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Aggregate;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;

public class PaymentMethod extends Aggregate<PaymentMethodId> {
	public PaymentMethod() {
		super(new PaymentMethodId());
	}

	public PaymentMethod(PaymentMethodId id) {
		super(id);
	}
}
