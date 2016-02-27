package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class OfferDetailsTest {

	private static final OfferId OFFER_ID = new OfferId();

	private OfferDetails offerDetails = new OfferDetails(OFFER_ID);

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(offerDetails).isAValidValueObject();
	}

	@Test
	public void shouldReturnOffersId() {
		Assertions.assertThat(offerDetails.offerId()).isSameAs(OFFER_ID);
	}
}