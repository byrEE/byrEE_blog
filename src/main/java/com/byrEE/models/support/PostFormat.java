package com.byrEE.models.support;

/**
 *
 * @author byrEE
 */

public enum PostFormat{
	HTML("html"),
	MARKDOWN("Markdown");

	private String name;

	PostFormat(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return name();
	}

	@Override
	public String toString(){
		return getName();
	}
}