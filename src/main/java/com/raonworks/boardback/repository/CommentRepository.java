package com.raonworks.boardback.repository;

import com.raonworks.boardback.data.entity.CommentEntity;
import com.raonworks.boardback.repository.resultSet.GetCommentListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

  @Query(value =
          "SELECT" +
          "    U.nickname," +
          "    U.profile_image as profileImage," +
          "    C.write_datetime as writeDatetime," +
          "    C.content " +
          "FROM comment as C " +
          "INNER JOIN user U ON C.user_email = U.email " +
          "WHERE C.board_number=?1 " +
          "ORDER BY write_datetime", nativeQuery = true)
  List<GetCommentListResultSet> getCommentList(Integer boardNumber);
}
