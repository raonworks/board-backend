package com.raonworks.boardback.data.dto.response.board;


import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.Getter;

@Getter
public class PostBoardResponseDTO extends ResponseDTO {
  private PostBoardResponseDTO() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  }

  public static ResponseEntity<PostBoardResponseDTO> success() {
    PostBoardResponseDTO result = new PostBoardResponseDTO();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDTO> notExistUser() {
    ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
  }

}
