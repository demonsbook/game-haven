package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryPurchaseRepository implements PurchaseRepository {

	private Map<PurchaseId, Purchase> purchases = new HashMap<>();

	@Override
	public Purchase getForId(PurchaseId id) {
		return purchases.get(id);
	}

	@Override
	public void save(Purchase purchase) {
		purchases.put(purchase.id(), purchase);
	}
}
