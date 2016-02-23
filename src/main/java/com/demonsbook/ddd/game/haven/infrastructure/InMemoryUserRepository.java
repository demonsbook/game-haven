package com.demonsbook.ddd.game.haven.infrastructure;

import com.demonsbook.ddd.game.haven.domain.User;
import com.demonsbook.ddd.game.haven.domain.UserId;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

	Map<UserId, User> users = new HashMap<>();

	@Override
	public User getForId(UserId id) {
		return users.get(id);
	}

	@Override
	public void save(User user) {
		users.put(user.id(), user);
	}
}
