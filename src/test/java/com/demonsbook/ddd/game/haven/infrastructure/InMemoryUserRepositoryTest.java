package com.demonsbook.ddd.game.haven.infrastructure;

import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class InMemoryUserRepositoryTest {

	private UserRepository userRepository = new InMemoryUserRepository();

	@Test
	public void shouldStoreMultipleUsers() {
		User user1 = new User();
		User user2 = new User();

		userRepository.save(user1);
		userRepository.save(user2);

		Assertions.assertThat(userRepository.getForId(user1.id())).isSameAs(user1);
		Assertions.assertThat(userRepository.getForId(user2.id())).isSameAs(user2);
	}

	@Test
	public void shouldReturnNullForNonExistentUser() {
		User user = new User();

		Assertions.assertThat(userRepository.getForId(user.id())).isNull();
	}

}