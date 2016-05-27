package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Aggregate;
import com.demonsbook.ddd.game.haven.domain.util.Money;
import com.demonsbook.ddd.game.haven.domain.value.object.GameId;

import java.util.Currency;

import static java.math.BigDecimal.ZERO;

public class Game extends Aggregate<GameId> {

	private Money price = new Money(ZERO, Currency.getInstance("PLN"));
	private boolean hasAPhysicalVersion = false;

	public Game() {
		super(new GameId());
	}

	void setPriceTo(Money price) {
		this.price = price;
	}

	public void physicalVersionIsAvailable() {
		this.hasAPhysicalVersion = true;
	}

	public void physicalVersionIsNotAvailable() {
		this.hasAPhysicalVersion = false;
	}

	public boolean hasAPhysicalVersion() {
		return hasAPhysicalVersion;
	}

	Money price() {
		return price;
	}
}
