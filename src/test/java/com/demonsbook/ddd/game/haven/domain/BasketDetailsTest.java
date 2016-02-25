package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import com.google.common.collect.ImmutableSet;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Set;

public class BasketDetailsTest {

	private static final UserId USER_ID = new UserId();
	private static final Set<Product> PRODUCTS = ImmutableSet.of(new Product());
	private BasketDetails basket = new BasketDetails(PRODUCTS, USER_ID);

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
		Assertions.assertThat(basket.getUserId()).isEqualTo(USER_ID);
	}

}