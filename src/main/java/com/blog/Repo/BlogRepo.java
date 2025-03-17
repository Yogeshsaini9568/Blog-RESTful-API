package com.blog.Repo;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blog.model.Blog;
import com.blog.services.AiSummarizationService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class BlogRepo {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	AiSummarizationService aiSummarizationService;
	
	@Transactional
	public boolean addBlog(Blog blog) {
		Session session=entityManager.unwrap(Session.class);
		Blog b=session.get(Blog.class, blog.getId());
		if(b==null) {
			session.persist(blog);
			return true;
		}else {
			return false;
		}
	}

	@Transactional
	public boolean deleteBlog(Long id) {
		Session session=entityManager.unwrap(Session.class);
		Blog b=session.get(Blog.class, id);
		if(b==null) {
			return false;
		}else {
			session.remove(b);
//			// Check the number of remaining records
//            long count = (Long) session.createQuery("SELECT COUNT(b) FROM Blog b").getSingleResult();
//
//            if (count == 0) {
//                // Reset AUTO_INCREMENT to 1 if no records are left
//                String resetAutoIncrementQuery = "ALTER TABLE blog AUTO_INCREMENT = 1";
//                session.createNativeQuery(resetAutoIncrementQuery).executeUpdate();
//            } else {
//                // Reset AUTO_INCREMENT to MAX(id) + 1 if records still exist
//                long maxId = (Long) session.createQuery("SELECT MAX(b.id) FROM Blog b").getSingleResult();
//                String resetAutoIncrementQuery = "ALTER TABLE blog AUTO_INCREMENT = " + (maxId + 1);
//                session.createNativeQuery(resetAutoIncrementQuery).executeUpdate();
//            }
			return true;
		}
	}

	public List<Blog> getAllBlogs() {
		Session session= entityManager.unwrap(Session.class);
		List<Blog> list=session.createQuery("select b from Blog b ",Blog.class)
		.list();
		return list;
	}

	public Blog getBlog(Long id) {
		Session session= entityManager.unwrap(Session.class);
		Blog b=session.get(Blog.class, id);
		return b;
	}

	@Transactional
	public boolean updateBlog(Blog blog) {
		Session session= entityManager.unwrap(Session.class);
		Blog b=session.get(Blog.class, blog.getId());
		if(b==null) {
			return false;
		}else {
			b.setTitle(blog.getTitle());
			b.setContent(blog.getContent());
			b.setAuthor(blog.getAuthor());
			b.setCreatedAt(LocalDateTime.now());
			session.persist(b);
			return true;
		}
	}

	@Transactional
	public boolean addBlogWithAI(Blog blog) {
		Session session=entityManager.unwrap(Session.class);
		Blog b=session.get(Blog.class, blog.getId());
		String summary = aiSummarizationService.getSummary(blog.getContent());
		blog.setContent(summary);
		if(b==null) {
			session.persist(blog);
			return true;
		}else {
			return false;
		}
	}

	

	
}
