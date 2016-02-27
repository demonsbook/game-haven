package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;

public class Purchase extends Entity<PurchaseId> {

	public Purchase() {
		super(new PurchaseId());
	}

	protected Purchase(PurchaseId id) {
		super(id);
	}

	public PurchaseDetails getDetails() {
		return new PurchaseDetails();
	}
}
