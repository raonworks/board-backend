package com.raonworks.boardback.data.dto.response.board;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.object.BoardListItem;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetSearchBoardListResponseDTO extends ResponseDTO {

  private List<BoardListItem> searchList;

  private GetSearchBoardListResponseDTO(List<BoardListViewEntity> listViewEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.searchList = BoardListItem.getList(listViewEntities);
  }

  public static ResponseEntity<GetSearchBoardListResponseDTO> success(List<BoardListViewEntity> listViewEntities) {
    GetSearchBoardListResponseDTO result = new GetSearchBoardListResponseDTO(listViewEntities);
    return ResponseEntity.ok(result);
  }

}
