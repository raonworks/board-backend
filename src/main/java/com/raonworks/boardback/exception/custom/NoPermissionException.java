package com.raonworks.boardback.exception.custom;

import com.raonworks.boardback.exception.ExceptionErrorCode;

public class NoPermissionException extends RuntimeException {

  public NoPermissionException() {
    super(ExceptionErrorCode.NO_PERMISSION.getMessage(), null, true, false);
  }

}
