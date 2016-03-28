package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import com.demonsbook.ddd.game.haven.domain.event.OfferAccepted;
import com.demonsbook.ddd.game.haven.domain.factory.PurchaseFactory;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_OFFER;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_OFFER_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PURCHASE;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class OfferStatusServiceTest {

	@Mock private UserRepository userRepository;
	@Mock private OfferRepository offerRepository;
	@Mock private DomainEventPublisher eventPublisher;
	@Mock private PurchaseFactory purchaseFactory;
	@Mock private PurchaseRepository purchaseRepository;

	@InjectMocks OfferStatusService offerStatusService;

	private OfferAccepted offerAcceptedEvent = new OfferAccepted(DUMMY_OFFER_ID);

	@Before
	public void init() {
		offerStatusService.subscribeToEvent();
	}

	@Test
	public void shouldGenerateAndStorePurchaseAfterOfferHadBeenAccepted() {
		given(offerRepository.getForId(DUMMY_OFFER_ID)).willReturn(DUMMY_OFFER);
		given(purchaseFactory.createFor(DUMMY_OFFER)).willReturn(DUMMY_PURCHASE);

		offerStatusService.handle(offerAcceptedEvent);

		then(purchaseRepository).should().save(DUMMY_PURCHASE);
	}

}