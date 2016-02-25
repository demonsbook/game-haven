package com.demonsbook.ddd.game.haven.domain.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.Currency;

public class Money {
	private BigDecimal amount;
	private Currency currency;

	public Money(BigDecimal amount, Currency currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public Money(long amount, Currency currency) {
		this(BigDecimal.valueOf(amount), currency);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other == null || getClass() != other.getClass()) {
			return false;
		}

		Money money = (Money) other;

		return new EqualsBuilder()
				.append(amount, money.amount)
				.append(currency, money.currency)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(amount)
				.append(currency)
				.toHashCode();
	}
}
