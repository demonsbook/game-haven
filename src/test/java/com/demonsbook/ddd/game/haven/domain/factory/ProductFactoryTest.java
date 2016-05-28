package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.Client;
import com.demonsbook.ddd.game.haven.domain.exception.PhysicalVersionNotAvaliableExeption;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.ClientRepository;
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

	private Client client = new Client();
	private Game game = new Game();

	@Mock private ClientRepository clientRepository;
	@Mock private GameRepository gameRepository;
	@InjectMocks private ProductFactory productFactory;

	@Before
	public void init() {
		given(clientRepository.getForId(client.id())).willReturn(client);
		given(gameRepository.getForId(game.id())).willReturn(game);
	}

	@Test
	public void shouldCreateAProductFromGameForClient() throws ProductAlreadyPurchasedException {
		Product product = productFactory.aProduct().forClient(client.id()).forGame(game.id()).inVersion(DIGITAL).create();

		assertThat(product).isNotNull();
		assertThat(product.clientId()).isSameAs(client.id());
		assertThat(product.gameId()).isSameAs(game.id());
	}

	@Test
	public void shouldThrowAnExceptionIfTargetGameDoesNotHaveARequiredPhysicalCopyAvailable() {
		game.physicalVersionIsNotAvailable();

		assertThatThrownBy(() -> productFactory.aProduct().forClient(client.id()).forGame(game.id()).inVersion(DIGITAL_AND_PHYSICAL).create())
				.isInstanceOf(PhysicalVersionNotAvaliableExeption.class)
				.hasMessage(String.format("Physical copy not available for game %s", game.id()));
	}

	@Test
	public void shouldThrowAnExceptionIfTargetClientAlreadyHaveTheProduct() {
		client.addGameToLibrary(game.id());

		assertThatThrownBy(() -> productFactory.aProduct().forClient(client.id()).forGame(game.id()).inVersion(DIGITAL).create())
				.isInstanceOf(ProductAlreadyPurchasedException.class)
				.hasMessage(String.format("Client %s already has the game %s in his library", client.id(), game.id()));
	}

	@Test
	public void shouldThrowAnExceptionIfNoGameIsSpecified() {
		client.addGameToLibrary(game.id());

		assertThatThrownBy(() -> productFactory.aProduct().forClient(client.id()).inVersion(DIGITAL).create())
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Creation of Product without a proper GameId is not allowed");
	}

	@Test
	public void shouldThrowAnExceptionIfNoTargetClientIsSpecified() {
		client.addGameToLibrary(game.id());

		assertThatThrownBy(() -> productFactory.aProduct().forGame(game.id()).inVersion(DIGITAL).create())
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Creation of Product without a proper ClientId is not allowed");
	}

	@Test
	public void shouldThrowAnExceptionIfNoProductVersionIsSpecified() {
		client.addGameToLibrary(game.id());

		assertThatThrownBy(() -> productFactory.aProduct().forClient(client.id()).forGame(game.id()).create())
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Creation of Product without a proper Version selected is not allowed");
	}

}