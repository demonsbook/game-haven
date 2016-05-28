package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.repository.OfferSearchCriteria;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.ACCEPTED;
import static com.demonsbook.ddd.game.haven.domain.repository.OfferSearchCriteria.anOfferSearchCriteria;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_DELIVERY_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_OFFER;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PAYMENT_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRICE;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRODUCTS;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_CLIENT_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

	@Mock private OfferFactory offerFactory;
	@Mock private OfferRepository offerRepository;
	@Mock private DomainEventPublisher domainEventPublisher;

	@Captor ArgumentCaptor<OfferSearchCriteria> searchCriteriaCaptor;

	@InjectMocks private OfferService offerService;

	@Test
	public void shouldGenerateAndStoreAnOfferForClient() {
		given(offerFactory.createFor(DUMMY_CLIENT_ID, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID)).willReturn(DUMMY_OFFER);
		offerService.generateOfferFor(DUMMY_CLIENT_ID, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);

		then(offerRepository).should().save(DUMMY_OFFER);
	}

	@Test
	public void shouldGiveDetailsOfAllOffersForAGivenClient() {
		given(offerRepository.getAllMatching(anOfferSearchCriteria().forClient(DUMMY_CLIENT_ID).build())).willReturn(ImmutableList.of(DUMMY_OFFER));

		List<OfferDetails> offerDetails = offerService.getOffersFor(DUMMY_CLIENT_ID);

		assertThat(offerDetails).containsOnly(DUMMY_OFFER.getDetails());
	}

	@Test
	public void shouldAllowAcceptanceOfGeneratedOffer() {
		Offer offer = new Offer(DUMMY_CLIENT_ID, DUMMY_PRODUCTS, DUMMY_PRICE, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);
		given(offerRepository.getForId(offer.id())).willReturn(offer);

		offerService.acceptOffer(offer.id());

		assertThat(offer.status()).isSameAs(ACCEPTED);
	}
}