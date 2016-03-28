package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Game;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.event.OfferAccepted;
import com.demonsbook.ddd.game.haven.domain.event.PurchaseCompleted;
import com.demonsbook.ddd.game.haven.domain.exception.ProductAlreadyPurchasedException;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.factory.ProductFactory;
import com.demonsbook.ddd.game.haven.domain.factory.PurchaseFactory;
import com.demonsbook.ddd.game.haven.domain.repository.GameRepository;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.BasketDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

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

	public Product getProduct(GameId gameId, UserId userId, Product.Version version) throws ProductAlreadyPurchasedException {
		User user = userRepository.getForId(userId);
		Game game = gameRepository.getForId(gameId);
		return productFactory.createFor(user, game, version);
	}

	public void addToUsersBasket(UserId userId, Product product) {
		User user = userRepository.getForId(userId);
		user.addToBasket(product);
	}

	public BasketDetails getUserBasketDetails(UserId userId) {
		User user = userRepository.getForId(userId);
		return user.getBasketDetails();
	}

	public OfferDetails generateOfferFor(BasketDetails basketDetails, DeliveryMethodId deliveryMethodId, PaymentMethodId paymentMethodId) {
		Offer offer = offerFactory.createFor(basketDetails, deliveryMethodId, paymentMethodId);
		offerRepository.save(offer);
		return offer.getDetails();
	}

	public void acceptOffer(OfferId offerId) {
		Offer offer = offerRepository.getForId(offerId);
		offer.accept();
		eventPublisher.publish(new OfferAccepted(offerId));
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

	public Collection<PurchaseDetails> getPurchasesOfUser(UserId userId) {
		Collection<Purchase> purchases = purchaseRepository.getAll();
		return purchases.stream().filter(purchase -> purchase.userId().equals(userId)).map(Purchase::getDetails).collect(toList());
	}
}
