package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.junit.Test;

public class ProductTest {

	private Product product = new Product();

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(product).isAValidValueObject();
	}
}