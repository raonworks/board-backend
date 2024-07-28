package com.raonworks.boardback.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionErrorCode {
  SUCCESS("SU", "Success"),
  VALIDATION_FAILED("VF", "Validation Failed"),
  DUPLICATE_EMAIL("DE", "Duplicate Email"),
  DUPLICATE_NICKNAME("DN", "Duplicate Nickname"),
  DUPLICATE_TEL_NUMBER("DT", "Duplicate Tel Number"),
  NOT_EXIST_USER("NU", "This user does not exist"),
  NOT_EXIST_BOARD("NB", "This board does not exist"),

  // HTTP Status 401
  SIGN_IN_FAILED("SF", "Login Failed"),
  AUTHORIZATION_FAILED("AF", "Authorization Failed"),

  // HTTP Status 403
  NO_PERMISSION("NP", "Do not have permission"),

  // HTPP Status 500
  DATABASE_ERROR("DBE", "Database Error"),
  ;

  private final String code;
  private final String message;
};
