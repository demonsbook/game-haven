package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.junit.Test;

public class GameTest {

	private Game game = new Game();

	@Test
	public void shouldBeAValidEntity() {
		EntityAssert.assertThat(game).isAValidEntity();
	}

}
