package com.raonworks.boardback.data.dto.response.board;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InceaseViewCountResponseDTO extends ResponseDTO {

  private InceaseViewCountResponseDTO() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  }

  public static ResponseEntity<InceaseViewCountResponseDTO> success() {
    InceaseViewCountResponseDTO result = new InceaseViewCountResponseDTO();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDTO> noExistBoard() {
    return PostCommentResponseDTO.noExistBoard();
  }

}
