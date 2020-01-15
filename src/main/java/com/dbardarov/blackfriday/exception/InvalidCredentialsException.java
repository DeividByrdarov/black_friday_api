package com.dbardarov.blackfriday.exception;

public class InvalidCredentialsException extends ValidationException {
  public InvalidCredentialsException() {
    super("Invalid credentials provided!");
  }
}
