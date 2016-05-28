package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Client;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class InMemoryRepositoryTest {

	private TestRepository testRepository = new TestRepository();

	@Test
	public void shouldStoreMultipleAggregatesClients() {
		Client client1 = new Client();
		Client client2 = new Client();

		testRepository.save(client1);
		testRepository.save(client2);

		Assertions.assertThat(testRepository.getForId(client1.id())).isSameAs(client1);
		Assertions.assertThat(testRepository.getForId(client2.id())).isSameAs(client2);
	}

	@Test
	public void shouldReturnNullForNonExistentAggregated() {
		Client client = new Client();

		Assertions.assertThat(testRepository.getForId(client.id())).isNull();
	}

	@Test
	public void shouldReturnAllStoredAggregates() {
		Client client1 = new Client();
		Client client2 = new Client();

		testRepository.save(client1);
		testRepository.save(client2);

		Assertions.assertThat(testRepository.getAll()).containsOnly(client1, client2);
	}

	private class TestRepository extends InMemoryRepository<Client, ClientId> {
	}

}