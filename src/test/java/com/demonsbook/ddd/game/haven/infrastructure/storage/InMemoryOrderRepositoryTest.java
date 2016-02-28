package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_DELIVERY_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PAYMENT_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_USER_ID;
import static java.util.Collections.emptySet;

public class InMemoryOrderRepositoryTest {

	private OfferRepository offerRepository = new InMemoryOfferRepository();

	@Test
	public void shouldStoreMultipleOffers() {
		Offer offer1 = new Offer(DUMMY_USER_ID, emptySet(), DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);
		Offer offer2 = new Offer(DUMMY_USER_ID, emptySet(), DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);

		offerRepository.save(offer1);
		offerRepository.save(offer2);

		Assertions.assertThat(offerRepository.getForId(offer1.id())).isSameAs(offer1);
		Assertions.assertThat(offerRepository.getForId(offer2.id())).isSameAs(offer2);
	}

	@Test
	public void shouldReturnNullForNonExistentOffer() {
		Offer offer = new Offer(DUMMY_USER_ID, emptySet(), DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID);

		Assertions.assertThat(offerRepository.getForId(offer.id())).isNull();
	}

}