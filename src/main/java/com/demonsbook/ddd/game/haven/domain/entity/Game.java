package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import com.demonsbook.ddd.game.haven.domain.util.Money;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;

import java.util.Currency;

import static java.math.BigDecimal.ZERO;

public class Game extends Entity<GameId> {

	private Money price = new Money(ZERO, Currency.getInstance("PLN"));

	public Game() {
		super(new GameId());
	}

	public void setPriceTo(Money price) {
		this.price = price;
	}

	public Money price() {
		return price;
	}
}
