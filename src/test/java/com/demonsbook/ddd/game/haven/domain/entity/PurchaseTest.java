package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.CONFIRMED;
import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.NEW;
import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseTest {

	private static final UserId USER_ID = new UserId();
	private static final GameId GAME_ID = new GameId();
	private Product product = new Product(USER_ID, GAME_ID);

	private Offer offer = new Offer(USER_ID, ImmutableSet.of(product));
	private Purchase purchase = new Purchase(offer);

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
		assertThat(purchase.userId()).isSameAs(USER_ID);
	}

	@Test
	public void shouldReturnTheSetOfPurchasedProducts() {
		assertThat(purchase.products()).containsExactlyElementsOf(offer.products());
	}
}