package com.demonsbook.ddd.game.haven.infrastructure;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class InMemoryGameRepositoryTest {

	private GameRepository gameRepository = new InMemoryGameRepository();

	@Test
	public void shouldStoreMultipleGames() {
		Game game1 = new Game();
		Game game2 = new Game();

		gameRepository.save(game1);
		gameRepository.save(game2);

		Assertions.assertThat(gameRepository.getForId(game1.id())).isSameAs(game1);
		Assertions.assertThat(gameRepository.getForId(game2.id())).isSameAs(game2);
	}

	@Test
	public void shouldReturnNullForNonExistentGame() {
		Game game = new Game();

		Assertions.assertThat(gameRepository.getForId(game.id())).isNull();
	}

}