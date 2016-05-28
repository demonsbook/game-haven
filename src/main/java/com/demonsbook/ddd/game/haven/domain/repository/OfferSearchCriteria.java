package com.demonsbook.ddd.game.haven.domain.repository;

import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Optional;

public class OfferSearchCriteria {
	private ClientId clientId;

	private OfferSearchCriteria(ClientId clientId) {
		this.clientId = clientId;
	}

	public Optional<ClientId> getClientId() {
		return Optional.ofNullable(clientId);
	}

	public static OfferSearchCriteria.Builder anOfferSearchCriteria() {
		return new OfferSearchCriteria.Builder();
	}

	public static class Builder {
		private ClientId clientId;

		public Builder forClient(ClientId clientId) {
			this.clientId = clientId;
			return this;
		}

		public OfferSearchCriteria build() {
			return new OfferSearchCriteria(clientId);
		}
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other == null || getClass() != other.getClass()) {
			return false;
		}

		OfferSearchCriteria that = (OfferSearchCriteria) other;

		return new EqualsBuilder()
				.append(clientId, that.clientId)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(clientId)
				.toHashCode();
	}
}
