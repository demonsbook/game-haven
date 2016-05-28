package com.demonsbook.ddd.game.haven.domain.value.object;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Set;

public final class BasketDetails {
	private final ClientId clientId;
	private final Set<Product> products;

	public BasketDetails(Set<Product> products, ClientId clientId) {
		this.products = ImmutableSet.copyOf(products);
		this.clientId = clientId;
	}

	public ClientId getClientId() {
		return clientId;
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
				.append(clientId, that.clientId)
				.append(products, that.products)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(clientId)
				.append(products)
				.toHashCode();
	}
}
