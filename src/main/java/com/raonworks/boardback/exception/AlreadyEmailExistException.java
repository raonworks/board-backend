package com.raonworks.boardback.exception;

import com.raonworks.boardback.common.ErrorCode;

public class AlreadyEmailExistException extends AlreadyExistException {

  public AlreadyEmailExistException() {
    super(ErrorCode.DUPLICATE_EMAIL.getMessage());
  }
}
