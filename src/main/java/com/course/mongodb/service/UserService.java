package com.course.mongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.mongodb.model.dto.UserDTO;
import com.course.mongodb.model.entities.User;
import com.course.mongodb.repository.UserRepository;
import com.course.mongodb.service.exception.ObjectNotFoundException;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(String id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
  }

  public User insert(User user) {
    return userRepository.insert(user);
  }

  public void deleteById(String id) {
    findById(id);
    userRepository.deleteById(id);
  }

  public User update(User user) {

    User userToUpdate = findById(user.getId());
    updateData(userToUpdate, user);

    return userRepository.save(userToUpdate);
  }

  private void updateData(User userToUpdate, User user) {
    userToUpdate.setName(user.getEmail());
    userToUpdate.setEmail(user.getName());
  }

  public User fromDTO(UserDTO userDTO) {
    return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
  }

}