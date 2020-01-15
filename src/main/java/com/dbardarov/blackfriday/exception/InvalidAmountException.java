package com.dbardarov.blackfriday.exception;

public class InvalidAmountException extends ValidationException {
  public InvalidAmountException(String productName) {
    super("Not enough items from " + productName + " in stock!");
  }
}
