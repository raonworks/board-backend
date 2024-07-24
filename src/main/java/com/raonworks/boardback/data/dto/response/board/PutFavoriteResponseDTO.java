package com.raonworks.boardback.data.dto.response.board;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PutFavoriteResponseDTO extends ResponseDTO {

  private PutFavoriteResponseDTO() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

  }

  public static ResponseEntity<PutFavoriteResponseDTO> success() {
    PutFavoriteResponseDTO result = new PutFavoriteResponseDTO();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDTO> noExistBoard() {
    return GetBoardResponseDTO.notExistBoard();
  }

  public static ResponseEntity<ResponseDTO> notExistUser() {
    return PostBoardResponseDTO.notExistUser();
  }
}
