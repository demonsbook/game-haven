package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.AggregateId;

import java.util.UUID;

public final class OfferId extends AggregateId {
	public OfferId() {
	}

	public OfferId(UUID value) {
		super(value);
	}
}
