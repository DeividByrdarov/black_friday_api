package com.dbardarov.blackfriday.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Document
public class UserSession {
  @Id
  private String id;

  @DBRef
  @NotNull
  private User user;

  @NotNull
  private Date expireAt;

  @NotNull
  private Date createdAt;

  public UserSession() {
  }

  public String getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public OffsetDateTime getExpireAt() {
    return expireAt.toInstant().atOffset(ZoneOffset.UTC);
  }

  public void setExpireAt(Date expireAt) {
    this.expireAt = expireAt;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt.toInstant().atOffset(ZoneOffset.UTC);
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
