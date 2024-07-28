package com.raonworks.boardback.exception.custom;

import com.raonworks.boardback.exception.ExceptionErrorCode;

public class AlreadyEmailExistException extends AlreadyExistException {

  public AlreadyEmailExistException() {
    super(ExceptionErrorCode.DUPLICATE_EMAIL.getMessage());
  }
}
