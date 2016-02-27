package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;

import java.util.UUID;

public final class PurchaseId extends EntityId {
	public PurchaseId() {
	}

	public PurchaseId(UUID value) {
		super(value);
	}
}
