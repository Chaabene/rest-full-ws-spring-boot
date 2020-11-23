package com.quick.start.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quick.start.dao.UserDao;
import com.quick.start.domain.UserBean;
import com.quick.start.service.UserService;

/**
 * @author Aymen Chaaben
 *
 */
@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public List<UserBean> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public UserBean saveUser(UserBean user) {
		return userDao.saveUser(user);
	}

	@Override
	public UserBean findUserById(int id) {
		return userDao.findUserById(id);
	}

	@Override
	public boolean deleteUserById(int id) {
		return userDao.deleteUserById(id);
	}

}
