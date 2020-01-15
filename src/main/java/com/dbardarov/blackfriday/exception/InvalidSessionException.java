package com.dbardarov.blackfriday.exception;

public class InvalidSessionException extends ValidationException {
  public InvalidSessionException() {
    super("Session with this ID can't be found");
  }
}
