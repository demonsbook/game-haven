package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.BasketDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {

	private Client client = new Client();
	private Game game = new Game();

	@Test
	public void isAValidEntity() {
		EntityAssert.assertThat(client).isAValidEntity();
	}

	@Test
	public void shouldTellIfHeDoesNotOwnAGame() {
		assertThat(client.owns(new Game().id())).isFalse();
	}

	@Test
	public void shouldAddGamesToHisLibrary() {
		client.addGameToLibrary(game.id());

		assertThat(client.owns(game.id())).isTrue();
	}

	@Test
	public void shouldHaveAnEmptyBasketWhenNothingWasAddedToIt() {
		BasketDetails basketDetails = client.getBasketDetails();

		assertThat(basketDetails.getProducts()).isEmpty();
	}

	@Test
	public void shouldAddProductsToTheBasket() {
		Product product = new Product(client.id(), game.id(), DIGITAL);
		client.addToBasket(product);

		BasketDetails basketDetails = client.getBasketDetails();

		assertThat(basketDetails.getProducts()).containsOnly(product);
	}

}