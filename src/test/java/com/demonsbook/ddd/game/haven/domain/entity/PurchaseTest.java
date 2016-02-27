package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.junit.Test;

public class PurchaseTest {

	private Purchase purchase = new Purchase();

	@Test
	public void shouldBeAValidEntity() {
		EntityAssert.assertThat(purchase).isAValidEntity();
	}


}