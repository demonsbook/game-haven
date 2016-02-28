package com.demonsbook.ddd.game.haven.domain.value.object;

public final class Product {

	private final UserId userId;
	private final GameId gameId;
	private final Version version;

	public Product(UserId userId, GameId gameId, Version version) {
		this.userId = userId;
		this.gameId = gameId;
		this.version = version;
	}

	public UserId userId() {
		return userId;
	}

	public GameId gameId() {
		return gameId;
	}

	public enum Version {
		DIGITAL, DIGITAL_AND_PHYSICAL
	}
}
