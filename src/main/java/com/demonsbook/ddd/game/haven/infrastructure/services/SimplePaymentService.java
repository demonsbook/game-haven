package com.demonsbook.ddd.game.haven.infrastructure.services;

import com.demonsbook.ddd.game.haven.application.services.PaymentService;
import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.event.PaymentCompleted;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimplePaymentService implements PaymentService {

	@Autowired private DomainEventPublisher eventPublisher;

	private List<PurchaseId> openPayments = new ArrayList<>();

	@Override
	public void requestPaymentFor(PurchaseId purchaseId) {
		openPayments.add(purchaseId);
	}

	@Override
	public boolean isPaymentOpen(PurchaseId purchaseId) {
		return openPayments.contains(purchaseId);
	}

	@Override
	public void payFor(PurchaseId purchaseId) {
		openPayments.remove(purchaseId);
		eventPublisher.publish(new PaymentCompleted(purchaseId));
	}

}
