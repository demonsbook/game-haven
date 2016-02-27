package com.demonsbook.ddd.game.haven.infrastructure;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryOfferRepository implements OfferRepository {

	private Map<OfferId, Offer> offers = new HashMap<>();

	@Override
	public Offer getForId(OfferId id) {
		return offers.get(id);
	}

	@Override
	public void save(Offer offer) {
		offers.put(offer.id(), offer);
	}
}
