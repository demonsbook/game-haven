package com.demonsbook.ddd.game.haven.domain.value.object;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class PurchaseDetails {
	private final PurchaseId purchaseId;

	public PurchaseDetails(PurchaseId purchaseId) {
		this.purchaseId = purchaseId;
	}

	public PurchaseId purchaseId() {
		return purchaseId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other == null || getClass() != other.getClass()) {
			return false;
		}

		PurchaseDetails that = (PurchaseDetails) other;

		return new EqualsBuilder()
				.append(purchaseId, that.purchaseId)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(purchaseId)
				.toHashCode();
	}
}
