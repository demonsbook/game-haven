package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.event.OfferAccepted;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.BasketDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

	@Autowired private OfferFactory offerFactory;
	@Autowired private OfferRepository offerRepository;
	@Autowired private DomainEventPublisher eventPublisher;

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

}
