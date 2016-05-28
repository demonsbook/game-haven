package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static com.demonsbook.ddd.game.haven.domain.repository.PurchaseSearchCriteria.aPurchaseSearchCriteria;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_DELIVERY_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PAYMENT_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRICE;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PURCHASE;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_CLIENT_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryPurchaseRepositoryTest {

	private InMemoryPurchaseRepository repository = new InMemoryPurchaseRepository();

	@Test
	public void shouldReturnOnlyThePurchasesMatchingSearchCriteria() {
		repository.save(DUMMY_PURCHASE);
		repository.save(new Purchase(new Offer(new ClientId(), Collections.emptySet(), DUMMY_PRICE, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID)));

		Collection<Purchase> allMatching = repository.getAllMatching(aPurchaseSearchCriteria().forClient(DUMMY_CLIENT_ID).build());

		assertThat(allMatching).containsOnly(DUMMY_PURCHASE);
	}
}