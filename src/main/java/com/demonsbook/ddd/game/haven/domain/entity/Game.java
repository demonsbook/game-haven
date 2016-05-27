package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Aggregate;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;

public class Game extends Aggregate<GameId> {

	private boolean hasAPhysicalVersion = false;

	public Game() {
		super(new GameId());
	}

	public void physicalVersionIsAvailable() {
		this.hasAPhysicalVersion = true;
	}

	public void physicalVersionIsNotAvailable() {
		this.hasAPhysicalVersion = false;
	}

	public boolean hasAPhysicalVersion() {
		return hasAPhysicalVersion;
	}

}
