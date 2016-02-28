package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.exception.PhysicalVersionNotAvaliableExeption;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL;
import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL_AND_PHYSICAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductFactoryTest {

	private ProductFactory productFactory = new ProductFactory();

	@Test
	public void shouldCreateAProductFromGameForUser() throws ProductAlreadyPurchasedException {
		User user = new User();
		Game game = new Game();

		Product product = productFactory.createFor(user, game, DIGITAL);

		assertThat(product).isNotNull();
		assertThat(product.userId()).isSameAs(user.id());
		assertThat(product.gameId()).isSameAs(game.id());
	}

	@Test
	public void shouldThrowAnExceptionIfTargetGameDoesNotHaveARequiredPhysicalCopyAvailable() {
		User user = new User();
		Game game = new Game();
		game.physicalVersionIsNotAvailable();

		assertThatThrownBy(() -> productFactory.createFor(user, game, DIGITAL_AND_PHYSICAL))
				.isInstanceOf(PhysicalVersionNotAvaliableExeption.class)
				.hasMessage(String.format("Physical copy not available for game %s", game.id()));
	}

	@Test
	public void shouldThrowAnExceptionIfTargetUserAlreadyHaveTheProduct() {
		User user = new User();
		Game game = new Game();
		user.addGameToLibrary(game.id());

		assertThatThrownBy(() -> productFactory.createFor(user, game, DIGITAL))
				.isInstanceOf(ProductAlreadyPurchasedException.class)
				.hasMessage(String.format("User %s already has the game %s in his library", user.id(), game.id()));
	}

}