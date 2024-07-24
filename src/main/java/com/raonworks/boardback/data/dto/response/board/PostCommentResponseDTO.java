package com.raonworks.boardback.data.dto.response.board;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostCommentResponseDTO extends ResponseDTO {

  private PostCommentResponseDTO() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  }

  public static ResponseEntity<PostCommentResponseDTO> success() {
    PostCommentResponseDTO dto = new PostCommentResponseDTO();
    return ResponseEntity.ok(dto);
  }

  public static ResponseEntity<ResponseDTO> noExistBoard() {
    ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
    return ResponseEntity.badRequest().body(result);
  }

  public static ResponseEntity<ResponseDTO> noExistUser() {
    return PostBoardResponseDTO.notExistUser();
  }

}
