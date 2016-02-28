package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.DeliveryMethod;
import com.demonsbook.ddd.game.haven.domain.repository.DeliveryMethodRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class InMemoryDeliveryMethodRepositoryTest {

	private DeliveryMethodRepository userRepository = new InMemoryDeliveryMethodRepository();

	@Test
	public void shouldStoreMultipleDeliveryMethods() {
		DeliveryMethod user1 = new DeliveryMethod();
		DeliveryMethod user2 = new DeliveryMethod();

		userRepository.save(user1);
		userRepository.save(user2);

		Assertions.assertThat(userRepository.getForId(user1.id())).isSameAs(user1);
		Assertions.assertThat(userRepository.getForId(user2.id())).isSameAs(user2);
	}

	@Test
	public void shouldReturnNullForNonExistentDeliveryMethod() {
		DeliveryMethod user = new DeliveryMethod();

		Assertions.assertThat(userRepository.getForId(user.id())).isNull();
	}

}