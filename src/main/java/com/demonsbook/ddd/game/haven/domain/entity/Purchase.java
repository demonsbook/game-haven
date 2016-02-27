package com.demonsbook.ddd.game.haven.domain.entity;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;

import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.CONFIRMED;
import static com.demonsbook.ddd.game.haven.domain.entity.Purchase.Status.NEW;

public class Purchase extends Entity<PurchaseId> {

	private Status status = NEW;

	public Purchase() {
		super(new PurchaseId());
	}

	protected Purchase(PurchaseId id) {
		super(id);
	}

	public PurchaseDetails getDetails() {
		return new PurchaseDetails(id);
	}

	public void confirm() {
		status = CONFIRMED;
	}

	public Status status() {
		return status;
	}

	public enum Status {
		NEW, CONFIRMED
	}
}
