package com.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Repo.BlogRepo;
import com.blog.model.Blog;

@Service
public class BlogService {

	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private AiSummarizationService aiSummarizationService;

	public boolean addBlog(Blog blog) {
		
		return blogRepo.addBlog(blog);
	}

	public boolean deleteBlog(Long id) {
		return blogRepo.deleteBlog(id);
	}

	public List<Blog> getAllBlogs() {
		return blogRepo.getAllBlogs();
	}

	public Blog getBlog(Long id) {
		return blogRepo.getBlog(id);
	}

	public boolean updateBlog(Blog blog) {
		return blogRepo.updateBlog(blog);
	}

	public boolean addBlogWithAI(Blog blog) {
		return blogRepo.addBlogWithAI(blog);
	}

	
}
