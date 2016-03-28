package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.PaymentMethod;
import com.demonsbook.ddd.game.haven.domain.repository.PaymentMethodRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryPaymentMethodRepository extends InMemoryRepository<PaymentMethod, PaymentMethodId> implements PaymentMethodRepository {
}
