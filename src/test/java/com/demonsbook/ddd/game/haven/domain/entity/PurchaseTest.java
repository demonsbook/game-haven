package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.CONFIRMED;
import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.NEW;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_OFFER;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_CLIENT_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseTest {

	private Purchase purchase = new Purchase(DUMMY_OFFER);

	@Test
	public void shouldBeAValidEntity() {
		EntityAssert.assertThat(purchase).isAValidEntity();
	}

	@Test
	public void shouldBeInStatusNewAfterCreation() {
		assertThat(purchase.status()).isSameAs(NEW);
	}

	@Test
	public void shouldBeInConfirmedStatusWhenConfirmed() {
		purchase.confirm();

		assertThat(purchase.status()).isSameAs(CONFIRMED);
	}

	@Test
	public void shouldReturnTheIdOfTheClientThatMadeThePurchase() {
		assertThat(purchase.clientId()).isSameAs(DUMMY_CLIENT_ID);
	}

	@Test
	public void shouldReturnTheSetOfPurchasedProducts() {
		assertThat(purchase.products()).containsExactlyElementsOf(DUMMY_OFFER.products());
	}
}