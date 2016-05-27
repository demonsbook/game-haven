package com.demonsbook.ddd.game.haven.domain.building.blocks;

public class Aggregate<T extends AggregateId> extends Entity<T> {
	protected Aggregate(T id) {
		super(id);
	}
}
