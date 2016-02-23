package com.demonsbook.ddd.game.haven.domain.building.blocks;

public interface Repository<K extends EntityId, E extends Entity<K>> {
	E getForId(K id);

	void save(E entity);
}
