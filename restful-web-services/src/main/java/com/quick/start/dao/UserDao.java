package com.quick.start.dao;

import java.util.List;

import com.quick.start.domain.UserBean;

public interface UserDao {

	 List<UserBean> getAllUsers();
	 UserBean saveUser(UserBean user);
	 UserBean findUserById(int id);
	 boolean deleteUserById(int id);
	 
}
