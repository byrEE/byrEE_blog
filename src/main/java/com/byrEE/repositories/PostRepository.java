package com.byrEE.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.byrEE.models.Post;
import org.springframework.stereotype.Repository;

import com.byrEE.models.support.PostStatus;
import com.byrEE.models.support.PostType;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.repository.query.Param;
/**
 * @author byrEE
 */


@Repository
public interface PostRepository extends MongoRepository<Post,Long>{
	Post findByPermalinkAndPostStatus(String permalink,PostStatus postStatus);

	Page<Post> findAllByPostType(PostType postType,Pageable pageRequest);

	Page<Post> findAllByPostTypeAndPostStatus(PostType postType,PostStatus postStatus,Pageable pageRequest);

	@Query("{ 'tags':{?0:{'$exists':true} }")
	Page<Post> findByTags(@Param("tag") String tag,Pageable pageRequest);

	//@Query("{ 'tags':{?0:{'$exists':true} }")
	//int countByTags(@Param("tag") String tag)

	@Query(value="{ 'postStatus':?0}",fields="{ 'tags':1,'$size':1}")
	List<Object[]> countPostsByPostStatus(@Param("status") PostStatus Status);

}