package com.demonsbook.ddd.game.haven.domain.value.object;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public final class UserDetails {
	private final UserId userId;
	private final Set<GameId> games;

	public UserDetails(UserId userId, Set<GameId> games) {
		this.games = ImmutableSet.copyOf(games);
		this.userId = userId;
	}

	public UserId getUserId() {
		return userId;
	}

	public Set<GameId> getGames() {
		return ImmutableSet.copyOf(games);
	}
}
