package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityIdAssert;
import org.junit.Test;

import static org.junit.Assert.*;

public class OfferIdTest {

	@Test
	public void shouldBeAValidEntityId() {
		EntityIdAssert.assertThat(new OfferId()).isAValidEntityId();
	}

}