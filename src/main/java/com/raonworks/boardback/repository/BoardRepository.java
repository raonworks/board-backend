package com.raonworks.boardback.repository;

import com.raonworks.boardback.data.entity.BoardEntity;
import com.raonworks.boardback.repository.resultSet.GetBoardResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

  BoardEntity findByBoardNumber(Integer boardNumber);

  @Query(value =
          "SELECT" +
          "   B.board_number as boardNumber," +
          "   B.title as title," +
          "   B.content as content," +
          "   B.writer_email as writerEmail," +
          "   B.write_date_time as writeDateTime," +
          "   U.nickname as writerNickname," +
          "   U.profile_image as writerProfileImage " +
          "FROM board AS B" +
          "   INNER JOIN user AS U ON B.writer_email=U.email " +
          "WHERE board_number = ?1", nativeQuery = true)
  GetBoardResultSet getBoard(Integer boardNum);
}
