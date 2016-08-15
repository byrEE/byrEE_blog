package com.byrEE.models;

import com.byrEE.models.support.PostFormat;
import com.byrEE.models.support.PostType;
import com.byrEE.models.support.PostStatus;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.*;

import com.byrEE.models.Tag;

/**
 * @author byrEE
 */

@Document(collection="posts")

public class Post extends BaseModel{
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT=new SimpleDateFormat("yyyy/MM/dd");

	@DBRef
	private User user;

	@Field
	private String title;

	private String content;

	private String renderedContent;


	public User getUser(){
		return user;
	}

	public void setUser(User user){
		this.user=user;
	}

	
	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content=content;
	}


	public void setRenderedContent(String renderedContent){
		this.renderedContent=renderedContent;
	}

	@Field
	@Enumerated(EnumType.STRING)
	private PostStatus postStatus=PostStatus.PUBLISHED;

	@Field
	@Enumerated(EnumType.STRING)
	private PostType postType=PostType.POST;

	@Field
	@Enumerated(EnumType.STRING)
	private PostFormat postFormat=PostFormat.MARKDOWN;


	public PostStatus getPostStatus(){
		return postStatus;
	}

	public void setPostStatus(PostStatus postStatus){
		this.postStatus=postStatus;
	}

	public PostType getPostType(){
		return postType;
	}

	public void setPostType(PostType postType){
		this.postType=postType;
	}

	public PostFormat getPostFormat(){
		return postFormat;
	}

	public void setPostFormat(PostFormat postFormat){
		this.postFormat=postFormat;
	}

	//@DBRef
	private Set<Tag> tags=new HashSet<>();

	public Set<Tag> getTags(){
		return tags;
	}

	public void setTags(Set<Tag> tags){
		this.tags=tags;
	}

	private String permalink;

	public String getPermalink(){
		return permalink;
	}


	public String getRenderedContent(){
		if(this.postFormat==PostFormat.MARKDOWN)
			return renderedContent;
		return getContent();
	}

	public void setPermalink(String permalink){
		String token=permalink.toLowerCase().replace("\n"," ").replaceAll("[^a-z\\d\\s]"," ");
		this.permalink=StringUtils.arrayToDelimitedString(StringUtils.tokenizeToStringArray(token," "),"-");
	}

}