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
public class GetTop3BoardListResponseDTO extends ResponseDTO {

  private final List<BoardListItem> top3BoardList;

  private GetTop3BoardListResponseDTO(List<BoardListViewEntity> entities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.top3BoardList = BoardListItem.getList(entities);
  }

  public static ResponseEntity<GetTop3BoardListResponseDTO> success(List<BoardListViewEntity> entities) {
    return ResponseEntity.ok(new GetTop3BoardListResponseDTO(entities));
  }

}
