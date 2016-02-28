package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.PaymentMethod;
import com.demonsbook.ddd.game.haven.domain.repository.PaymentMethodRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class InMemoryPaymentMethodRepositoryTest {

	private PaymentMethodRepository userRepository = new InMemoryPaymentMethodRepository();

	@Test
	public void shouldStoreMultiplePaymentMethods() {
		PaymentMethod user1 = new PaymentMethod();
		PaymentMethod user2 = new PaymentMethod();

		userRepository.save(user1);
		userRepository.save(user2);

		Assertions.assertThat(userRepository.getForId(user1.id())).isSameAs(user1);
		Assertions.assertThat(userRepository.getForId(user2.id())).isSameAs(user2);
	}

	@Test
	public void shouldReturnNullForNonExistentPaymentMethod() {
		PaymentMethod user = new PaymentMethod();

		Assertions.assertThat(userRepository.getForId(user.id())).isNull();
	}

}