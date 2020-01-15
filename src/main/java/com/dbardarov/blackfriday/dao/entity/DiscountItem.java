package com.dbardarov.blackfriday.dao.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class DiscountItem {
  private double discount;
  @DBRef
  private Product product;

  public DiscountItem() {
  }

  public DiscountItem(double discount, Product product) {
    this.discount = discount;
    this.product = product;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
