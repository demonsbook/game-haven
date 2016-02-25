package com.demonsbook.ddd.game.haven.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class OfferFactoryTest {

	private final static User USER = new User();

	private OfferFactory offerFactory = new OfferFactory();

	@Test
	public void shouldGenerateOfferForUser() {
		Offer offer = offerFactory.createFor(USER);

		Assertions.assertThat(offer).isNotNull();
	}

}