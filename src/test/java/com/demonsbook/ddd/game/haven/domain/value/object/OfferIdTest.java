package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityIdAssert;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import org.junit.Test;

public class OfferIdTest {

	@Test
	public void shouldBeAValidEntityId() {
		EntityIdAssert.assertThat(new OfferId()).isAValidEntityId();
	}

}