package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;

import java.util.UUID;

public final class PaymentMethodId extends EntityId {
	public PaymentMethodId() {
	}

	public PaymentMethodId(UUID value) {
		super(value);
	}
}
