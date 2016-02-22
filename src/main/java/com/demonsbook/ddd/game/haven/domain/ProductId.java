package com.demonsbook.ddd.game.haven.domain;

import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;

import java.util.UUID;

public final class ProductId extends EntityId {
	public ProductId() {
	}

	public ProductId(UUID value) {
		super(value);
	}
}
