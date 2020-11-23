package com.quick.start.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quick.start.domain.UserBean;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Integer>{

	 
}
