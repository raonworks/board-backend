package com.raonworks.boardback.exception;

import com.raonworks.boardback.common.ErrorCode;
import com.raonworks.boardback.exception.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j(topic = "NOMAD.CODE")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  public static final String TRACE = "trace";

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    return super.handleExceptionInternal(ex, body, headers, statusCode, request);
  }

  private ResponseEntity<Object> buildErrorResponse(Exception exception,
                                                    HttpStatus httpStatus,
                                                    String code,
                                                    String message,
                                                    WebRequest request) {
    ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(httpStatus.value(), code, message);
    return ResponseEntity.status(httpStatus).body(errorResponseDTO);
  }

  //이메일 중복 예외처리
  @ExceptionHandler(AlreadyEmailExistException.class)
  public ResponseEntity<Object> handleAlreadyEmailException(AlreadyEmailExistException exception, WebRequest request) {
    log.error(exception.getMessage(), exception);
    return buildErrorResponse(exception, HttpStatus.CONFLICT, ErrorCode.DUPLICATE_EMAIL.getCode(), ErrorCode.DUPLICATE_EMAIL.getMessage(), request);
  }

}
