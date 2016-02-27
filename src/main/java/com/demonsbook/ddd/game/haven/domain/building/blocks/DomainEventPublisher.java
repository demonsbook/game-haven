package com.demonsbook.ddd.game.haven.domain.building.blocks;

import com.demonsbook.ddd.game.haven.infrastructure.event.SimpleEventPublisher;

public interface DomainEventPublisher {
	void publish(DomainEvent event);

	void subscribeTo(SimpleEventPublisher.DomainEventFilter filter, DomainEventListener listener);
}
