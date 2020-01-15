package com.dbardarov.blackfriday.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class Campaign {
  @Id
  private String id;
  private String name;
  private String description;
  private boolean active;
  private ArrayList<DiscountItem> items;

  public Campaign() {
  }

  public Campaign(String name, String description, boolean active, ArrayList<DiscountItem> items) {
    this.name = name;
    this.description = description;
    this.active = active;
    this.items = items;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public ArrayList<DiscountItem> getItems() {
    return items;
  }

  public void setItems(ArrayList<DiscountItem> items) {
    this.items = items;
  }
}
