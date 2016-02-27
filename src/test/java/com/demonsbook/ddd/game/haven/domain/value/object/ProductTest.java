package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

	private static final UserId USER_ID = new UserId();
	private static final GameId GAME_ID = new GameId();

	private Product product = new Product(USER_ID, GAME_ID);

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(product).isAValidValueObject();
	}

	@Test
	public void shouldReturnTheIdOfUserForWhichTheGameIsBought() {
		assertThat(product.userId()).isSameAs(USER_ID);
	}

	@Test
	public void shouldReturnTheGameIdOfTheBoughtGame() {
		assertThat(product.gameId()).isSameAs(GAME_ID);
	}
}