package com.byrEE.services;


import com.byrEE.Constants;
import com.byrEE.error.NotFoundException;
import com.byrEE.models.Post;
import com.byrEE.models.Tag;
import com.byrEE.models.support.PostFormat;
import com.byrEE.models.support.PostStatus;
import com.byrEE.models.support.PostType;
import com.byrEE.repositories.PostRepository;
import com.byrEE.utils.Markdown;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * @author  byrEE
 */

@Service
public class PostService{
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private UserRepository userRepository;

	public static final String CACHE_NAME = "cache.post";
    public static final String CACHE_NAME_ARCHIVE = CACHE_NAME + ".archive";
    public static final String CACHE_NAME_PAGE = CACHE_NAME + ".page";
    public static final String CACHE_NAME_TAGS = CACHE_NAME + ".tag";
    public static final String CACHE_NAME_COUNTS = CACHE_NAME + ".counts_tags";

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Cacheable(CACHE_NAME)
    public Post getPost(Long postId){
    	logger.debug("Get post "+ postId);
    	
    	Post post=postRepository.findOne(postId);

    	if (post=null){
    		throw new NotFoundException("Post with id "+postId+" is not found");
    	}
    	return post;
    }

    @Cacheable(CACHE_NAME)
    public Post getPublishedPostByPermalink(String permaink){
    	logger.debug("Get post with permalink "+permalink);

    	Post post=postRepository.findByPermalinkAndPostStatus(permalink,PostStatus.PUBLISHED);

	   	if (post=null){
    		throw new NotFoundException("Post with permalink "+permalink+" is not found");
    	}

    	return post;
    }

    @Caching(evict = {
            @CacheEvict(value = CACHE_NAME_ARCHIVE, allEntries = true),
            @CacheEvict(value = CACHE_NAME_PAGE, allEntries = true),
            @CacheEvict(value = CACHE_NAME_COUNTS, allEntries = true)
    })
    public Post createPost(Post post){
    	if(post.getPostFormat()==PostFormat.MARKDOWN){
    		post.setRenderedContent(Markdown.markdownToHtml(post.getContent()));
    	}

    	return postRepository.save(post);
    }

    @Caching(evict = {
            @CacheEvict(value = CACHE_NAME, key = "#post.id"),
            @CacheEvict(value = CACHE_NAME, key = "#post.permalink", condition = "#post.permalink != null"),
            @CacheEvict(value = CACHE_NAME_TAGS, key = "#post.id.toString().concat('-tags')"),
            @CacheEvict(value = CACHE_NAME_ARCHIVE, allEntries = true),
            @CacheEvict(value = CACHE_NAME_PAGE, allEntries = true),
            @CacheEvict(value = CACHE_NAME_COUNTS, allEntries = true)
    })
    public Post updatePost(Post post){
    	if(post.getPostFormat()==PostFormat.MARKDOWN){
    		post.setRenderedContent(Markdown.markdownToHtml(post.getContent()));
    	}

    	return postRepository.save(post);
   }

   @Cacheable(value = CACHE_NAME_ARCHIVE, key = "#root.method.name")
   public List<Post> getArchivePosts(){
   	logger.debug("Get all archives from database");

   	Iterable<Post> posts=postRepository.findAllByPostTypeAndPostStatus(
   		PostType.POST,
        PostStatus.PUBLISHED,
        new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.DESC, "createdAt"));

   	List<Post> cachePosts =new ArrayList<>();

   	posts.forEach(post->cachePosts.add(extractPostMeta(post)));

   	return cachePosts;
   }

    @Cacheable(value = CACHE_NAME_TAGS, key = "#post.id.toString().concat('-tags')")
    public List<Tag> getPostTags(Post post){
    	logger.debug("get tags fo post "+post.getId());

    	List<Tag> tags=new ArrayList<>();
    	postRepository.findOne(post.getId()).getTags().forEach(tags::add);

    	return tags;

    }

    private Post extractPostMeta(Post post) {
        Post archivePost = new Post();
        archivePost.setId(post.getId());
        archivePost.setTitle(post.getTitle());
        archivePost.setPermalink(post.getPermalink());
        archivePost.setCreatedAt(post.getCreatedAt());

        return archivePost;
    }

    public createAboutPage(){
    	logger.debug("Create default about page");

    	Post post=new Post();

    	post.setTitle(Constants.ABOUT_PAGE_PERMALINK);
    	post.setContent(Constants.ABOUT_PAGE_PERMALINK.toLowerCase());
    	post.setPermalink(Constants.ABOUT_PAGE_PERMALINK);
    	post.setUser(userService.getSuperUser());
    	post.setPostFormat(PostFormat.MARKDOWN);

    	return createPost(post);
    }

    public Set<Tag> parseTagNames(String tagNames){
    	Set<Tag> tags=new HashSet<>();

    	if(tagNames!=null && tagNames.isEmpty()){
    		tagNames=tagNames.toLowerCase();
    		String[] names=tagNames.splite("\\s*,\\s*");
    		for(String name:names)
    			tags.add(tagService.findOrCreatByName(name));
    	}

    	return tags;
    }
 
 	public String getTagNames(Set<Tag> tags){
 		if(tags==null ||tags.isEmpty())
 			return "";
 		StringBuilder names=new StringBuilder();
 		tags.forEach(tag->names.append(tag.getName()).append(","));
 		names.deleteCharAt(names.length-1);

 		return names.toString();
 	}

	public Page<Post> findPostsByTag(String tagName, int page, int pageSize) {
        return postRepository.findByTag(tagName, new PageRequest(page, pageSize, Sort.Direction.DESC, "createdAt"));
    }

	@Cacheable(value = CACHE_NAME_COUNTS, key = "#root.method.name")
	public List<Object[]> countPostsByTags(){
		logger.debug("Count posts group by tags. ");

		return postRepository.countPostsByTags(PostStatus.PUBLISHED);
	}    	 	
	

}