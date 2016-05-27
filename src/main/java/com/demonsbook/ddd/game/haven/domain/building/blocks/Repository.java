package com.demonsbook.ddd.game.haven.domain.building.blocks;

import java.util.Collection;

public interface Repository<K extends AggregateId, E extends Aggregate<K>> {
	Collection<E> getAll();

	E getForId(K id);

	void save(E entity);
}
