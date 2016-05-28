package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityIdAssert;
import org.junit.Test;

public class ClientIdTest {

	@Test
	public void shouldBeAValidEntityId() {
		EntityIdAssert.assertThat(new ClientId()).isAValidEntityId();
	}

}