package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;

public class PaymentMethod extends Entity<PaymentMethodId> {
	public PaymentMethod() {
		super(new PaymentMethodId());
	}

	public PaymentMethod(PaymentMethodId id) {
		super(id);
	}
}
