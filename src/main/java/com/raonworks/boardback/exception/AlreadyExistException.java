package com.raonworks.boardback.exception;

public class AlreadyExistException extends RuntimeException {

  public AlreadyExistException(String errorMessage) {
    super(errorMessage, null, true, false);
  }
}
