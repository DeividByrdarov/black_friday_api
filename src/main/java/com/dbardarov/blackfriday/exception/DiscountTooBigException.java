package com.dbardarov.blackfriday.exception;

import com.dbardarov.blackfriday.dao.entity.Product;

public class DiscountTooBigException extends ValidationException {
  public DiscountTooBigException(Product product) {
    super("Discount for product " + product.getName() + " is too big!");
  }
}
