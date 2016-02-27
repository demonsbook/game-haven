package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collections;

import static java.util.Collections.emptySet;

public class InMemoryPurchaseRepositoryTest {

	private Offer offer = new Offer(new UserId(), emptySet());
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