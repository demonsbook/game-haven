package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PurchaseDetailsTest {

	private static final PurchaseId PURCHASE_ID = new PurchaseId();

	private PurchaseDetails purchaseDetails = new PurchaseDetails(PURCHASE_ID);

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(purchaseDetails).isAValidValueObject();
	}

	@Test
	public void shouldReturnOffersId() {
		Assertions.assertThat(purchaseDetails.purchaseId()).isSameAs(PURCHASE_ID);
	}

}