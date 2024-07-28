package com.raonworks.boardback.exception.custom;

public class NotExistsException extends RuntimeException {
  public NotExistsException(String errorMessage) {
    super(errorMessage, null, true, false);
  }
}
