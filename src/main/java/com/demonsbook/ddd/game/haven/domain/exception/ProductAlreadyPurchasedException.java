package com.demonsbook.ddd.game.haven.domain.exception;

import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;

import static java.lang.String.format;

public class ProductAlreadyPurchasedException extends Exception {

	public ProductAlreadyPurchasedException(ClientId clientId, GameId gameId) {
		super(format("Client %s already has the game %s in his library", clientId, gameId));
	}
}
