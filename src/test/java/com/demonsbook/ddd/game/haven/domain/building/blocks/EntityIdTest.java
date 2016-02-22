package com.demonsbook.ddd.game.haven.domain.building.blocks;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityIdTest {

	@Test
	public void shouldHaveAnUUIDValue() {
		EntityId entityId = new SomeEntityId();

		assertThat(entityId.value()).isInstanceOf(UUID.class);
	}

	@Test
	public void shouldEqualOtherEntityIdWithTheSameValue() {
		EntityId entityId = new SomeEntityId();
		EntityId otherEntityId = new SomeEntityId(entityId.value());

		assertThat(entityId).isEqualTo(otherEntityId);
	}

	@Test
	public void shouldHaveValidEqualAndHashCodeMethods() {
		EqualsVerifier.forClass(SomeEntityId.class).withRedefinedSuperclass().verify();
	}

	final class SomeEntityId extends EntityId {
		public SomeEntityId() {
			super();
		}

		public SomeEntityId(UUID value) {
			super(value);
		}
	}
}