package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Collections;

import static java.util.Collections.emptySet;

public class InMemoryOrderRepositoryTest {

	private static final UserId USER_ID = new UserId();
	private OfferRepository offerRepository = new InMemoryOfferRepository();

	@Test
	public void shouldStoreMultipleOffers() {
		Offer offer1 = new Offer(USER_ID, emptySet());
		Offer offer2 = new Offer(USER_ID, emptySet());

		offerRepository.save(offer1);
		offerRepository.save(offer2);

		Assertions.assertThat(offerRepository.getForId(offer1.id())).isSameAs(offer1);
		Assertions.assertThat(offerRepository.getForId(offer2.id())).isSameAs(offer2);
	}

	@Test
	public void shouldReturnNullForNonExistentOffer() {
		Offer offer = new Offer(USER_ID, emptySet());

		Assertions.assertThat(offerRepository.getForId(offer.id())).isNull();
	}

}