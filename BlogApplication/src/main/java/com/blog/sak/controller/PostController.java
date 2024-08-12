package com.blog.sak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.blog.sak.bean.Post;
import com.blog.sak.service.PostService;

@RestController
public class PostController {
	@Autowired
	
	private PostService postService;
	
	@PostMapping("createPost")
	public ResponseEntity<String> createPost(@RequestBody Post post)
	{
		Post response=postService.createPost(post);
		System.out.println("Done");
		return new ResponseEntity<>("Post created successfully. Id -> "+response.getId(), HttpStatus.CREATED);
	}
	
	@GetMapping("/getPost/{id}")
	public ResponseEntity<?> getPostById(@PathVariable Long id)
	{
		try {
		Post post=postService.getPostById(id);
		
		return ResponseEntity.ok(post);
		}
		catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/getPosts")
	public List<Post> getAllPost()
	{
		return postService.getAllPost();
	}

	
	@PutMapping("/updatePost/{id}")
	public ResponseEntity<String> updatePost(@RequestBody Post post,@PathVariable long id)
	{
		postService.updatePost(post, id);
		return ResponseEntity.ok().body("Post updated Successfully.");
	}
	
	@DeleteMapping("/deletePost/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable long id)
	{
		postService.deletePostById(id);
		return ResponseEntity.ok().body("Post deleted successfully.");
	}
}
