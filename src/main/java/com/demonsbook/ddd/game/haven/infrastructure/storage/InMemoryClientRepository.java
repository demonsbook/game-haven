package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.Client;
import com.demonsbook.ddd.game.haven.domain.repository.ClientRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryClientRepository extends InMemoryRepository<Client, ClientId> implements ClientRepository {
}
