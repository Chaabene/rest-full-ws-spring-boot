package com.quick.start.service;

import java.util.List;

import com.quick.start.domain.UserBean;

/**
 * @author Aymen Chaaben
 *
 */
public interface UserService {

	List<UserBean> getAllUsers();

	UserBean saveUser(UserBean user);

	UserBean findUserById(int id);

	boolean deleteUserById(int id);
}
