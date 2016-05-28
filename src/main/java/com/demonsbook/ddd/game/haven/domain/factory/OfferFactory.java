package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Client;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.repository.ClientRepository;
import com.demonsbook.ddd.game.haven.domain.services.PricingService;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.Money;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OfferFactory {

	@Autowired private ClientRepository clientRepository;
	@Autowired private PricingService pricingService;

	public Offer createFor(ClientId clientId, DeliveryMethodId deliveryMethodId, PaymentMethodId paymentMethodId) {
		Client client = clientRepository.getForId(clientId);
		Set<Product> products = client.getBasketDetails().getProducts();
		Money price = pricingService.calculatePriceFor(products);
		return new Offer(clientId, products, price, deliveryMethodId, paymentMethodId);
	}
}
