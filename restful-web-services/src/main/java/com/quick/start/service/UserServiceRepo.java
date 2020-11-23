package com.quick.start.service;

import java.util.List;
import java.util.Optional;

import com.quick.start.domain.UserBean;

/**
 * @author Aymen Chaaben
 *
 */
public interface UserServiceRepo {

	List<UserBean> getAllUsers();

	UserBean saveUser(UserBean user);

	Optional<UserBean> findUserById(int id);

	void deleteUserById(int id);
}
