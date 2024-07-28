package com.raonworks.boardback.exception.custom;

import com.raonworks.boardback.exception.ExceptionErrorCode;

public class NotExistsBoardException extends NotExistsException {
  public NotExistsBoardException() {
    super(ExceptionErrorCode.NOT_EXIST_BOARD.getMessage());
  }
}
