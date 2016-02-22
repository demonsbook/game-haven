package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;

public class Game extends Entity<GameId> {

	public Game() {
		super(new GameId());
	}

}
