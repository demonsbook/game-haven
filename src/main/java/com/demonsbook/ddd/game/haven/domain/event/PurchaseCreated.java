package com.demonsbook.ddd.game.haven.domain.event;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEvent;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseId;

public final class PurchaseCreated implements DomainEvent {
	private final PurchaseId purchaseId;

	public PurchaseCreated(PurchaseId purchaseId) {
		this.purchaseId = purchaseId;
	}

	public PurchaseId purchaseId() {
		return purchaseId;
	}
}
