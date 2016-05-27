package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.util.Money;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.Product;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OfferFactory {

	@Autowired private UserRepository userRepository;
	@Autowired private PricingService pricingService;

	public Offer createFor(UserId userId, DeliveryMethodId deliveryMethodId, PaymentMethodId paymentMethodId) {
		User user = userRepository.getForId(userId);
		Set<Product> products = user.getBasketDetails().getProducts();
		Money price = pricingService.calculatePriceFor(products);
		return new Offer(userId, products, price, deliveryMethodId, paymentMethodId);
	}
}
