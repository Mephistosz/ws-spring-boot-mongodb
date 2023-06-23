package com.course.mongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.course.mongodb.model.dto.UserDTO;
import com.course.mongodb.model.entities.Post;
import com.course.mongodb.model.entities.User;
import com.course.mongodb.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {

    List<User> list = userService.findAll();

    List<UserDTO> listDto = list.stream().map(UserDTO::new)
        .collect(Collectors.toList());

    return ResponseEntity.ok().body(listDto);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {

    User user = userService.findById(id);

    return ResponseEntity.ok().body(new UserDTO(user));
  }

  @GetMapping("/{id}/posts")
  public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {

    User user = userService.findById(id);

    return ResponseEntity.ok().body(user.getPosts());
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {

    User user = userService.fromDTO(userDTO);
    userService.insert(user);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId()).toUri();

    return ResponseEntity.created(uri).build();
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO userChanges) {

    User user = userService.fromDTO(userChanges);
    user.setId(id);

    userService.update(user);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {

    userService.deleteById(id);

    return ResponseEntity.noContent().build();
  }

}
