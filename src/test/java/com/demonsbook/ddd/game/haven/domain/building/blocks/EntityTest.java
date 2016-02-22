package com.demonsbook.ddd.game.haven.domain.building.blocks;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class EntityTest {

	@Test
	public void shouldHaveAValidUnchangeableId() {
		Entity entity = new SomeEntity();

		assertThat(entity.id()).isNotNull();
		assertThat(entity.id()).isEqualTo(entity.id());
	}

	class SomeEntity extends Entity<SomeEntityId> {
		SomeEntity() {
			super(new SomeEntityId());
		}
	}

	class SomeEntityId extends EntityId { }

}