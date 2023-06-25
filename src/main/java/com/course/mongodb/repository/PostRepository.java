package com.course.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.course.mongodb.model.entities.Post;

public interface PostRepository extends MongoRepository<Post, String> {

  public List<Post> findByTitleContainingIgnoreCase(String title);
}