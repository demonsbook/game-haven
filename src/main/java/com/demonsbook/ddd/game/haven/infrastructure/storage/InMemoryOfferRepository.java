package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.repository.OfferRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryOfferRepository extends InMemoryRepository<Offer, OfferId> implements OfferRepository {
}
