package com.demonsbook.ddd.game.haven.domain.building.blocks;

import java.util.Collection;

public interface Repository<K extends EntityId, E extends Entity<K>> {
	Collection<E> getAll();

	E getForId(K id);

	void save(E entity);
}
