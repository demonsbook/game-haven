package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Purchase;
import com.demonsbook.ddd.game.haven.domain.entity.PurchaseId;
import com.demonsbook.ddd.game.haven.domain.repository.PurchaseRepository;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryPurchaseRepository extends InMemoryRepository<Purchase, PurchaseId> implements PurchaseRepository {
}
