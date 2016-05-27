package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.AggregateId;

import java.util.UUID;

public final class PaymentMethodId extends AggregateId {
	public PaymentMethodId() {
	}

	public PaymentMethodId(UUID value) {
		super(value);
	}
}
