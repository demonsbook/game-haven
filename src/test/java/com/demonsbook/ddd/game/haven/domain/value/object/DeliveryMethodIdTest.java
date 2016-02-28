package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityIdAssert;
import org.junit.Test;

public class DeliveryMethodIdTest {
	@Test
	public void shouldBeAValidValueObject() {
		EntityIdAssert.assertThat(new DeliveryMethodId()).isAValidEntityId();
	}
}