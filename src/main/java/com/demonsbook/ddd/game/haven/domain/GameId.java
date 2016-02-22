package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;

import java.util.UUID;

public final class GameId extends EntityId {

	public GameId() {
	}

	public GameId(UUID value) {
		super(value);
	}

}
