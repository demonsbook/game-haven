package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.factory.OfferFactory;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferDetails;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.demonsbook.ddd.game.haven.domain.entity.Offer.Status.ACCEPTED;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_DELIVERY_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_OFFER;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PAYMENT_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRODUCTS;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_USER_ID;
import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

	@Mock private OfferFactory offerFactory;
	@Mock private OfferRepository offerRepository;
	@Mock private DomainEventPublisher domainEventPublisher;
	@InjectMocks private OfferService offerService;

	@Test
	public void shouldGenerateAndStoreAnOfferForUser() {
		given(offerFactory.createFor(DUMMY_USER_ID, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID)).willReturn(DUMMY_OFFER);
		offerService.generateOfferFor(DUMMY_USER_ID, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);

		then(offerRepository).should().save(DUMMY_OFFER);
	}

	@Test
	public void shouldGiveDetailsOfAllOffersForAGivenUser() {
		given(offerRepository.getAll()).willReturn(ImmutableList.of(DUMMY_OFFER, new Offer(new UserId(), emptySet(), DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID)));

		List<OfferDetails> offerDetails = offerService.getOffersFor(DUMMY_USER_ID);

		assertThat(offerDetails).containsOnly(DUMMY_OFFER.getDetails());
	}

	@Test
	public void shouldAllowAcceptanceOfGeneratedOffer() {
		Offer offer = new Offer(DUMMY_USER_ID, DUMMY_PRODUCTS, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);
		given(offerRepository.getForId(offer.id())).willReturn(offer);

		offerService.acceptOffer(offer.id());

		assertThat(offer.status()).isSameAs(ACCEPTED);
	}
}