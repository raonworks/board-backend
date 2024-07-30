package com.raonworks.boardback.data.dto.response.board;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.object.BoardListItem;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.dto.response.user.GetUserResponseDTO;
import com.raonworks.boardback.data.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetUserBoardListResponseDTO extends ResponseDTO {

  private List<BoardListItem> userBoardList;

  private GetUserBoardListResponseDTO(List<BoardListViewEntity> userBoardListView) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    userBoardList = BoardListItem.getList(userBoardListView);
  }

  public static ResponseEntity<GetUserBoardListResponseDTO> success(List<BoardListViewEntity> entities) {
    GetUserBoardListResponseDTO result = new GetUserBoardListResponseDTO(entities);
    return ResponseEntity.ok(result);
  }

  public static ResponseEntity<ResponseDTO> noExistUser() {
    return GetUserResponseDTO.noExist();
  }
  
}
