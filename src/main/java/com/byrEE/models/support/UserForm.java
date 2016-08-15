package com.byrEE.models.support;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import javax.validation.constraints.NotNull;
/**
 * @author byrEE
 */
@Data
public class UserForm{
	@NotNull
	private String email;

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	@NotNull
	private String newPassword;

	public String getNewPassword(){
		return newPassword;
	}

	public void setNewPassword(String newPassword){
		this.newPassword=newPassword;
	}

	private String password;

	public String getPassword(){
		return password;
	}

	public void setpassword(String password){
		this.password=password;
	}
}