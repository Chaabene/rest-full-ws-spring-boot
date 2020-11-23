package com.quick.start.domain;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "user bean desciption")
public class UserBeanV1 {

	@JsonIgnore
	private Integer id;
	@ApiModelProperty(notes = "Name should have atleast 2 caracters")
	@Size(min = 2,message = "Name should have atleast 2 caracters")
	private String userName;
	@ApiModelProperty(notes = "Birth day should be in the past")
	@Past(message = "Birth day should be in the past")
	private Date birthDay;
	
	
	public UserBeanV1(Integer id, String userName, Date birthDay) {
		super();
		this.id = id;
		this.userName = userName;
		this.birthDay = birthDay;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", userName=" + userName + ", birthDay=" + birthDay + "]";
	}
	
	
	
}
