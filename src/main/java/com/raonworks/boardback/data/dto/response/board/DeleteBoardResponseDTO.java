package com.raonworks.boardback.data.dto.response.board;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class DeleteBoardResponseDTO extends ResponseDTO {

  private DeleteBoardResponseDTO() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  }

  public static ResponseEntity<DeleteBoardResponseDTO> success() {
    DeleteBoardResponseDTO response = new DeleteBoardResponseDTO();
    return ResponseEntity.ok(response);
  }

  public static ResponseEntity<ResponseDTO> noExistBoard() {
    ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
    return ResponseEntity.badRequest().body(result);
  }

  public static ResponseEntity<ResponseDTO> noExistUser() {
    ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
    return ResponseEntity.badRequest().body(result);
  }

  public static ResponseEntity<ResponseDTO> noPermission() {
    ResponseDTO result = new ResponseDTO(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
  }

}
