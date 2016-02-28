package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;

public class DeliveryMethod extends Entity<DeliveryMethodId> {

	public DeliveryMethod() {
		super(new DeliveryMethodId());
	}

	protected DeliveryMethod(DeliveryMethodId id) {
		super(id);
	}
}
