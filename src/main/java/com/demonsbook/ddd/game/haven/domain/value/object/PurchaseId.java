package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.AggregateId;

import java.util.UUID;

public final class PurchaseId extends AggregateId {
	public PurchaseId() {
	}

	public PurchaseId(UUID value) {
		super(value);
	}
}
