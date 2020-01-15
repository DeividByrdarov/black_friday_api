package com.dbardarov.blackfriday.exception;

public class UserNotFoundException extends ValidationException {

  public UserNotFoundException() {
    super("User with this ID can't be found!");
  }
}
