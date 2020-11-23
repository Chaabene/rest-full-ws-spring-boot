package com.quick.start.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.quick.start.dao.UserDao;
import com.quick.start.domain.UserBean;

@Component
public class UserDaoImpl implements UserDao {
	private static List<UserBean> users = new ArrayList<>();
	private static int userCount = 3;
	static {
		users.add(new UserBean(1, "Aymen", new Date()));
		users.add(new UserBean(2, "Achref", new Date()));
		users.add(new UserBean(3, "Khalil", new Date()));
	}

	@Override
	public List<UserBean> getAllUsers() {
		return users;
	}

	@Override
	public UserBean saveUser(UserBean user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}

	@Override
	public UserBean findUserById(int id) {
		return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);

	}

	@Override
	public boolean deleteUserById(int id) {
		return users.removeIf(u -> u.getId() == id);
		
	}

}
