package com.demonsbook.ddd.game.haven.domain.event;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEvent;
import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;

public final class PurchaseCompleted implements DomainEvent {
	private final PurchaseId purchaseId;

	public PurchaseCompleted(PurchaseId purchaseId) {
		this.purchaseId = purchaseId;
	}

	public PurchaseId purchaseId() {
		return purchaseId;
	}
}
