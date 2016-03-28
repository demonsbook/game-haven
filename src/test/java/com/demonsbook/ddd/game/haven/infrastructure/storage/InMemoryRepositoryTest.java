package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class InMemoryRepositoryTest {

	private TestRepository testRepository = new TestRepository();

	@Test
	public void shouldStoreMultipleAggregatesUsers() {
		User user1 = new User();
		User user2 = new User();

		testRepository.save(user1);
		testRepository.save(user2);

		Assertions.assertThat(testRepository.getForId(user1.id())).isSameAs(user1);
		Assertions.assertThat(testRepository.getForId(user2.id())).isSameAs(user2);
	}

	@Test
	public void shouldReturnNullForNonExistentAggregated() {
		User user = new User();

		Assertions.assertThat(testRepository.getForId(user.id())).isNull();
	}

	@Test
	public void shouldReturnAllStoredAggregates() {
		User user1 = new User();
		User user2 = new User();

		testRepository.save(user1);
		testRepository.save(user2);

		Assertions.assertThat(testRepository.getAll()).containsOnly(user1, user2);
	}

	private class TestRepository extends InMemoryRepository<User, UserId> {
	}

}