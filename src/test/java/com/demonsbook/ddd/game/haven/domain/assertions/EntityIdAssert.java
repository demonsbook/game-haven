package com.demonsbook.ddd.game.haven.domain.assertions;

import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class EntityIdAssert<T extends EntityId> extends AbstractAssert<EntityIdAssert<T>, T> {

	protected EntityIdAssert(T actual, Class<?> selfType) {
		super(actual, selfType);
	}

	public static <T extends EntityId> EntityIdAssert<EntityId> assertThat(T actual) {
		return new EntityIdAssert<>(actual, EntityIdAssert.class);
	}

	public EntityIdAssert<T> isAValidEntityId() {
		isNotNull();

		shouldBeAValidValueObject();
		shouldHaveAnUUIDValue();
		shouldEqualOtherEntityIdWithTheSameValue();
		shouldHaveValidEqualAndHashCodeMethods();

		return this;
	}

	private void shouldBeAValidValueObject() {
		ValueObjectAssert.assertThat(actual).isAValidValueObject();
	}

	private void shouldHaveAnUUIDValue() {
		Assertions.assertThat(actual.value()).isInstanceOf(UUID.class);
	}

	private void shouldEqualOtherEntityIdWithTheSameValue() {
		try {
			assertThat(actual).isEqualTo(actual.getClass().getConstructor(UUID.class).newInstance(actual.value()));
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			failWithMessage("EntityId should be instantiable for specified UUID value.");
		}
	}

	private void shouldHaveValidEqualAndHashCodeMethods() {
		EqualsVerifier.forClass(actual.getClass()).withRedefinedSuperclass().verify();
	}
}
