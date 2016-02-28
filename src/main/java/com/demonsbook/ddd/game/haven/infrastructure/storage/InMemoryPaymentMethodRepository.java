package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.PaymentMethod;
import com.demonsbook.ddd.game.haven.domain.repository.PaymentMethodRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.PaymentMethodId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryPaymentMethodRepository implements PaymentMethodRepository {

	private Map<PaymentMethodId, PaymentMethod> users = new HashMap<>();

	@Override
	public PaymentMethod getForId(PaymentMethodId id) {
		return users.get(id);
	}

	@Override
	public void save(PaymentMethod user) {
		users.put(user.id(), user);
	}
}
