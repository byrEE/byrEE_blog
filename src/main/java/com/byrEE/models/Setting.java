package com.byrEE.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.data.mongodb.core.index.Indexed;


/**
 * @author byrEE
 */

@Document(collection="setting")
@Getter
@Setter
public class Setting extends BaseModel{
	@Field("_key")
	@Indexed(unique=true)
	private String key;

	public String getKey(){
		return key;
	}

	public void setKey(String key){
		this.key=key;
	}

	@Field("_value")
	private Serializable value;

	public Serializable getValue(){
		return value;
	}

	public void setValue(Serializable value){
		this.value=value;
	}
}