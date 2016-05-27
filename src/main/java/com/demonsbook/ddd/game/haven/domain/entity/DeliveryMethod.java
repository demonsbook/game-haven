package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Aggregate;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;

public class DeliveryMethod extends Aggregate<DeliveryMethodId> {

	public DeliveryMethod() {
		super(new DeliveryMethodId());
	}

	protected DeliveryMethod(DeliveryMethodId id) {
		super(id);
	}
}
