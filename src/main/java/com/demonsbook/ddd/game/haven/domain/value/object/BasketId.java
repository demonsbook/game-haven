package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;

import java.util.UUID;

public final class BasketId extends EntityId {

	public BasketId() {
	}

	public BasketId(UUID value) {
		super(value);
	}
}
