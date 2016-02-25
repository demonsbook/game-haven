package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class OfferTest {

	private Offer offer = new Offer();

	@Test
	public void shouldBeAValidEntity() {
		EntityAssert.assertThat(offer).isAValidEntity();
	}

	@Test
	public void shouldGenerateOfferDetails() {
		OfferDetails details = offer.getDetails();

		Assertions.assertThat(details).isNotNull();
	}
}