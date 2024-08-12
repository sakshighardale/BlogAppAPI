package com.blog.sak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.sak.bean.Comment;
import com.blog.sak.bean.CommentRepo;

@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{id}/addComment")
	public ResponseEntity<String> createComment(@RequestBody Comment comment, @PathVariable("id") long postId )
	{
		Comment reponse=commentService.addComment(comment, postId);
		
		return new ResponseEntity<>("Comment created successfully with Id ->"+reponse.getId(),HttpStatus.CREATED);
	}
	
	@GetMapping("/comment/{id}")
	public ResponseEntity<Comment> getCommentByCommnetId(@PathVariable long id)
	{
		Comment comment=commentService.getCommentById(id);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}
	
	@GetMapping("/post/{postId}/comment")
	public List<Comment> getCommentsbyPostID(@PathVariable("postId") long postId)
	{
		return commentService.getCommentByPostId(postId);	
	}
	
	@PutMapping("/post/{postId}/comment/{id}")
	public  ResponseEntity<String> updateComment(@PathVariable long postId, @PathVariable long id,@RequestBody Comment comment)
	{
		commentService.updateCommentByID(id, postId, comment);
		return ResponseEntity.ok().body("Comment updated Successfully.");
	}
	
	@DeleteMapping("/deleteComment/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable long id)
	{
		commentService.deleteComment(id);
		return ResponseEntity.ok().body("Comment deleted successfully.");
	}
	
}
