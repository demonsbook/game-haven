package com.demonsbook.ddd.game.haven.domain.value.object;

import java.util.UUID;

public final class DeliveryDetailsId {
	private final UUID value;

	public DeliveryDetailsId(UUID value) {
		this.value = value;
	}

	public UUID value() {
		return value;
	}
}
