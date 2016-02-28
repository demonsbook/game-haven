package com.demonsbook.ddd.game.haven.domain.exception;

import com.demonsbook.ddd.game.haven.domain.value.object.GameId;

import static java.lang.String.format;

public class PhysicalVersionNotAvaliableExeption extends RuntimeException {
	public PhysicalVersionNotAvaliableExeption(GameId id) {
		super(format("Physical copy not available for game %s", id));
	}
}
