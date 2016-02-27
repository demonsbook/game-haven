package com.demonsbook.ddd.game.haven.domain.building.blocks;

public interface DomainEventListener {
	void handle(DomainEvent event);
}
