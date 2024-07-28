package com.raonworks.boardback.exception;

import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.exception.custom.*;
import com.raonworks.boardback.exception.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j(topic = "NOMAD.CODE")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//  public static final String TRACE = "trace";

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

//  // 412 Validate Exception
//  @Override
//  @Hidden
//  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
//  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//    ErrorResponseDto errorResponseDto = new ErrorResponseDto(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error. Check 'errors' field for details.", LocalDateTime.now());
//    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
//      errorResponseDto.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
//    }
//    return ResponseEntity.unprocessableEntity().body(errorResponseDto);
//  }

  //접근권한 없음 예외처리
  @ExceptionHandler(NoPermissionException.class)
  public ResponseEntity<Object> handleNoPermissionException(NoPermissionException exception, WebRequest request) {
    log.error(exception.getMessage(), exception);
    return buildErrorResponse(exception, HttpStatus.FORBIDDEN, ExceptionErrorCode.NO_PERMISSION.getCode(), ExceptionErrorCode.NO_PERMISSION.getMessage(), request);
  }

  //이메일 중복 예외처리
  @ExceptionHandler(AlreadyEmailExistException.class)
  public ResponseEntity<Object> handleAlreadyEmailException(AlreadyEmailExistException exception, WebRequest request) {
    log.error(exception.getMessage(), exception);
    return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, ExceptionErrorCode.DUPLICATE_EMAIL.getCode(), ExceptionErrorCode.DUPLICATE_EMAIL.getMessage(), request);
  }

  //닉네임 중복 예외처리
  @ExceptionHandler(AlreadyNicknameException.class)
  public ResponseEntity<Object> handleAlreadyNicknameException(AlreadyNicknameException exception, WebRequest request) {
    log.error(exception.getMessage(), exception);
    return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, ExceptionErrorCode.DUPLICATE_NICKNAME.getCode(), ExceptionErrorCode.DUPLICATE_EMAIL.getMessage(), request);
  }

  //사용자 없음 예외처리
  @ExceptionHandler(NotExistsUserException.class)
  public ResponseEntity<Object> handleNotExistsUserException(NotExistsUserException exception, WebRequest request) {
    log.error(exception.getMessage(), exception);
    return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, ExceptionErrorCode.NOT_EXIST_USER.getCode(), ExceptionErrorCode.NOT_EXIST_USER.getMessage(), request);
  }

  //게시물 없음 예외처리
  @ExceptionHandler(NotExistsBoardException.class)
  public ResponseEntity<Object> handleNotExistsBoardException(NotExistsBoardException exception, WebRequest request) {
    log.error(exception.getMessage(), exception);
    return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, ExceptionErrorCode.NOT_EXIST_BOARD.getCode(), ExceptionErrorCode.NOT_EXIST_BOARD.getMessage(), request);
  }

}
