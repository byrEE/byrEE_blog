package com.byrEE.models.support;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.Data;

import java.util.Set;

import com.byrEE.models.Tag;
import com.byrEE.models.PostStatus;
import com.byrEE.models.PostFormat;

import javax.validation.constraints.NotNull;
/**
 * @author byrEE
 */

@Data
public class PostForm{
	@NonNull
	private String content;

	@NonNull
	private String title;

	@NotNulls
	private PostFormat postFormat;

	@NotNull
	private PostStatus postStatus;

	@NotNull
	private String permalink;

	@NotNull
	private String postTags;

}