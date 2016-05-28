package com.demonsbook.ddd.game.haven.domain.value.object;

public final class Product {

	private final ClientId clientId;
	private final GameId gameId;
	private final Version version;

	public Product(ClientId clientId, GameId gameId, Version version) {
		this.clientId = clientId;
		this.gameId = gameId;
		this.version = version;
	}

	public ClientId clientId() {
		return clientId;
	}

	public GameId gameId() {
		return gameId;
	}

	public enum Version {
		DIGITAL, DIGITAL_AND_PHYSICAL
	}
}
