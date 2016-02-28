package com.demonsbook.ddd.game.haven.domain.repository;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Repository;
import com.demonsbook.ddd.game.haven.domain.entity.PaymentMethod;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;

public interface PaymentMethodRepository extends Repository<PaymentMethodId, PaymentMethod> {
}
