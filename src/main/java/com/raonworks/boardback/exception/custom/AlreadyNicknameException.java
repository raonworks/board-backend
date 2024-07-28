package com.raonworks.boardback.exception.custom;

import com.raonworks.boardback.exception.ExceptionErrorCode;

public class AlreadyNicknameException extends AlreadyExistException {
  public AlreadyNicknameException() {
    super(ExceptionErrorCode.DUPLICATE_NICKNAME.getMessage());
  }
}
