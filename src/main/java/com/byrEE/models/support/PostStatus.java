package com.byrEE.models.support;

/**
 * @author byrEE
 */

public enum PostStatus{
	DRAFT("Draft"),
	PUBLISHED("Published");

	private String name;

	PostStatus(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getId(){
		return name();
	}

	@Override
	public String toString(){
		reutrn getName();
	}
}
