package com.raonworks.boardback.data.dto.response.board;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.entity.ImageEntity;
import com.raonworks.boardback.repository.resultSet.GetBoardResultSet;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetBoardResponseDTO extends ResponseDTO {
  private final int boardNumber;
  private final String title;
  private final String content;
  private final List<String> boardImageList;
  private final String writeDatetime;
  private final String writerEmail;
  private final String writerNickname;
  private final String writerProfileImage;

  private GetBoardResponseDTO(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    List<String> boardImageList = new ArrayList<>();
    for(ImageEntity imageEntity : imageEntities) {
      String boardImage = imageEntity.getImage();
      boardImageList.add(boardImage);
    }

    this.boardNumber = resultSet.getBoardNumber();
    this.title = resultSet.getTitle();
    this.content = resultSet.getContent();
    this.boardImageList = boardImageList;
    this.writeDatetime = resultSet.getWriteDateTime();
    this.writerEmail = resultSet.getWriterEmail();
    this.writerNickname = resultSet.getWriterNickname();
    this.writerProfileImage = resultSet.getWriterProfileImage();
  }

  public static ResponseEntity<GetBoardResponseDTO> success(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
    GetBoardResponseDTO dto = new GetBoardResponseDTO(resultSet, imageEntities);
    return ResponseEntity.status(HttpStatus.OK).body(dto);
  }

  public static ResponseEntity<ResponseDTO> notExistBoard() {
    ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_BOARD, ResponseMessage.NOT_EXIST_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

}
