package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.CONFIRMED;
import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.NEW;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_OFFER;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_USER_ID;
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
	public void shouldReturnTheIdOfTheUserThatMadeThePurchase() {
		assertThat(purchase.userId()).isSameAs(DUMMY_USER_ID);
	}

	@Test
	public void shouldReturnTheSetOfPurchasedProducts() {
		assertThat(purchase.products()).containsExactlyElementsOf(DUMMY_OFFER.products());
	}
}