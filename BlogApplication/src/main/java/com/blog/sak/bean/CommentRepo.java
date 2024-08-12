package com.blog.sak.bean;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {

	public List<Comment>findByPostId(long postId);
	
}
