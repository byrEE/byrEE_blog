package com.byrEE.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.mongodb.core.mapping.*;

import java.util.ArrayList;
import java.util.Collection;

import lombok.NonNull;

import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @author byrEE
 */

@Document(collection="user")
public class User extends BaseModel{
	public static final String ROLE_ADMIN="ROLE_ADMIN";
	public static final String ROLE_USER="ROLE_USER";

	@Indexed(unique=true)
	@NonNull
	private String email;

	@JsonIgnore
	@NonNull
	private String password;

	private String role=ROLE_USER;

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password=password;
	}	

	public String getRole(){
		return role;
	}

	public void setRole(String role){
		this.role=role;
	}	

	@DBRef
	private Collection<Post> posts=new ArrayList<>();

	public User(){}

	public User(String email,String password,String role){
		this.email=email;
		this.password=password;
		this.role=role;
	}


}