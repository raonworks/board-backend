package com.raonworks.boardback.data.dto.response.auth;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDTO extends ResponseDTO {

  private String token;
  private Integer expirationTime;

  private SignInResponseDTO(String token) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.token = token;
    this.expirationTime = 3600;
  }

  public static ResponseEntity<SignInResponseDTO> success(String token) {
    SignInResponseDTO result = new SignInResponseDTO(token);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDTO> SignInFailed() {
    ResponseDTO result = new ResponseDTO(ResponseCode.SIGN_IN_FAILED, ResponseMessage.SIGN_IN_FAILED);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
  }
}
