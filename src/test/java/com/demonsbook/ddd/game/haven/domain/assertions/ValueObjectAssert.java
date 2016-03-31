package com.demonsbook.ddd.game.haven.domain.assertions;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.assertj.core.api.AbstractAssert;
import org.mutabilitydetector.unittesting.MutabilityAssert;

public class ValueObjectAssert<T> extends AbstractAssert<ValueObjectAssert<T>, T> {

	private ValueObjectAssert(T actual, Class<?> selfType) {
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
