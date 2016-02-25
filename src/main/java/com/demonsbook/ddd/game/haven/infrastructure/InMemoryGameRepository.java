package com.demonsbook.ddd.game.haven.infrastructure;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;

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