package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.assertions.ValueObjectAssert;
import org.junit.Test;

public class OfferDetailsTest {

	private OfferDetails offerDetails = new OfferDetails();

	@Test
	public void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(offerDetails).isAValidValueObject();
	}
}