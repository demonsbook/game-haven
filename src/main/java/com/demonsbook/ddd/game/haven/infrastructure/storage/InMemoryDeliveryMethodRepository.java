package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.DeliveryMethod;
import com.demonsbook.ddd.game.haven.domain.repository.DeliveryMethodRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.DeliveryMethodId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryDeliveryMethodRepository implements DeliveryMethodRepository {

	private Map<DeliveryMethodId, DeliveryMethod> users = new HashMap<>();

	@Override
	public DeliveryMethod getForId(DeliveryMethodId id) {
		return users.get(id);
	}

	@Override
	public void save(DeliveryMethod user) {
		users.put(user.id(), user);
	}
}
