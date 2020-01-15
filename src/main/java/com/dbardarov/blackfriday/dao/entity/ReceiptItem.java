package com.dbardarov.blackfriday.dao.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class ReceiptItem {
  private int amount;
  @DBRef
  private Product product;

  public ReceiptItem() {
  }

  public ReceiptItem(int amount, Product product) {
    this.amount = amount;
    this.product = product;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
