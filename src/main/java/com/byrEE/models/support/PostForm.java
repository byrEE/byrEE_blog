package com.byrEE.models.support;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NonNull;
import lombok.Data;

import java.util.Set;

import com.byrEE.models.Tag;
import com.byrEE.models.support.PostStatus;
import com.byrEE.models.support.PostFormat;

import javax.validation.constraints.NotNull;
/**
 * @author byrEE
 */

@Data
public class PostForm{
	@NonNull
	private String content;

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content=content;
	}

	@NonNull
	private String title;

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title=title;
	}

	@NotNull
	private PostFormat postFormat;

	public PostFormat getPostFormat(){
		return postFormat;
	}

	public void setPostFormat(PostFormat postFormat){
		this.postFormat=postFormat;
	}

	@NotNull
	private PostStatus postStatus;

	public PostStatus getPostStatus(){
		return postStatus;
	}

	public void setPostStatus(PostStatus postStatus){
		this.postStatus=postStatus;
	}

	@NotNull
	private String permalink;

	public String getPermalink(){
		return permalink;
	}

	public void setPermalink(String permalink){
		this.permalink=permalink;
	}

	@NotNull
	private String postTags;

	public String getPostTags(){
		return postTags;
	}

	public void setPostTags(String postTags){
		this.postTags=postTags;
	}

}