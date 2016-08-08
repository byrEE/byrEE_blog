package com.byrEE.models;

import com.fastxml.jackson.annotation.JsonIgnore;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;


/**
 * @author byrEE
 */

@Getter @Setter
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

	@DBRef
	private Collection<Post> posts=new ArrayList<>();

	public User(){}

	public User(String email,String password,String role){
		this.email=email;
		this.password=password;
		this.role=role;
	}


}