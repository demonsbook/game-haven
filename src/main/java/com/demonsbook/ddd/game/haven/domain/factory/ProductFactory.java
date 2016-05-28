package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Client;
import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.exception.PhysicalVersionNotAvaliableExeption;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.repository.ClientRepository;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.demonsbook.ddd.game.haven.domain.value.object.Product.Version.DIGITAL_AND_PHYSICAL;

@Component
public class ProductFactory {

	@Autowired private ClientRepository clientRepository;
	@Autowired private GameRepository gameRepository;

	private Product createFor(ClientId clientId, GameId gameId, Product.Version version) throws ProductAlreadyPurchasedException {
		Client client = clientRepository.getForId(clientId);
		Game game = gameRepository.getForId(gameId);

		return createFor(client, game, version);
	}

	private Product createFor(Client client, Game game, Product.Version version) throws ProductAlreadyPurchasedException {
		verifyProductHasNotBeenAlreadyPurchased(client, game);
		verifyRequiredVersionIsAvailable(game, version);
		return new Product(client.id(), game.id(), version);
	}

	private void verifyRequiredVersionIsAvailable(Game game, Product.Version version) {
		if (version == DIGITAL_AND_PHYSICAL && !game.hasAPhysicalVersion()) {
			throw new PhysicalVersionNotAvaliableExeption(game.id());
		}
	}

	private void verifyProductHasNotBeenAlreadyPurchased(Client client, Game game) throws ProductAlreadyPurchasedException {
		if (client.owns(game.id())) {
			throw new ProductAlreadyPurchasedException(client.id(), game.id());
		}
	}

	public ProductBuilder aProduct() {
		return new ProductBuilder();
	}

	public class ProductBuilder {
		private ClientId clientId;
		private GameId gameId;
		private Product.Version version;

		public ProductBuilder forGame(GameId gameId) {
			this.gameId = gameId;
			return this;
		}

		public ProductBuilder forClient(ClientId clientId) {
			this.clientId = clientId;
			return this;
		}

		public ProductBuilder inVersion(Product.Version version) {
			this.version = version;
			return this;
		}

		public Product create() throws ProductAlreadyPurchasedException {
			verifyAllRequiredArgumentsArePresent();
			return createFor(clientId, gameId, version);
		}

		private void verifyAllRequiredArgumentsArePresent() {
			if (gameId == null) {
				throw new IllegalStateException("Creation of Product without a proper GameId is not allowed");
			}
			if (clientId == null) {
				throw new IllegalStateException("Creation of Product without a proper ClientId is not allowed");
			}
			if (version == null) {
				throw new IllegalStateException("Creation of Product without a proper Version selected is not allowed");
			}
		}
	}
}
