package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.value.object.BasketDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

	private User user = new User();
	private Game game = new Game();

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
		user.addGameToLibrary(game.id());

		assertThat(user.owns(game.id())).isTrue();
	}

	@Test
	public void shouldHaveAnEmptyBasketWhenNothingWasAddedToIt() {
		BasketDetails basketDetails = user.getBasketDetails();

		assertThat(basketDetails.getProducts()).isEmpty();
	}

	@Test
	public void shouldAddProductsToTheBasket() {
		Product product = new Product(user.id(), game.id());
		user.addToBasket(product);

		BasketDetails basketDetails = user.getBasketDetails();

		assertThat(basketDetails.getProducts()).containsOnly(product);
	}

}