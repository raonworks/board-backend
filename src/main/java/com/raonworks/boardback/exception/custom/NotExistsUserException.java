package com.raonworks.boardback.exception.custom;

import com.raonworks.boardback.exception.ExceptionErrorCode;

public class NotExistsUserException extends NotExistsException {
  public NotExistsUserException() {
    super(ExceptionErrorCode.NOT_EXIST_USER.getMessage());
  }
}
