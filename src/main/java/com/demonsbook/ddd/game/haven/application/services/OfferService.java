package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.event.OfferAccepted;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.repository.ClientRepository;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demonsbook.ddd.game.haven.domain.repository.OfferSearchCriteria.anOfferSearchCriteria;
import static java.util.stream.Collectors.toList;

@Service
public class OfferService {

	@Autowired private ClientRepository clientRepository;
	@Autowired private OfferFactory offerFactory;
	@Autowired private OfferRepository offerRepository;
	@Autowired private DomainEventPublisher eventPublisher;

	public void generateOfferFor(ClientId clientId, DeliveryMethodId deliveryMethodId, PaymentMethodId paymentMethodId) {
		Offer offer = offerFactory.createFor(clientId, deliveryMethodId, paymentMethodId);
		offerRepository.save(offer);
	}

	public List<OfferDetails> getOffersFor(ClientId clientId) {
		return offerRepository.getAllMatching(anOfferSearchCriteria().forClient(clientId).build())
				.stream().map(Offer::getDetails).collect(toList());
	}

	public void acceptOffer(OfferId offerId) {
		Offer offer = offerRepository.getForId(offerId);
		offer.accept();
		eventPublisher.publish(new OfferAccepted(offerId));
	}

}
