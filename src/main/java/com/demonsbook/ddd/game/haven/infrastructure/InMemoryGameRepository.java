package com.demonsbook.ddd.game.haven.infrastructure;

import com.demonsbook.ddd.game.haven.domain.Game;
import com.demonsbook.ddd.game.haven.domain.GameId;
import com.demonsbook.ddd.game.haven.domain.User;
import com.demonsbook.ddd.game.haven.domain.UserId;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryGameRepository implements GameRepository {

	Map<GameId, Game> games = new HashMap<>();

	@Override
	public Game getForId(GameId id) {
		return games.get(id);
	}

	@Override
	public void save(Game game) {
		games.put(game.id(), game);
	}
}
