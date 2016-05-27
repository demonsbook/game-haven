package com.demonsbook.ddd.game.haven.domain.value.object;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class OfferDetails {
	private final OfferId offerId;

	public OfferDetails(OfferId offerId) {
		this.offerId = offerId;
	}

	public OfferId offerId() {
		return offerId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other == null || getClass() != other.getClass()) {
			return false;
		}

		OfferDetails that = (OfferDetails) other;

		return new EqualsBuilder()
				.append(offerId, that.offerId)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(offerId)
				.toHashCode();
	}
}
