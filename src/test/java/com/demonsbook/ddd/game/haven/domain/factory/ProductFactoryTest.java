package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.exception.PhysicalVersionNotAvaliableExeption;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL;
import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL_AND_PHYSICAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductFactoryTest {

	private User user = new User();
	private Game game = new Game();

	@Mock private UserRepository userRepository;
	@Mock private GameRepository gameRepository;
	@InjectMocks private ProductFactory productFactory;

	@Before
	public void init() {
		given(userRepository.getForId(user.id())).willReturn(user);
		given(gameRepository.getForId(game.id())).willReturn(game);
	}

	@Test
	public void shouldCreateAProductFromGameForUser() throws ProductAlreadyPurchasedException {
		Product product = productFactory.createFor(user.id(), game.id(), DIGITAL);

		assertThat(product).isNotNull();
		assertThat(product.userId()).isSameAs(user.id());
		assertThat(product.gameId()).isSameAs(game.id());
	}

	@Test
	public void shouldThrowAnExceptionIfTargetGameDoesNotHaveARequiredPhysicalCopyAvailable() {
		game.physicalVersionIsNotAvailable();

		assertThatThrownBy(() -> productFactory.createFor(user.id(), game.id(), DIGITAL_AND_PHYSICAL))
				.isInstanceOf(PhysicalVersionNotAvaliableExeption.class)
				.hasMessage(String.format("Physical copy not available for game %s", game.id()));
	}

	@Test
	public void shouldThrowAnExceptionIfTargetUserAlreadyHaveTheProduct() {
		user.addGameToLibrary(game.id());

		assertThatThrownBy(() -> productFactory.createFor(user.id(), game.id(), DIGITAL))
				.isInstanceOf(ProductAlreadyPurchasedException.class)
				.hasMessage(String.format("User %s already has the game %s in his library", user.id(), game.id()));
	}

}