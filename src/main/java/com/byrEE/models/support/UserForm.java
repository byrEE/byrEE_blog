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

	@NotNull
	private String newPassword;
}