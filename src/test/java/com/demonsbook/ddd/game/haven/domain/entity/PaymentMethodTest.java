package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.junit.Test;

public class PaymentMethodTest {

	@Test
	public void shouldBeAValidEntity() {
		EntityAssert.assertThat(new PaymentMethod()).isAValidEntity();
	}

}