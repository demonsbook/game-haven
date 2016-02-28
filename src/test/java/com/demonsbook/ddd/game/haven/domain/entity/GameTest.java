package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.assertions.EntityAssert;
import com.demonsbook.ddd.game.haven.domain.util.Money;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Currency;

public class GameTest {

	private Game game = new Game();

	@Test
	public void shouldBeAValidEntity() {
		EntityAssert.assertThat(game).isAValidEntity();
	}

	@Test
	public void shouldHaveAPrice() {
		Money price = new Money(10, Currency.getInstance("PLN"));

		game.setPriceTo(price);

		Assertions.assertThat(game.price()).isEqualTo(price);
	}

	@Test
	public void shouldNotHaveAPhysicalVersionByDefault() {
		Assertions.assertThat(game.hasAPhysicalVersion()).isFalse();
	}

	@Test
	public void shouldBeAllowedToHaveAPhysicalVersion() {
		game.physicalVersionIsAvailable();

		Assertions.assertThat(game.hasAPhysicalVersion()).isTrue();
	}

	@Test
	public void shouldBeAllowedToNoLongerHaveAPhysicalVersion() {
		game.physicalVersionIsAvailable();

		game.physicalVersionIsNotAvailable();

		Assertions.assertThat(game.hasAPhysicalVersion()).isFalse();
	}
}
