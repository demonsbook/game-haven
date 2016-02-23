package com.demonsbook.ddd.game.haven.domain.building.blocks;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class Entity<T extends EntityId> {
	protected final T id;

	protected Entity(T id) {
		this.id = id;
	}

	public T id() {
		return id;
	}

	@Override
	public final boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other == null || !(other instanceof Entity) ) {
			return false;
		}

		Entity<?> entity = (Entity<?>) other;

		return new EqualsBuilder()
				.append(id, entity.id)
				.isEquals();
	}

	@Override
	public final int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(id)
				.toHashCode();
	}
}
