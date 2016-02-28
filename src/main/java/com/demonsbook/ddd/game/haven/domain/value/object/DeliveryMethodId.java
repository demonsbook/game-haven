package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;

import java.util.UUID;

public final class DeliveryMethodId extends EntityId {
	public DeliveryMethodId() {
	}

	public DeliveryMethodId(UUID value) {
		super(value);
	}
}
