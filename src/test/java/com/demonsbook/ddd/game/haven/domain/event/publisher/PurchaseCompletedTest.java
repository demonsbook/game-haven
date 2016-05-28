package com.demonsbook.ddd.game.haven.domain.event.publisher;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseId;
import com.demonsbook.ddd.game.haven.domain.event.PurchaseCompleted;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseCompletedTest {

	private static final PurchaseId PURCHASE_ID = new PurchaseId();

	private PurchaseCompleted purchaseCompleted = new PurchaseCompleted(PURCHASE_ID);

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(purchaseCompleted).isAValidValueObject();
	}

	@Test
	public void shouldCarryTheIdOfPurchaseThatHadCompleted() {
		assertThat(purchaseCompleted.purchaseId()).isSameAs(PURCHASE_ID);
	}

}