package com.byrEE.models;

import lombok.Setter;
import lombok.Getter;

import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author byrEE
 */

@Document(collection="setting")
@Getter @Setter
public class Setting extends BaseModel{
	@Field("_key")
	@Indexed(unique=true)
	private String key;

	@Field("_value")
	private Serializable value;
}