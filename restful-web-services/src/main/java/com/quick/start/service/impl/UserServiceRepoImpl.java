package com.quick.start.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quick.start.dao.UserRepository;
import com.quick.start.domain.UserBean;
import com.quick.start.service.UserServiceRepo;

/**
 * @author Aymen Chaaben
 *
 */
@Component
public class UserServiceRepoImpl implements UserServiceRepo {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserBean> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserBean saveUser(UserBean user) {
		return userRepository.save(user);
	}

	@Override
	public Optional<UserBean> findUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteUserById(int id) {
		 userRepository.deleteById(id);
	}

}
