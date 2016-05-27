package com.demonsbook.ddd.game.haven.domain.repository;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Repository;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;

import java.util.Collection;

public interface PurchaseRepository extends Repository<PurchaseId, Purchase> {
	Collection<Purchase> getAllMatching(PurchaseSearchCriteria criteria);
}
