package com.byrEE.models;

import com.byrEE.models.support.PostFormat;
import com.byrEE.models.support.PostType;
import com.byrEE.models.support.PostStatus;

import lombok.Setter;
import lombok.Getter;
import lombok.Builder;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import com.byrEE.models.Tag;

/**
 * @author byrEE
 */

@Document(collection="posts")
@Getter @Setter
public class Post extends BaseModel{
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT=new SimpleDateFormat("yyyy/MM/dd");

	@DBRef
	private User user;

	@Field
	private String title;

	private String content;

	private String renderedContent;

	@Field
	@Enumrated(EnumType.STRING)
	private PostStatus postStatus=PostStatus.PUBLISHED;

	@Field
	@Enumrated(EnumType.STRING)
	private PostType postType=PostType.POST;

	@Field
	@Enumrated(EnumType.STRING)
	private PostFormat postFormat=PostFormat.MARKDOWN;

	//@DBRef
	private Set<Tag> tags=new HashSet<>();

	private String permalink;

	public String getRenderedContent(){
		if(this.postFormat==PostFormat.MARKDOWN)
			return renderedContent();
		return getContent();
	}

	public void setPermalink(String permalink){
		String token=permalink.toLowerCase().replace("\n"," ").replaceAll("[^a-z\\d\\s]"," ");
		this.permalink=StringUtils.arrayToDelimitedString(StringUtils.tokenizeToStringArray(token," "),"-");
	}

}