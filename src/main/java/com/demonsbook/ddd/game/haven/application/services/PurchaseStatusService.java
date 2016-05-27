package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEvent;
import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventListener;
import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.event.PaymentCompleted;
import com.demonsbook.ddd.game.haven.domain.event.PurchaseCompleted;
import com.demonsbook.ddd.game.haven.domain.event.PurchaseCreated;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PurchaseStatusService extends DomainEventListener {

	@Autowired private UserRepository userRepository;
	@Autowired private PurchaseRepository purchaseRepository;
	@Autowired private PurchaseService purchaseService;
	@Autowired private PaymentService paymentService;
	@Autowired private DomainEventPublisher eventPublisher;

	@PostConstruct
	public void subscribeToTrigger() {
		subscribeTo(this::purchaseCreatedEventPredicate, this::requestPayment);
		subscribeTo(this::paymentCompletedEventPredicate, this::completePurchase);
		subscribeTo(this::purchaseCompletedEventPredicate, this::finalizePurchase);
	}

	private void completePurchase(DomainEvent event) {
		purchaseService.confirmPurchase(((PaymentCompleted) event).purchaseId());
	}

	private void requestPayment(DomainEvent event) {
		paymentService.requestPaymentFor(((PurchaseCreated) event).purchaseId());
	}

	private void finalizePurchase(DomainEvent event) {
		Purchase purchase = purchaseRepository.getForId(((PurchaseCompleted) event).purchaseId());
		purchase.products().forEach(this::realisePurchaseOf);
	}

	private void realisePurchaseOf(Product product) {
		User user = userRepository.getForId(product.userId());
		user.addGameToLibrary(product.gameId());
	}

	private boolean purchaseCreatedEventPredicate(DomainEvent event) {
		return event instanceof PurchaseCreated;
	}

	private boolean purchaseCompletedEventPredicate(DomainEvent event) {
		return event instanceof PurchaseCompleted;
	}

	private boolean paymentCompletedEventPredicate(DomainEvent event) {
		return event instanceof PaymentCompleted;
	}

}
