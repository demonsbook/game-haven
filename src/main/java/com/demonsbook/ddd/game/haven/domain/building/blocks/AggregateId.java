package com.demonsbook.ddd.game.haven.domain.building.blocks;

import java.util.UUID;

public class AggregateId extends EntityId {

	public AggregateId(UUID value) {
		super(value);
	}

	public AggregateId() {
	}
}
