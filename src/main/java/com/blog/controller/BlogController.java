package com.blog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.blog.model.Blog;
import com.blog.services.AiSummarizationService;
import com.blog.services.BlogService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class BlogController {
	
	
	
	@Autowired
	BlogService blogService;
	
	@RequestMapping("/")
	public String home() {
		return "Welcome to my BLOG page!";
	}

    @PostMapping("/summarize")
    public boolean summarizeText(Map<String, String> request, Blog blog) {
        return blogService.addBlogWithAI(blog);
    }
	
	@PostMapping("/addBlog")
	public boolean addBlog(Blog blog) {
		return blogService.addBlog(blog);
	}
	
	@DeleteMapping("/deleteBlog/{id}")
	public boolean deleteBlog(@PathVariable Long id) {
		return blogService.deleteBlog(id);
	}
	
	@PutMapping("/updateBlog")
	public boolean updateBlog(Blog blog){
		return blogService.updateBlog(blog);
	}
	
	@GetMapping("/getAllBlogs")
	public List<Blog> getAllBlogs() {
		return blogService.getAllBlogs();
	}
	
	@GetMapping("/getBlog/{id}")
	public Blog getBlog(@PathVariable Long id) {
		return blogService.getBlog(id);
	}
	
	
}
