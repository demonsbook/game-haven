package com.demonsbook.ddd.game.haven.infrastructure.storage;

import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.springframework.stereotype.Repository;

@Repository
class InMemoryUserRepository extends InMemoryRepository<User, UserId> implements UserRepository {
}
