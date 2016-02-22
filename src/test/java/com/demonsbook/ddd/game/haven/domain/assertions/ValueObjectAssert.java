package com.demonsbook.ddd.game.haven.domain.assertions;

import com.demonsbook.ddd.game.haven.domain.building.blocks.EntityId;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.mutabilitydetector.unittesting.MutabilityAssert;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class ValueObjectAssert<T> extends AbstractAssert<ValueObjectAssert<T>, T> {

	protected ValueObjectAssert(T actual, Class<?> selfType) {
		super(actual, selfType);
	}

	public static <T> ValueObjectAssert<T> assertThat(T actual) {
		return new ValueObjectAssert<>(actual, ValueObjectAssert.class);
	}

	public ValueObjectAssert<T> isAValidValueObject() {
		isNotNull();

		shouldBeImmutable();
		shouldHaveValidEqualAndHashCodeMethods();
		return this;
	}

	private void shouldBeImmutable() {
		MutabilityAssert.assertImmutable(actual.getClass());
	}

	private void shouldHaveValidEqualAndHashCodeMethods() {
		EqualsVerifier.forClass(actual.getClass()).withRedefinedSuperclass().verify();
	}
}
