package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import com.google.common.collect.ImmutableSet;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Set;

import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRODUCT;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_USER_ID;

public class BasketDetailsTest {

	private static final Set<Product> PRODUCTS = ImmutableSet.of(DUMMY_PRODUCT);
	private BasketDetails basket = new BasketDetails(PRODUCTS, DUMMY_USER_ID);

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(basket).isAValidValueObject();
	}

	@Test
	public void shouldReturnStoredProducts() {
		Assertions.assertThat(basket.getProducts()).containsExactlyElementsOf(PRODUCTS);
	}

	@Test
	public void shouldReturnOwnerId() {
		Assertions.assertThat(basket.getUserId()).isEqualTo(DUMMY_USER_ID);
	}

}