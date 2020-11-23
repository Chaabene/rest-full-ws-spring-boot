package com.quick.start.controllers;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quick.start.domain.Name;
import com.quick.start.domain.UserBeanV1;
import com.quick.start.domain.UserBeanV2;

@RestController
public class UserControllerVersioning {


	@GetMapping("/v1/users-versioning")
	public UserBeanV1 getUserV1UriMethode() {
		return new UserBeanV1(1, "aymen", new Date());
		
	}
	
	@GetMapping("/v2/users-versioning")
	public UserBeanV2 getUserV2UriMethode() {
		return new UserBeanV2(1, new Name("aymen","chaabene"), new Date());
	}
	
	

	@GetMapping(value="/users-versioning/param",params = "version=1")
	public UserBeanV1 getUserV1ParamMethode() {
		return new UserBeanV1(1, "aymen", new Date());
		
	}
	
	@GetMapping(value="/users-versioning/param",params = "version=2")
	public UserBeanV2 getUserV2ParamMethode() {
		return new UserBeanV2(1, new Name("aymen","chaabene"), new Date());
	}

	
	@GetMapping(value="/users-versioning/header",headers  = "X-API-VERSION=1")
	public UserBeanV1 getUserV1HeaderMethode() {
		return new UserBeanV1(1, "aymen", new Date());
		
	}
	
	@GetMapping(value="/users-versioning/header",headers = "X-API-VERSION=2")
	public UserBeanV2 getUserV2HeaderMethode() {
		return new UserBeanV2(1, new Name("aymen","chaabene"), new Date());
	}

	@GetMapping(value="/users-versioning/produces",produces ="application/v1+json")
	public UserBeanV1 getUserV1ProducesMethode() {
		return new UserBeanV1(1, "aymen", new Date());
		
	}
	
	@GetMapping(value="/users-versioning/produces",produces ="application/v2+json")
	public UserBeanV2 getUserV2ProducesMethode() {
		return new UserBeanV2(1, new Name("aymen","chaabene"), new Date());
	}


}
