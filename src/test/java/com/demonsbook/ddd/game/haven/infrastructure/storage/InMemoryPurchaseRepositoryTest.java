package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_DELIVERY_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PAYMENT_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_USER_ID;
import static java.util.Collections.emptySet;

public class InMemoryPurchaseRepositoryTest {

	private Offer offer = new Offer(DUMMY_USER_ID, emptySet(), DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);
	private PurchaseRepository purchaseRepository = new InMemoryPurchaseRepository();

	@Test
	public void shouldStoreMultiplePurchases() {
		Purchase purchase1 = new Purchase(offer);
		Purchase purchase2 = new Purchase(offer);

		purchaseRepository.save(purchase1);
		purchaseRepository.save(purchase2);

		Assertions.assertThat(purchaseRepository.getForId(purchase1.id())).isSameAs(purchase1);
		Assertions.assertThat(purchaseRepository.getForId(purchase2.id())).isSameAs(purchase2);
	}

	@Test
	public void shouldReturnNullForNonExistentPurchase() {
		Purchase purchase = new Purchase(offer);

		Assertions.assertThat(purchaseRepository.getForId(purchase.id())).isNull();
	}

}