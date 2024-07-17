package com.raonworks.boardback.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board_list_view")
@Table(name = "board_list_view")
public class BoardListViewEntity {
  @Id
  private Integer boardNumber;
  private String title;
  private String content;
  private String titleImage;
  private Integer viewCount;
  private Integer favoriteCount;
  private Integer commentCount;;
  private String writeDatetime;
  private String writerEmail;
  private String writerNickname;
  private String writerProfileImage;
}
