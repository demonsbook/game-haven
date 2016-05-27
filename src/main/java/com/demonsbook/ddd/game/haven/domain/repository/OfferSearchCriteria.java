package com.demonsbook.ddd.game.haven.domain.repository;

import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Optional;

public class OfferSearchCriteria {
	private UserId userId;

	private OfferSearchCriteria(UserId userId) {
		this.userId = userId;
	}

	public Optional<UserId> getUserId() {
		return Optional.ofNullable(userId);
	}

	public static OfferSearchCriteria.Builder anOfferSearchCriteria() {
		return new OfferSearchCriteria.Builder();
	}

	public static class Builder {
		private UserId userId;

		public Builder forUser(UserId userId) {
			this.userId = userId;
			return this;
		}

		public OfferSearchCriteria build() {
			return new OfferSearchCriteria(userId);
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
				.append(userId, that.userId)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(userId)
				.toHashCode();
	}
}
