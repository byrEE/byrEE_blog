package com.byrEE.models.support;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NonNull;
import lombok.Data;

import javax.validation.constraints.NotNull;
/**
 * @author byrEE
 */
@Data
public class SettingsForm{
	
	@NonNull @NotNull
	private String siteName;

	@NonNull
	private String siteSlogan;

	@NonNull
	private Integer pageSize;
}