package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseSearchCriteria;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toSet;

@Repository
class InMemoryPurchaseRepository extends InMemoryRepository<Purchase, PurchaseId> implements PurchaseRepository {

	@Override
	public Collection<Purchase> getAllMatching(PurchaseSearchCriteria criteria) {
		return getAll().stream().filter(criteriaFilterFor(criteria)).collect(toSet());
	}

	private Predicate<Purchase> criteriaFilterFor(PurchaseSearchCriteria criteria) {
		return criteria.getUserId()
				.map(userId -> (Predicate<Purchase>) purchase -> purchase.userId().equals(userId))
				.orElse(purchase -> true);
	}
}
