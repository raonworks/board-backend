package com.raonworks.boardback.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
