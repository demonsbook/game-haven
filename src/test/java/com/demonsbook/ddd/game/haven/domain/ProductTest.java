package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import org.junit.Test;

public class ProductTest {

	private Product product = new Product();

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(product).isAValidValueObject();
	}
}