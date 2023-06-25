package com.course.mongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.mongodb.model.entities.Post;
import com.course.mongodb.repository.PostRepository;
import com.course.mongodb.service.exception.ObjectNotFoundException;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public Post findById(String id) {
    Optional<Post> post = postRepository.findById(id);
    return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
  }

  public List<Post> findByTitle(String text) {
    return postRepository.findByTitleContainingIgnoreCase(text);
  }

}
