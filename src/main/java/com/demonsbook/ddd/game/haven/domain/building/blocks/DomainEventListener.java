package com.demonsbook.ddd.game.haven.domain.building.blocks;

import com.demonsbook.ddd.game.haven.infrastructure.event.SimpleEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public abstract class DomainEventListener {

	@Autowired private DomainEventPublisher eventPublisher;
	private final Map<SimpleEventPublisher.DomainEventFilter, EventHandler> eventHandlers = new HashMap<>();

	protected void subscribeTo(SimpleEventPublisher.DomainEventFilter eventPredicate, EventHandler handler) {
		eventPublisher.subscribeTo(eventPredicate, this);
		eventHandlers.put(eventPredicate, handler);
	}

	public void handle(DomainEvent event) {
		eventHandlers.entrySet().stream()
				.filter(eventHandlerEntry -> eventHandlerEntry.getKey().accepts(event))
				.forEach(eventHandlerEntry -> eventHandlerEntry.getValue().handle(event));
	}

	public interface EventHandler {
		void handle(DomainEvent event);
	}

}
