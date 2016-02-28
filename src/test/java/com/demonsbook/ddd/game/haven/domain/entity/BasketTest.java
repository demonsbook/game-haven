package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyInTheBasketException;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRODUCT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BasketTest {

	private Basket basket = new Basket();

	@Test
	public void isAValidEntity() {
		EntityAssert.assertThat(basket).isAValidEntity();
	}

	@Test
	public void shouldNotAllowToAddTheSameProductToTheBasketMoreThanOnce() {
		basket.add(DUMMY_PRODUCT);

		assertThatThrownBy(() -> basket.add(DUMMY_PRODUCT))
				.isInstanceOf(ProductAlreadyInTheBasketException.class);
	}
}