package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductFactoryTest {

	private ProductFactory productFactory = new ProductFactory();

	@Test
	public void shouldCreateAProductFromGameForUser() {
		User user = new User();
		Game game = new Game();

		Product product = productFactory.createFor(user, game);

		assertThat(product).isNotNull();
		assertThat(product.userId()).isSameAs(user.id());
		assertThat(product.gameId()).isSameAs(game.id());
	}

}