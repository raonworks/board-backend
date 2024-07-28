package com.raonworks.boardback.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Immutable //readonly option
@Entity(name = "board_list_view")
@Table(name = "board_list_view")
@Subselect(
        //Subselect를 사용하면 view 테이블이 생성은 되지 않고 내부적으로 사용할 수만 있다.
        "SELECT B.board_number AS board_number, " +
                "B.title AS title, " +
                "B.content AS content, " +
                "B.view_count AS view_count, " +
                "B.favorite_count AS favorite_count, " +
                "B.comment_count AS comment_count, " +
                "B.write_date_time AS write_datetime, " +
                "U.email AS writer_email, " +
                "U.nickname AS writer_nickname, " +
                "U.profile_image AS writer_profile_image, " +
                "I.image AS title_image " +
                "FROM board as B " +
                "INNER JOIN user AS U ON B.writer_email = U.email " +
                "LEFT JOIN (SELECT board_number, ANY_VALUE(image) AS image FROM image GROUP BY board_number) AS I " +
                "ON B.board_number = I.board_number")

//Board + User 정보를 함께 표현하는 view table.
public class BoardListViewEntity {
  @Id
  private Integer boardNumber;
  private String title;
  private String content;
  private String titleImage;
  private Integer viewCount;
  private Integer favoriteCount;
  private Integer commentCount;

  private String writeDatetime;
  private String writerEmail;
  private String writerNickname;
  private String writerProfileImage;
}

//CREATE VIEW board_list_view AS SELECT
//    B.board_number AS board_number,
//    B.title AS title,
//    B.content AS content,
//    I.image AS title_image,
//    B.view_count AS view_count,
//    B.favorite_count AS favorite_count,
//    B.comment_count AS comment_count,
//    B.write_date_time AS write_datetime,
//    U.email AS writer_email,
//    U.nickname AS writer_nickname,
//    U.profile_image AS writer_profile_image
//FROM board AS B
//INNER JOIN user AS U
//ON B.writer_email = U.email
//LEFT JOIN (SELECT board_number, ANY_VALUE(image) AS image FROM image GROUP BY board_number) AS I
//ON B.board_number = I.board_number;
