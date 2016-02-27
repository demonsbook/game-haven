package com.demonsbook.ddd.game.haven.infrastructure.event;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEvent;
import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventListener;
import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleEventPublisher implements DomainEventPublisher {

	private List<PickyDomainEventListener> listeners = new ArrayList<>();

	@Override
	public void publish(DomainEvent event) {
		listeners.stream().filter(listener -> listener.accepts(event)).forEach(listener -> listener.handle(event));
	}

	@Override
	public void subscribeTo(DomainEventFilter filter, DomainEventListener listener) {
		listeners.add(new PickyDomainEventListener(filter, listener));
	}

	private class PickyDomainEventListener {
		DomainEventFilter filter;
		DomainEventListener listener;

		public PickyDomainEventListener(DomainEventFilter filter, DomainEventListener listener) {
			this.filter = filter;
			this.listener = listener;
		}

		public boolean accepts(DomainEvent event) {
			return filter.accepts(event);
		}

		public void handle(DomainEvent event) {
			listener.handle(event);
		}
	}

	public interface DomainEventFilter {
		boolean accepts(DomainEvent event);
	}
}
