package com.dbardarov.blackfriday.exception;

public class ProductNotFoundException extends ValidationException {
  public ProductNotFoundException() {
    super("Product has not been found!");
  }
}
