package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.repository.OfferSearchCriteria;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toSet;

@Repository
class InMemoryOfferRepository extends InMemoryRepository<Offer, OfferId> implements OfferRepository {
	@Override
	public Collection<Offer> getAllMatching(OfferSearchCriteria criteria) {
		return getAll().stream().filter(criteriaFilterFor(criteria)).collect(toSet());
	}

	private Predicate<Offer> criteriaFilterFor(OfferSearchCriteria criteria) {
		return criteria.getUserId()
				.map(userId -> (Predicate<Offer>) offer -> offer.userId().equals(userId))
				.orElse(offer -> true);
	}
}
