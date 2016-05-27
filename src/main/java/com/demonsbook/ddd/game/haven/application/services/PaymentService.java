package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;

public interface PaymentService {
	void requestPaymentFor(PurchaseId purchaseId);

	boolean isPaymentOpen(PurchaseId purchaseId);

	void payFor(PurchaseId purchaseId);
}
