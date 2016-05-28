package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Client;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.event.PurchaseCompleted;
import com.demonsbook.ddd.game.haven.domain.repository.ClientRepository;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.PurchaseId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.demonsbook.ddd.game.haven.domain.repository.PurchaseSearchCriteria.aPurchaseSearchCriteria;
import static java.util.stream.Collectors.toList;

@Service
public class PurchaseService {

	@Autowired private ClientRepository clientRepository;
	@Autowired private PurchaseRepository purchaseRepository;
	@Autowired private DomainEventPublisher eventPublisher;

	public void addProductToClientsBasket(ClientId clientId, Product product) {
		Client client = clientRepository.getForId(clientId);
		client.addToBasket(product);
	}

	public void confirmPurchase(PurchaseId purchaseId) {
		Purchase purchase = purchaseRepository.getForId(purchaseId);
		purchase.confirm();
		eventPublisher.publish(new PurchaseCompleted(purchaseId));
	}

	public ClientDetails getClientDetails(ClientId clientId) {
		Client client = clientRepository.getForId(clientId);
		return client.details();
	}

	public Collection<PurchaseDetails> getPurchasesOfClient(ClientId clientId) {
		Collection<Purchase> purchases = purchaseRepository.getAllMatching(aPurchaseSearchCriteria().forClient(clientId).build());
		return purchases.stream().map(Purchase::getDetails).collect(toList());
	}
}
