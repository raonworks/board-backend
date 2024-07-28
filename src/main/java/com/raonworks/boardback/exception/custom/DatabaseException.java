package com.raonworks.boardback.exception.custom;

import com.raonworks.boardback.exception.ExceptionErrorCode;

public class DatabaseException extends RuntimeException {

  public DatabaseException() {
    super(ExceptionErrorCode.DATABASE_ERROR.getMessage(), null, true, false);
  }
}
