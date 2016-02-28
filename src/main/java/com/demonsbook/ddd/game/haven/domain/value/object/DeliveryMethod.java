package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;

public class DeliveryMethod extends Entity<DeliveryMethodId> {

	public DeliveryMethod() {
		super(new DeliveryMethodId());
	}

	protected DeliveryMethod(DeliveryMethodId id) {
		super(id);
	}
}
