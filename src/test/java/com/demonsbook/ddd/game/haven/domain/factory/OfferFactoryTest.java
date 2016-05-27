package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_BASKET_DETAILS;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_DELIVERY_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PAYMENT_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRICE;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OfferFactoryTest {

	@Mock private User user;
	@Mock private UserRepository userRepository;
	@Mock private PricingService pricingService;

	@InjectMocks private OfferFactory offerFactory;

	@Test
	public void shouldGenerateOfferForUser() {
		given(userRepository.getForId(DUMMY_USER_ID)).willReturn(user);
		given(pricingService.calculatePriceFor(DUMMY_BASKET_DETAILS.getProducts())).willReturn(DUMMY_PRICE);
		given(user.getBasketDetails()).willReturn(DUMMY_BASKET_DETAILS);

		Offer offer = offerFactory.createFor(DUMMY_USER_ID, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);

		assertThat(offer).isNotNull();
		assertThat(offer.userId()).isSameAs(DUMMY_USER_ID);
		assertThat(offer.products()).isSameAs(DUMMY_BASKET_DETAILS.getProducts());
		assertThat(offer.deliveryMethodId()).isSameAs(DUMMY_DELIVERY_METHOD_ID);
		assertThat(offer.paymentMethodId()).isSameAs(DUMMY_PAYMENT_METHOD_ID);
	}

}