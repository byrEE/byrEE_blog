package com.byrEE.models;

import org.springframework.data.mongodb.core.mapping.*;

import org.springframework.data.annotation.Id;

//import lombok.Getter;
//import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * @author byrEE
 */

@Document(collection="tag")

public class Tag extends BaseModel{
	@Field
	@Id
	private String name;

	//@DBRef
	private List<Post> posts=new ArrayList<>();

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public Tag(){}

	public Tag(String name){
		this.setName(name);
	}
}