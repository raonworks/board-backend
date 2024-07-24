package com.raonworks.boardback.data.dto.response.board;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.object.CommentListItem;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.repository.resultSet.GetCommentListResultSet;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetCommentListResponseDTO extends ResponseDTO {

  private List<CommentListItem> commentList;

  private GetCommentListResponseDTO(List<GetCommentListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.commentList = CommentListItem.copyList(resultSets);
  }

  public static ResponseEntity<GetCommentListResponseDTO> success(List<GetCommentListResultSet> resultSets) {
    GetCommentListResponseDTO dto = new GetCommentListResponseDTO(resultSets);
    return ResponseEntity.ok(dto);
  }

  public static ResponseEntity<ResponseDTO> noExistBoard() {
    return PostCommentResponseDTO.noExistBoard();
  }

}
