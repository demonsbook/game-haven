package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.AggregateId;

import java.util.UUID;

public final class ClientId extends AggregateId {
	public ClientId() {
	}

	public ClientId(UUID value) {
		super(value);
	}
}
