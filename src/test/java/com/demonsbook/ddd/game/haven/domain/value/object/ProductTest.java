package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_GAME_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_CLIENT_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

	private Product product = new Product(DUMMY_CLIENT_ID, DUMMY_GAME_ID, DIGITAL);

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(product).isAValidValueObject();
	}

	@Test
	public void shouldReturnTheIdOfClientForWhichTheGameIsBought() {
		assertThat(product.clientId()).isSameAs(DUMMY_CLIENT_ID);
	}

	@Test
	public void shouldReturnTheGameIdOfTheBoughtGame() {
		assertThat(product.gameId()).isSameAs(DUMMY_GAME_ID);
	}
}