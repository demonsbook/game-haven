package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.building.blocks.AggregateId;

import java.util.UUID;

public final class GameId extends AggregateId {

	public GameId() {
	}

	public GameId(UUID value) {
		super(value);
	}

}
