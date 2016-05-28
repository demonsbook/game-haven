package com.demonsbook.ddd.game.haven.domain.value.object;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public final class ClientDetails {
	private final ClientId clientId;
	private final Set<GameId> games;

	public ClientDetails(ClientId clientId, Set<GameId> games) {
		this.games = ImmutableSet.copyOf(games);
		this.clientId = clientId;
	}

	public ClientId getClientId() {
		return clientId;
	}

	public Set<GameId> getGames() {
		return ImmutableSet.copyOf(games);
	}
}
