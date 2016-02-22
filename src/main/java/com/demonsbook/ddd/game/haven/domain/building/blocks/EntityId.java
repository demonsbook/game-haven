package com.demonsbook.ddd.game.haven.domain.building.blocks;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public abstract class EntityId {
	private final UUID uuid;

	public EntityId() {
		this.uuid = UUID.randomUUID();
	}

	public EntityId(UUID value) {
		this.uuid = value;
	}

	public UUID value() {
		return uuid;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		EntityId entityId = (EntityId) o;

		return new EqualsBuilder()
				.append(uuid, entityId.uuid)
				.isEquals();
	}

	@Override
	public final int hashCode() {
		return new HashCodeBuilder()
				.append(uuid)
				.toHashCode();
	}

}
