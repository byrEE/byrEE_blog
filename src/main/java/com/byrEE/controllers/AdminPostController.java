package com.byrEE.controllers;

import com.byrEE.models.support.PostForm;
import com.byrEE.models.Post;
import com.byrEE.models.Tag;
import com.byrEE.models.User;
import com.byrEE.models.support.PostFormat;
import com.byrEE.models.support.PostStatus;
import com.byrEE.repositories.PostRepository;
import com.byrEE.repositories.UserRepository;
import com.byrEE.services.PostService;
import com.byrEE.services.TagService;
import com.byrEE.utils.DTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author byrEE
 */
@Controller("adminPostController")
@RequestMapping("admin/posts")
public class AdminPostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    private static final int PAGE_SIZE = 20;

    @RequestMapping(value = "")
    public String index(@RequestParam(defaultValue = "0") int page, Model model){
        Page<Post> posts = postRepository.findAll(new PageRequest(page, PAGE_SIZE, Sort.Direction.DESC, "id"));

        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("page", page);
        model.addAttribute("posts", posts);

        return "admin/posts/index";
    }

    @RequestMapping(value = "new")
    public String newPost(Model model){
        PostForm postForm = DTOUtil.map(new Post(), PostForm.class);
        postForm.setPostTags("");

        model.addAttribute("postForm", postForm);
        model.addAttribute("postFormats", PostFormat.values());
        model.addAttribute("postStatus", PostStatus.values());

        return "admin/posts/new";
    }

    @RequestMapping(value = "{postId:[0-9]+}/edit")
    public String editPost(@PathVariable Long postId, Model model){
        Post post = postRepository.findOne(postId);
        PostForm postForm = DTOUtil.map(post, PostForm.class);

        postForm.setPostTags(postService.getTagNames(post.getTags()));

        model.addAttribute("post", post);
        model.addAttribute("postForm", postForm);
        model.addAttribute("postFormats", PostFormat.values());
        model.addAttribute("postStatus", PostStatus.values());

        return "admin/posts/edit";
    }

    @RequestMapping(value = "{postId:[0-9]+}/delete", method = {DELETE, POST})
    public String deletePost(@PathVariable Long postId){
        postService.deletePost(postRepository.findOne(postId));
        return "redirect:/admin/posts";
    }

    @RequestMapping(value = "", method = POST)
    public String create(Principal principal, @Valid PostForm postForm, Errors errors, Model model){
        if (errors.hasErrors()) {
            model.addAttribute("postFormats", PostFormat.values());
            model.addAttribute("postStatus", PostStatus.values());

            return "admin/posts/new";
        } else {
            Post post = DTOUtil.map(postForm, Post.class);
            post.setUser(userRepository.findByEmail(principal.getName()));
            post.setTags(postService.parseTagNames(postForm.getPostTags()));

            postService.createPost(post);

            return "redirect:/admin/posts";
        }
    }

    @RequestMapping(value = "{postId:[0-9]+}", method = {PUT, POST})
    public String update(@PathVariable Long postId, @Valid PostForm postForm, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("postFormats", PostFormat.values());
            model.addAttribute("postStatus", PostStatus.values());

            return "admin/posts_edit";
        } else {
            Post post = postRepository.findOne(postId);
            DTOUtil.mapTo(postForm, post);
            post.setTags(postService.parseTagNames(postForm.getPostTags()));

            postService.updatePost(post);

            return "redirect:/admin/posts";
        }
    }

}
