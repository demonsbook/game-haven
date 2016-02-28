package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyInTheBasketException;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_GAME_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_USER_ID;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BasketTest {

	private Basket basket = new Basket();

	@Test
	public void isAValidEntity() {
		EntityAssert.assertThat(basket).isAValidEntity();
	}

	@Test
	public void shouldNotAllowToAddTheSameProductToTheBasketMoreThanOnce() {
		Product product = new Product(DUMMY_USER_ID, DUMMY_GAME_ID);
		basket.add(product);

		assertThatThrownBy(() -> basket.add(product))
				.isInstanceOf(ProductAlreadyInTheBasketException.class);
	}
}