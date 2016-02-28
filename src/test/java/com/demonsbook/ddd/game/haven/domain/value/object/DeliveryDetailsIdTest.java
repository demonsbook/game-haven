package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import org.junit.Test;

import java.util.UUID;

public class DeliveryDetailsIdTest {

	private DeliveryDetailsId deliveryDetailsId = new DeliveryDetailsId(UUID.randomUUID());

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(deliveryDetailsId).isAValidValueObject();
	}
}