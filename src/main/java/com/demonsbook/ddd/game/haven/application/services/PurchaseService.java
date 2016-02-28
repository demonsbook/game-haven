package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.event.publisher.PurchaseCompleted;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.factory.ProductFactory;
import com.demonsbook.ddd.game.haven.domain.factory.PurchaseFactory;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

	@Autowired private ProductFactory productFactory;
	@Autowired private OfferFactory offerFactory;
	@Autowired private PurchaseFactory purchaseFactory;
	@Autowired private UserRepository userRepository;
	@Autowired private GameRepository gameRepository;
	@Autowired private OfferRepository offerRepository;
	@Autowired private PurchaseRepository purchaseRepository;
	@Autowired private DomainEventPublisher eventPublisher;

	public Product getProduct(GameId gameId, UserId userId) throws ProductAlreadyPurchasedException {
		User user = userRepository.getForId(userId);
		Game game = gameRepository.getForId(gameId);
		return productFactory.createFor(user, game);
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
		offer.accept();
		Purchase purchase = purchaseFactory.createFor(offer);
		purchaseRepository.save(purchase);
		return purchase.getDetails();
	}

	public void confirmPurchase(PurchaseId purchaseId) {
		Purchase purchase = purchaseRepository.getForId(purchaseId);
		purchase.confirm();
		eventPublisher.publish(new PurchaseCompleted(purchaseId));
	}

	public UserDetails getUserDetails(UserId userId) {
		User user = userRepository.getForId(userId);
		return user.details();
	}
}
