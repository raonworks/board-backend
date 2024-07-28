package com.raonworks.boardback.data.dto.response.board;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.exception.ExceptionErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchBoardResponseDTO extends ResponseDTO {
  private PatchBoardResponseDTO() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  }

  public static ResponseEntity<PatchBoardResponseDTO> success() {
    PatchBoardResponseDTO result = new PatchBoardResponseDTO();
    return ResponseEntity.ok(result);
  }

  public static ResponseEntity<ResponseDTO> noExistBoard() {
    ResponseDTO result = new ResponseDTO(ExceptionErrorCode.NOT_EXIST_BOARD.getCode(), ExceptionErrorCode.NOT_EXIST_USER.getMessage());
    return ResponseEntity.badRequest().body(result);
  }

  public static ResponseEntity<ResponseDTO> noExistUser() {
    ResponseDTO result = new ResponseDTO(ExceptionErrorCode.NOT_EXIST_USER.getCode(), ExceptionErrorCode.NOT_EXIST_USER.getMessage());
    return ResponseEntity.badRequest().body(result);
  }

  public static ResponseEntity<ResponseDTO> noPermission() {
    ResponseDTO result = new ResponseDTO(ExceptionErrorCode.NO_PERMISSION.getCode(), ExceptionErrorCode.NO_PERMISSION.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
  }

}
