package com.dbardarov.blackfriday.input;

public class DiscountItemInput {
  private double discount;
  private String productId;

  public DiscountItemInput() {
  }

  public DiscountItemInput(double discount, String productId) {
    this.discount = discount;
    this.productId = productId;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }
}
