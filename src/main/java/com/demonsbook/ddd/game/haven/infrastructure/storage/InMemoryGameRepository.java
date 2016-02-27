package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryGameRepository implements GameRepository {

	private Map<GameId, Game> games = new HashMap<>();

	@Override
	public Game getForId(GameId id) {
		return games.get(id);
	}

	@Override
	public void save(Game game) {
		games.put(game.id(), game);
	}
}
