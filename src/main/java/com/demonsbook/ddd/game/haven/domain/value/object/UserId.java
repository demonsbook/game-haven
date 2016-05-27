package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.AggregateId;

import java.util.UUID;

public final class UserId extends AggregateId {
	public UserId() {
	}

	public UserId(UUID value) {
		super(value);
	}
}
