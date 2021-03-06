package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityIdAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseId;
import org.junit.Test;

public class PurchaseIdTest {

	@Test
	public void shouldBeAValidEntityId() {
		EntityIdAssert.assertThat(new PurchaseId()).isAValidEntityId();
	}

}