package com.dbardarov.blackfriday.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Receipt {
  @Id
  private String id;
  private double total;
  @DBRef
  @Indexed(unique = false, name = "user.username")
  private User user;
  private ArrayList<ReceiptItem> items;

  public Receipt() {
  }

  public String getId() {
    return id;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public ArrayList<ReceiptItem> getItems() {
    return items;
  }

  public void setItems(ArrayList<ReceiptItem> items) {
    this.items = items;
  }
}
