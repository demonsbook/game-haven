package com.demonsbook.ddd.game.haven.infrastructure;

import com.demonsbook.ddd.game.haven.domain.entity.User;
import com.demonsbook.ddd.game.haven.domain.repository.UserRepository;
import com.demonsbook.ddd.game.haven.domain.value.object.UserId;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryUserRepository implements UserRepository {

	private Map<UserId, User> users = new HashMap<>();

	@Override
	public User getForId(UserId id) {
		return users.get(id);
	}

	@Override
	public void save(User user) {
		users.put(user.id(), user);
	}
}
