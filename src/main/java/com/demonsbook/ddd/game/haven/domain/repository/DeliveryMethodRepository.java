package com.demonsbook.ddd.game.haven.domain.repository;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Repository;
import com.demonsbook.ddd.game.haven.domain.entity.DeliveryMethod;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;

public interface DeliveryMethodRepository extends Repository<DeliveryMethodId, DeliveryMethod> {
}
