package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityIdAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.BasketId;
import org.junit.Test;

public class BasketIdTest {

	@Test
	public void shouldBeAValidEntityId() {
		EntityIdAssert.assertThat(new BasketId()).isAValidEntityId();
	}
}