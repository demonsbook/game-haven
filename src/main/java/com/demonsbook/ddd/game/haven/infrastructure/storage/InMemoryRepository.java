package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;
import com.demonsbook.ddd.game.haven.domain.building.blocks.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class InMemoryRepository<E extends Entity<K>, K extends EntityId> implements Repository<K, E> {
	private Map<K, E> aggregates = new HashMap<>();

	@Override
	public Collection<E> getAll() {
		return aggregates.values();
	}

	public E getForId(K id) {
		return aggregates.get(id);
	}

	public void save(E aggregate) {
		aggregates.put(aggregate.id(), aggregate);
	}
}
