package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static com.demonsbook.ddd.game.haven.domain.repository.OfferSearchCriteria.anOfferSearchCriteria;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_DELIVERY_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_OFFER;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PAYMENT_METHOD_ID;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_PRICE;
import static com.demonsbook.ddd.game.haven.util.TestDummies.DUMMY_CLIENT_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryOfferRepositoryTest {

	private InMemoryOfferRepository repository = new InMemoryOfferRepository();

	@Test
	public void shouldReturnOnlyTheOffersMatchingSearchCriteria() {
		repository.save(DUMMY_OFFER);
		repository.save(new Offer(new ClientId(), Collections.emptySet(), DUMMY_PRICE, DUMMY_DELIVERY_METHOD_ID, DUMMY_PAYMENT_METHOD_ID));

		Collection<Offer> allMatching = repository.getAllMatching(anOfferSearchCriteria().forClient(DUMMY_CLIENT_ID).build());

		assertThat(allMatching).containsOnly(DUMMY_OFFER);
	}
}