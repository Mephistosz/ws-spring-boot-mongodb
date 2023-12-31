package com.course.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.course.mongodb.model.entities.Post;

public interface PostRepository extends MongoRepository<Post, String> {

  @Query("{title:{$regex:/?0/,$options:i}}")
  public List<Post> findByTitle(String text);

  public List<Post> findByTitleContainingIgnoreCase(String title);
}