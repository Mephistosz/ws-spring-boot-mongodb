package com.course.mongodb.model.entities;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.course.mongodb.model.dto.UserDTO;

@Document(value = "post")
public class Post implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String id;
  private Instant date;
  private String title;
  private String body;
  private UserDTO author;

  public Post() {
  }

  public Post(String id, Instant date, String title, String body, UserDTO author) {
    this.id = id;
    this.date = date;
    this.title = title;
    this.body = body;
    this.author = author;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Instant getDate() {
    return date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public UserDTO getUser() {
    return author;
  }

  public void setUser(UserDTO author) {
    this.author = author;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Post other = (Post) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
