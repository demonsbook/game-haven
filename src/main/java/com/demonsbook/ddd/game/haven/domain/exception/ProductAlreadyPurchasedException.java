package com.demonsbook.ddd.game.haven.domain.exception;

import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;

import static java.lang.String.format;

public class ProductAlreadyPurchasedException extends Exception {

	public ProductAlreadyPurchasedException(UserId userId, GameId gameId) {
		super(format("User %s already has the game %s in his library", userId, gameId));
	}
}
