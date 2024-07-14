package com.raonworks.boardback.common;

public interface ResponseCode {
  // HTTP Status 200
  String SUCCESS = "SU";

  // HTTP Status 400
  String VALIDATION_FAILED = "VF";
  String DUPLICATE_EMAIL = "DE";
  String DUPLICATE_NICKNAME = "DN";
  String DUPLICATE_TEL_NUMBER = "DT";
  String NOT_EXIST_USER = "NU";
  String NOT_EXIST_BOARD = "NB";

  // HTTP Status 401
  String SIGN_IN_FAILED = "SF";
  String AUTHORIZATION_FAILED = "AF";

  // HTTP Status 403
  String NO_PERMISSION = "NP";

  // HTPP Status 500
  String DATABASE_ERROR = "DBE";
}
