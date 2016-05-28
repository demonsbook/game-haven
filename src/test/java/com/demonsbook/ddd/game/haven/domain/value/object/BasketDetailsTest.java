package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRODUCTS;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_CLIENT_ID;

public class BasketDetailsTest {

	private BasketDetails basket = new BasketDetails(DUMMY_PRODUCTS, DUMMY_CLIENT_ID);

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(basket).isAValidValueObject();
	}

	@Test
	public void shouldReturnStoredProducts() {
		Assertions.assertThat(basket.getProducts()).containsExactlyElementsOf(DUMMY_PRODUCTS);
	}

	@Test
	public void shouldReturnOwnerId() {
		Assertions.assertThat(basket.getClientId()).isEqualTo(DUMMY_CLIENT_ID);
	}

}