package com.raonworks.boardback.common;

public interface ResponseMessage {
  String SUCCESS = "SU";

  // HTTP Status 400
  String VALIDATION_FAILED = "Validation Failed";
  String DUPLICATE_EMAIL = "Duplicate Email";
  String DUPLICATE_NICKNAME = "Duplicate Nickname";
  String DUPLICATE_TEL_NUMBER = "Duplicate Tel Number";
  String NOT_EXIST_USER = "This user does not exist";
  String NOT_EXIST_BOARD = "This board does not exist";

  // HTTP Status 401
  String SIGN_IN_FAILED = "Login Failed";
  String AUTHORIZATION_FAILED = "Authorization Failed";

  // HTTP Status 403
  String NO_PERMISSION = "Do not have permission";

  // HTPP Status 500
  String DATABASE_ERROR = "Database Error";
}
