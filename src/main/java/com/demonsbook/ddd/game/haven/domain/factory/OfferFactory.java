package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.exception.BasketContentsChangedBeforeOfferCreatedException;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.BasketDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferFactory {

	@Autowired private UserRepository userRepository;

	public Offer createFor(BasketDetails basketDetails, DeliveryMethodId deliveryMethodId, PaymentMethodId paymentMethodId) {
		validateBasketDetails(basketDetails);
		return new Offer(basketDetails.getUserId(), basketDetails.getProducts(), deliveryMethodId, paymentMethodId);
	}

	private void validateBasketDetails(BasketDetails basketDetails) {
		User user = userRepository.getForId(basketDetails.getUserId());
		if (!user.getBasketDetails().equals(basketDetails)) {
			throw new BasketContentsChangedBeforeOfferCreatedException();
		}
	}
}
