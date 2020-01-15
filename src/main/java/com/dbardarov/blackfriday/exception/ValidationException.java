package com.dbardarov.blackfriday.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class ValidationException extends RuntimeException implements GraphQLError {
  private String message;

  public ValidationException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  @JsonIgnore
  public StackTraceElement[] getStackTrace() {
    return super.getStackTrace();
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.ValidationError;
  }
}
