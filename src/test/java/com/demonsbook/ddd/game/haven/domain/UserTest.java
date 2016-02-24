package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

	private User user = new User();

	@Test
	public void isAValidEntity() {
		EntityAssert.assertThat(user).isAValidEntity();
	}

	@Test
	public void shouldTellIfHeDoesNotOwnAGame() {
		assertThat(user.owns(new Game().id())).isFalse();
	}

	@Test
	public void shouldAddGamesToHisLibrary() {
		Game game = new Game();

		user.addGameToLibrary(game);

		assertThat(user.owns(game.id())).isTrue();
	}

	@Test
	public void shouldHaveAnEmptyBasketWhenNothingWasAddedToIt() {
		BasketDetails basketDetails = user.getBasketDetails();

		assertThat(basketDetails.getProducts()).isEmpty();
	}

	@Test
	public void shouldAddProductsToTheBasker() {
		Product product = new Product();
		user.addToBasket(product);

		BasketDetails basketDetails = user.getBasketDetails();

		assertThat(basketDetails.getProducts()).containsOnly(product);
	}

}