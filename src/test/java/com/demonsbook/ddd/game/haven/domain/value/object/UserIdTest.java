package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityIdAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.junit.Test;

public class UserIdTest {

	@Test
	public void shouldBeAValidEntityId() {
		EntityIdAssert.assertThat(new UserId()).isAValidEntityId();
	}

}