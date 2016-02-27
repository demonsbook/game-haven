package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.factory.ProductFactory;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

	@Autowired private ProductFactory productFactory;
	@Autowired private OfferFactory offerFactory;
	@Autowired private UserRepository userRepository;
	@Autowired private GameRepository gameRepository;
	@Autowired private OfferRepository offerRepository;

	public Product getProduct(GameId gameId, UserId userId) {
		User user = userRepository.getForId(userId);
		Game game = gameRepository.getForId(gameId);
		return productFactory.createFor(game, user);
	}

	public void addToUsersBasket(UserId userId, Product product) {
		User user = userRepository.getForId(userId);
		user.addToBasket(product);
	}

	public OfferDetails generateOfferFor(UserId userId) {
		User user = userRepository.getForId(userId);
		Offer offer = offerFactory.createFor(user);
		offerRepository.save(offer);
		return offer.getDetails();
	}

	public PurchaseDetails acceptOffer(OfferId offerId) {
		Offer offer = offerRepository.getForId(offerId);
		return offer.accept().getDetails();
	}
}
