package com.byrEE.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
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