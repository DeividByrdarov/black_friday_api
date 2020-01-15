package com.dbardarov.blackfriday.exception;

public class PasswordsDoNotMatchException extends ValidationException {
  public PasswordsDoNotMatchException() {
    super("Passwords do not match!");
  }
}
