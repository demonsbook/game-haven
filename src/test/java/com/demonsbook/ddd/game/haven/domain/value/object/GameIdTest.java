package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityIdAssert;

import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import org.junit.Test;

public class GameIdTest {

	@Test
	public void shouldBeAValidEntityId() {
		EntityIdAssert.assertThat(new GameId()).isAValidEntityId();
	}

}