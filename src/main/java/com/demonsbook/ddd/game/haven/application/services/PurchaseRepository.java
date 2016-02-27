package com.demonsbook.ddd.game.haven.application.services;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Repository;
import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;

public interface PurchaseRepository extends Repository<PurchaseId, Purchase> {
}
