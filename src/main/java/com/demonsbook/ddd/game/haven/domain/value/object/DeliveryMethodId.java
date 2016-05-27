package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.AggregateId;

import java.util.UUID;

public final class DeliveryMethodId extends AggregateId {
	public DeliveryMethodId() {
	}

	public DeliveryMethodId(UUID value) {
		super(value);
	}
}
