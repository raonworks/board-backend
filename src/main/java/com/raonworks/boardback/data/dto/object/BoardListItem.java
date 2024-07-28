package com.raonworks.boardback.data.dto.object;

import com.raonworks.boardback.data.entity.BoardListViewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardListItem {
  private int boardNumber;
  private String title;
  private String content;
  private String boardTitleImage;
  private int favoriteCount;
  private int commentCount;
  private int viewCount;
  private String writeDatetime;
  private String writeNickname;
  private String writeProfileImage;

  public BoardListItem(BoardListViewEntity entity) {
    this.boardNumber = entity.getBoardNumber();
    this.title = entity.getTitle();
    this.content = entity.getContent();
    this.boardTitleImage = entity.getTitleImage();
    this.favoriteCount = entity.getFavoriteCount();
    this.commentCount = entity.getCommentCount();
    this.viewCount = entity.getViewCount();
    this.writeDatetime = entity.getWriteDatetime();
    this.writeNickname = entity.getWriterNickname();
    this.writeProfileImage = entity.getWriterProfileImage();
  }

  public static List<BoardListItem> getList(List<BoardListViewEntity> entities) {
    List<BoardListItem> list = new ArrayList<>();
    for (BoardListViewEntity entity : entities) {
      list.add(new BoardListItem(entity));
    }

    return list;
  }
}
