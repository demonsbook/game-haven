package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityIdAssert;
import org.junit.Test;

public class ProductIdTest {

	@Test
	public void shouldBeAValidEntityId() {
		EntityIdAssert.assertThat(new ProductId()).isAValidEntityId();
	}

}