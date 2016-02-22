package com.demonsbook.ddd.game.haven.domain.assertions;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Entity;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class EntityAssert<T extends Entity> extends AbstractAssert<EntityAssert<T>, T> {

	protected EntityAssert(T actual, Class<?> selfType) {
		super(actual, selfType);
	}

	public static <T extends Entity> EntityAssert<Entity> assertThat(T actual) {
		return new EntityAssert<>(actual, EntityAssert.class);
	}

	public EntityAssert<T> isAValidEntity() {
		isNotNull();
		shouldHaveAValidUnchangeableId();
		shouldHaveValidEqualAndHashCodeMethods();

		return this;
	}

	private void shouldHaveAValidUnchangeableId() {
		Assertions.assertThat(actual.id()).isNotNull();
		Assertions.assertThat(actual.id()).isEqualTo(actual.id());
	}

	private void shouldHaveValidEqualAndHashCodeMethods() {
		EqualsVerifier.forClass(actual.getClass()).verify();
	}
}
