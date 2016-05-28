package com.demonsbook.ddd.game.haven.domain.repository;

import com.demonsbook.ddd.game.haven.domain.building.blocks.Repository;
import com.demonsbook.ddd.game.haven.domain.entity.Client;
import com.demonsbook.ddd.game.haven.domain.value.object.ClientId;

public interface ClientRepository extends Repository<ClientId, Client> {
}
