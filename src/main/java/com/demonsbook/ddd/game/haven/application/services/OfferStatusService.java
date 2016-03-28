package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEvent;
import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventListener;
import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.event.OfferAccepted;
import com.demonsbook.ddd.game.haven.domain.factory.PurchaseFactory;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class OfferStatusService extends DomainEventListener {

	@Autowired private UserRepository userRepository;
	@Autowired private OfferRepository offerRepository;
	@Autowired private DomainEventPublisher eventPublisher;
	@Autowired private PurchaseFactory purchaseFactory;
	@Autowired private PurchaseRepository purchaseRepository;

	@PostConstruct
	void subscribeToEvent() {
		subscribeTo(this::offerAcceptedEventPredicate, this::handleEvent);
	}

	private void handleEvent(DomainEvent event) {
		Offer offer = offerRepository.getForId(((OfferAccepted) event).offerId());

		Purchase purchase = purchaseFactory.createFor(offer);
		purchaseRepository.save(purchase);
	}

	private boolean offerAcceptedEventPredicate(DomainEvent event) {
		return event instanceof OfferAccepted;
	}

}
