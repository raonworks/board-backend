package com.raonworks.boardback.data.dto.response.board;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.object.FavoriteItemList;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.repository.resultSet.GetFavoriteListResultSet;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetFavoriteListResponseDTO extends ResponseDTO {

  private List<FavoriteItemList> favoriteList;

  private GetFavoriteListResponseDTO(List<GetFavoriteListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.favoriteList = FavoriteItemList.copyList(resultSets);
  }

  public static ResponseEntity<GetFavoriteListResponseDTO> success(List<GetFavoriteListResultSet> resultSets) {
    GetFavoriteListResponseDTO response = new GetFavoriteListResponseDTO(resultSets);
    return ResponseEntity.ok(response);
  }

  public static ResponseEntity<ResponseDTO> noExistBoard() {
    ResponseDTO response = new ResponseDTO(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
    return ResponseEntity.badRequest().body(response);
  }

}
