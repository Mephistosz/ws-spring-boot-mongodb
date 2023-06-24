package com.course.mongodb.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.course.mongodb.model.dto.AuthorDTO;
import com.course.mongodb.model.dto.CommentDTO;
import com.course.mongodb.model.entities.Post;
import com.course.mongodb.model.entities.User;
import com.course.mongodb.repository.PostRepository;
import com.course.mongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PostRepository postRepository;

  @Override
  public void run(String... args) throws Exception {

    userRepository.deleteAll();
    postRepository.deleteAll();

    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");

    userRepository.saveAll(Arrays.asList(maria, alex, bob));

    Post p1 = new Post(null, Instant.now(), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",
        new AuthorDTO(maria));
    Post p2 = new Post(null, Instant.now(), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

    postRepository.saveAll(Arrays.asList(p1, p2));

    CommentDTO c1 = new CommentDTO("Boa viagem mano!", Instant.now(), new AuthorDTO(alex));
    CommentDTO c2 = new CommentDTO("Aproveite!", Instant.now(), new AuthorDTO(bob));
    CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", Instant.now(), new AuthorDTO(alex));

    p1.getCommets().addAll(Arrays.asList(c1, c2));
    p2.getCommets().addAll(Arrays.asList(c3));
    postRepository.saveAll(Arrays.asList(p1, p2));

    maria.getPosts().addAll(Arrays.asList(p1, p2));
    userRepository.save(maria);

  }

}
