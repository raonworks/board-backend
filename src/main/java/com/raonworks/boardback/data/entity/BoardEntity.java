package com.raonworks.boardback.data.entity;

import com.raonworks.boardback.data.dto.request.board.PostBoardRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Table(name = "board")
public class BoardEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int boardNumber;
  private String title;
  private String content;
  private String writeDateTime;
  private Integer favoriteCount;
  private Integer commentCount;
  private Integer viewCount;
  private String WriterEmail;

  public BoardEntity(PostBoardRequestDTO dto, String email) {
    Date now = Date.from(Instant.now());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String writeDatetime = simpleDateFormat.format(now);

    this.title = dto.getTitle();
    this.content = dto.getContent();
    this.writeDateTime = writeDatetime;
    this.favoriteCount = 0;
    this.commentCount = 0;
    this.viewCount = 0;
    this.WriterEmail = email;
  }

  public void increaseViewCount() {
    this.viewCount++;
  }

  public void inceaseFavoriteCount() {
    this.favoriteCount++;
  }

  public void deceaseFavoriteCount() {
    this.favoriteCount--;
  }

}
