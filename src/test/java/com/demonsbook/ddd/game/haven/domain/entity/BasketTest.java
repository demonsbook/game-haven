package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.junit.Test;

public class BasketTest {

	private Basket basket = new Basket();

	@Test
	public void isAValidEntity() {
		EntityAssert.assertThat(basket).isAValidEntity();
	}

}