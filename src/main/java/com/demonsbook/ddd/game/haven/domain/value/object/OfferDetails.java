package com.demonsbook.ddd.game.haven.domain.value.object;

public final class OfferDetails {
	private final OfferId offerId;

	public OfferDetails(OfferId offerId) {
		this.offerId = offerId;
	}

	public OfferId offerId() {
		return offerId;
	}
}
