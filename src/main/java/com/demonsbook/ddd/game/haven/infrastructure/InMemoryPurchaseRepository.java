package com.demonsbook.ddd.game.haven.infrastructure;

import com.demonsbook.ddd.game.haven.application.services.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
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
