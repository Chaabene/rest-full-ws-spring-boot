package com.quick.start.controllers;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.quick.start.domain.UserBean;
import com.quick.start.domain.UserBeanDynamicFiltring;
import com.quick.start.exception.UserNotFoundException;
import com.quick.start.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<UserBean> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public EntityModel<UserBean> findUserById(@PathVariable int id) {
		UserBean user= userService.findUserById(id);
		if(user==null) {
			throw new UserNotFoundException("id "+id);
		}
		//Hateoas 
		EntityModel<UserBean> resource= EntityModel.of(user);
		WebMvcLinkBuilder linkToBuilder=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		resource.add(linkToBuilder.withRel("all-users"));
		return resource;
	}

	@PostMapping("/users")
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
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		boolean checkDelete= userService.deleteUserById(id);
		if(checkDelete==false) {
			throw new UserNotFoundException("id "+id);
		}
	}
	

	@GetMapping("/users/dynamic-filtring")
	public MappingJacksonValue getUserDynamicFilter() {
		UserBeanDynamicFiltring user= new UserBeanDynamicFiltring(5, "aymen", new Date());
		/**
		 * dynamic filter
		 */
		SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("userName","birthDay");
		FilterProvider filters= new SimpleFilterProvider().addFilter("dynamicFilter", filter);
		MappingJacksonValue mapping= new MappingJacksonValue(user);
		mapping.setFilters(filters);
		
		return mapping;
	}

}
