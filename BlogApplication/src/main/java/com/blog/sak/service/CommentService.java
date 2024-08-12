package com.blog.sak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.blog.sak.bean.Comment;
import com.blog.sak.bean.CommentRepo;
import com.blog.sak.bean.Post;
import com.blog.sak.dao.PostRepository;

@Service
public class CommentService {
	@Autowired
	public PostRepository postRepo;
	
	@Autowired
	public CommentRepo commentRepo;
	//add a comment
	public Comment addComment(Comment comment, long postId)
	{
		Post post=postRepo.findById(postId).orElseThrow(()->new RuntimeException(postId+" -> This post id doesn't exists."));
		comment.setPost(post);
		return commentRepo.save(comment);
	}
	
	//get a comment by id
	public Comment getCommentById(long id)
	{
		return commentRepo.findById(id).orElseThrow(()-> new RuntimeException(id+" -> This id doesn't exists." ));
	}
	
	//get comment by post_id
	public List<Comment> getCommentByPostId(long postId)
	{
		return commentRepo.findByPostId(postId);
	}
	
	
	//update comment
	public void updateCommentByID(long id,long postId,Comment comment)
	{
		Post post=postRepo.findById(postId).orElseThrow(()->new RuntimeException(postId+" -> This post id doesn't exists."));
		comment.setId(id);
		comment.setPost(post);
		commentRepo.save(comment);
	}
	
	
	//delete comment
	public void deleteComment(long id)
	{	
		if(commentRepo.findById(id).isPresent())
			commentRepo.deleteById(id);
		else
			throw new RuntimeException(id+"-> This id doesn't exists.");
	}
}
