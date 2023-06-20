package com.course.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.course.mongodb.model.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

}