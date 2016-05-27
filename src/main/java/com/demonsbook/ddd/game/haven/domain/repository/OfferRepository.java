package com.demonsbook.ddd.game.haven.domain.repository;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Repository;
import com.demonsbook.ddd.game.haven.domain.entity.Offer;
import com.demonsbook.ddd.game.haven.domain.value.object.OfferId;

import java.util.Collection;

public interface OfferRepository extends Repository<OfferId, Offer> {
	Collection<Offer> getAllMatching(OfferSearchCriteria criteria);
}
