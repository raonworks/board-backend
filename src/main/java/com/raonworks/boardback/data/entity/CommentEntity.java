package com.raonworks.boardback.data.entity;

import com.raonworks.boardback.data.dto.request.board.PostCommentRequestDTO;
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
@Entity(name = "comment")
@Table(name = "comment")
public class CommentEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer commentNumber;
  private String content;
  private String writeDatetime;
  private String userEmail;
  private Integer boardNumber;

  public CommentEntity(PostCommentRequestDTO dto, Integer boardNumber, String userEmail) {
    Date now = Date.from(Instant.now());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String writeDatetime = simpleDateFormat.format(now);

    this.content = dto.getContent();
    this.writeDatetime = writeDatetime;
    this.userEmail = userEmail;
    this.boardNumber = boardNumber;
  }

}
