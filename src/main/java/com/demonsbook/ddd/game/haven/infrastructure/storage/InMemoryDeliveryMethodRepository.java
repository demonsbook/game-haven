package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.DeliveryMethod;
import com.demonsbook.ddd.game.haven.domain.repository.DeliveryMethodRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryDeliveryMethodRepository extends InMemoryRepository<DeliveryMethod, DeliveryMethodId> implements DeliveryMethodRepository {
}
