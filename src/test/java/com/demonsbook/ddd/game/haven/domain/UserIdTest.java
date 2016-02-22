package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityIdAssert;
import org.junit.Test;

public class UserIdTest {

	@Test
	public void shouldBeAValidEntityId() {
		EntityIdAssert.assertThat(new UserId()).isAValidEntityId();
	}

}