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
public class GetLatestBoardListResponseDTO extends ResponseDTO {

  private List<BoardListItem> latestList;

  private GetLatestBoardListResponseDTO(List<BoardListViewEntity> boardEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.latestList = BoardListItem.getList(boardEntities);
  }

  public static ResponseEntity<GetLatestBoardListResponseDTO> suceess(List<BoardListViewEntity> entities) {
    GetLatestBoardListResponseDTO result = new GetLatestBoardListResponseDTO(entities);
    return ResponseEntity.ok(result);
  }
}
