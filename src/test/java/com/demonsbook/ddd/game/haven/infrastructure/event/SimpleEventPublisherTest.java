package com.demonsbook.ddd.game.haven.infrastructure.event;

import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEvent;
import com.demonsbook.ddd.game.haven.domain.building.blocks.DomainEventListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEventPublisherTest {

	@Mock private DomainEvent event;
	@Mock private DomainEventListener listener;

	private SimpleEventPublisher simpleEventPublisher = new SimpleEventPublisher();

	@Test
	public void shouldPublishEventToAllSubscribedListeners() {
		simpleEventPublisher.subscribeTo(anyEvent -> true, listener);

		simpleEventPublisher.publish(event);

		then(listener).should().handle(event);
	}

	@Test
	public void shouldNotBotherListenerIfEventDoesNotPassTheFilter() {
		simpleEventPublisher.subscribeTo(anyEvent -> false, listener);

		simpleEventPublisher.publish(event);

		then(listener).should(never()).handle(event);
	}

}