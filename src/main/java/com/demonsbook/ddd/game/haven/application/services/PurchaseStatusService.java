package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEvent;
import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventListener;
import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.event.publisher.PurchaseCompleted;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PurchaseStatusService implements DomainEventListener {

	@Autowired private UserRepository userRepository;
	@Autowired private PurchaseRepository purchaseRepository;
	@Autowired private DomainEventPublisher eventPublisher;

	@PostConstruct
	public void subscribeToTrigger() {
		eventPublisher.subscribeTo(this::purchaseCompletedEventPredicate, this);
	}

	@Override
	public void handle(DomainEvent event) {
		if (purchaseCompletedEventPredicate(event)) {
			handle((PurchaseCompleted) event);
		}
	}

	private void handle(PurchaseCompleted event) {
		Purchase purchase = purchaseRepository.getForId(event.purchaseId());
		purchase.products().forEach(this::realisePurchaseOf);
	}

	private void realisePurchaseOf(Product product) {
		User user = userRepository.getForId(product.userId());
		user.addGameToLibrary(product.gameId());
	}

	private boolean purchaseCompletedEventPredicate(DomainEvent event) {
		return event instanceof PurchaseCompleted;
	}


}
