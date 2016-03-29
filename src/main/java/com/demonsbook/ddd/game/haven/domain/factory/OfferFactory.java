package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferFactory {

	@Autowired private UserRepository userRepository;

	public Offer createFor(UserId userId, DeliveryMethodId deliveryMethodId, PaymentMethodId paymentMethodId) {
		User user = userRepository.getForId(userId);
		return new Offer(userId, user.getBasketDetails().getProducts(), deliveryMethodId, paymentMethodId);
	}
}
