package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;

import java.util.UUID;

public final class UserId extends EntityId {
	public UserId() {
	}

	public UserId(UUID value) {
		super(value);
	}
}
