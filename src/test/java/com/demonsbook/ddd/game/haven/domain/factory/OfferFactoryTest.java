package com.demonsbook.ddd.game.haven.domain.factory;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.entity.Client;
import com.demonsbook.ddd.game.haven.domain.repository.ClientRepository;
import com.demonsbook.ddd.game.haven.domain.services.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_BASKET_DETAILS;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_DELIVERY_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PAYMENT_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRICE;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_CLIENT_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OfferFactoryTest {

	@Mock private Client client;
	@Mock private ClientRepository clientRepository;
	@Mock private PricingService pricingService;

	@InjectMocks private OfferFactory offerFactory;

	@Test
	public void shouldGenerateOfferForClient() {
		given(clientRepository.getForId(DUMMY_CLIENT_ID)).willReturn(client);
		given(pricingService.calculatePriceFor(DUMMY_BASKET_DETAILS.getProducts())).willReturn(DUMMY_PRICE);
		given(client.getBasketDetails()).willReturn(DUMMY_BASKET_DETAILS);

		Offer offer = offerFactory.createFor(DUMMY_CLIENT_ID, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);

		assertThat(offer).isNotNull();
		assertThat(offer.clientId()).isSameAs(DUMMY_CLIENT_ID);
		assertThat(offer.products()).isSameAs(DUMMY_BASKET_DETAILS.getProducts());
		assertThat(offer.deliveryMethodId()).isSameAs(DUMMY_DELIVERY_METHOD_ID);
		assertThat(offer.paymentMethodId()).isSameAs(DUMMY_PAYMENT_METHOD_ID);
	}

}