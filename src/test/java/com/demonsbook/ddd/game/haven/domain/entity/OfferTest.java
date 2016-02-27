package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.ACCEPTED;
import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.DISCARDED;
import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.NEW;
import static org.assertj.core.api.Assertions.assertThat;

public class OfferTest {

	private Offer offer = new Offer();

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
}