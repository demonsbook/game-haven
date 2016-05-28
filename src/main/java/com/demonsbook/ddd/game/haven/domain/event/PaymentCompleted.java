package com.demonsbook.ddd.game.haven.domain.event;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEvent;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseId;

public class PaymentCompleted implements DomainEvent {
	private final PurchaseId purchaseId;

	public PaymentCompleted(PurchaseId purchaseId) {
		this.purchaseId = purchaseId;
	}

	public PurchaseId purchaseId() {
		return purchaseId;
	}
}
