package com.blog.sak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.sak.bean.Post;
import com.blog.sak.dao.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;
	
	public Post createPost(Post post)
	{
		return postRepo.save(post);
	}
	
	public Post getPostById(long id)
	{
		return postRepo.findById(id).orElseThrow(() -> new RuntimeException(id+"-> This id doesn't exists"));
	}
	
	public List<Post> getAllPost()
	{
		return (List<Post>) postRepo.findAll();
	}
	
	public void updatePost(Post post,long id)
	{
		if(postRepo.findById(id).isPresent())
		{
			Post newPost=new Post();
			newPost.setId(id);
			newPost.setTitle(post.getTitle());
			newPost.setDescription(post.getDescription());
			  postRepo.save(newPost);
		}
		else
		{
			throw new RuntimeException(id+"-> This id doesn't exists."); 
		}
	}
	
	public void deletePostById(long id)
	{
		if(postRepo.findById(id).isPresent())
			postRepo.deleteById(id);
		else
			throw new RuntimeException(id+"-> This id doesn't exists.");
	}
}
