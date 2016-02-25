package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;

import java.util.UUID;

public final class OfferId extends EntityId {
	public OfferId() {
	}

	public OfferId(UUID value) {
		super(value);
	}
}
