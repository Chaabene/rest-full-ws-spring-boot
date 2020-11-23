package com.quick.start.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.quick.start.domain.UserBean;
import com.quick.start.exception.UserNotFoundException;
import com.quick.start.service.UserServiceRepo;

@RestController
public class UserJpaController {

	@Autowired
	private UserServiceRepo userService;

	@GetMapping("/jpa/users")
	public List<UserBean> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/jpa/users/{id}")
	public EntityModel<UserBean> findUserById(@PathVariable int id) {
		Optional<UserBean> user= userService.findUserById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id "+id);
		}
		//Hateoas 
		EntityModel<UserBean> resource= EntityModel.of(user.get());
		WebMvcLinkBuilder linkToBuilder=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		resource.add(linkToBuilder.withRel("all-users"));
		return resource;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity saveUser(@Valid @RequestBody UserBean user) {
		UserBean userSaved = userService.saveUser(user);
		/**
		 * Hateoas
		 */
//		EntityModel<UserBean> resource= EntityModel.of(user);
//		WebMvcLinkBuilder linkToBuilder=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).findUserById(userSaved.getId()));
//		return resource.add(linkToBuilder.withRel("all-users"));
		/**
		 * location
		 */
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(userSaved.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		userService.deleteUserById(id);
		
	}
	

}
