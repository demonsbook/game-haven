package com.demonsbook.ddd.game.haven.domain.value.object;

public final class Product {

	private final UserId userId;
	private final GameId gameId;

	public Product(UserId userId, GameId gameId) {
		this.userId = userId;
		this.gameId = gameId;
	}

	public UserId userId() {
		return userId;
	}

	public GameId gameId() {
		return gameId;
	}
}
