package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
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