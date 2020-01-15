package com.dbardarov.blackfriday.input;

public class ReceiptItemInput {
  private int amount;
  private String productId;

  public ReceiptItemInput() {
  }

  public ReceiptItemInput(int amount, String productId) {
    this.amount = amount;
    this.productId = productId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }
}
