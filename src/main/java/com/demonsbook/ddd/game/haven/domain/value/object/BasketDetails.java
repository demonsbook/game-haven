package com.demonsbook.ddd.game.haven.domain.value.object;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Set;

public final class BasketDetails {
	private final UserId userId;
	private final Set<Product> products;

	public BasketDetails(Set<Product> products, UserId userId) {
		this.products = ImmutableSet.copyOf(products);
		this.userId = userId;
	}

	public UserId getUserId() {
		return userId;
	}

	public Set<Product> getProducts() {
		return ImmutableSet.copyOf(products);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other == null || getClass() != other.getClass()) {
			return false;
		}

		BasketDetails that = (BasketDetails) other;

		return new EqualsBuilder()
				.append(userId, that.userId)
				.append(products, that.products)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(userId)
				.append(products)
				.toHashCode();
	}
}
