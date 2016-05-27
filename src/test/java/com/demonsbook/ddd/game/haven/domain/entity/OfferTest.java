package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.ACCEPTED;
import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.DISCARDED;
import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.NEW;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_DELIVERY_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PAYMENT_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRICE;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRODUCT;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRODUCTS;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_USER_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class OfferTest {

	private Offer offer = new Offer(DUMMY_USER_ID, DUMMY_PRODUCTS, DUMMY_PRICE, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);

	@Test
	public void shouldBeAValidEntity() {
		EntityAssert.assertThat(offer).isAValidEntity();
	}

	@Test
	public void shouldGenerateOfferDetails() {
		OfferDetails details = offer.getDetails();

		assertThat(details).isNotNull();
	}

	@Test
	public void shouldBeInNewStateAfterGenerated() {
		assertThat(offer.status()).isSameAs(NEW);
	}

	@Test
	public void shouldSwitchStatusToAcceptedWhenAccepted() {
		offer.accept();

		assertThat(offer.status()).isSameAs(ACCEPTED);
	}

	@Test
	public void shouldSwitchStatusToDiscardedWhenDiscarded() {
		offer.discard();

		assertThat(offer.status()).isSameAs(DISCARDED);
	}

	@Test
	public void shouldReturnIdOfTargetUser() {
		assertThat(offer.userId()).isSameAs(DUMMY_USER_ID);
	}

	@Test
	public void shouldReturnTheCollectionsOfOfferedProducts() {
		assertThat(offer.products()).containsExactly(DUMMY_PRODUCT);
	}
}