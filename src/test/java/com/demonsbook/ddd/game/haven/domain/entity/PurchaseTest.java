package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.CONFIRMED;
import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.NEW;
import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseTest {

	private Purchase purchase = new Purchase();

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
}