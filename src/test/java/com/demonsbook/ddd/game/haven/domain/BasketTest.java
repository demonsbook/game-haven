package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasketTest {

	private Basket basket = new Basket();

	@Test
	public void isAValidEntity() {
		EntityAssert.assertThat(basket).isAValidEntity();
	}

}