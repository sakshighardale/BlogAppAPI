package com.blog.sak.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blog.sak.bean.Post;
@Repository
public interface PostRepository extends CrudRepository<Post, Long>{

}
