package com.demonsbook.ddd.game.haven.domain.value.object;

import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;

public final class PurchaseDetails {
	private final PurchaseId purchaseId;

	public PurchaseDetails(PurchaseId purchaseId) {
		this.purchaseId = purchaseId;
	}

	public PurchaseId purchaseId() {
		return purchaseId;
	}
}
