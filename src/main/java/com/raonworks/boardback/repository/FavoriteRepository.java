package com.raonworks.boardback.repository;

import com.raonworks.boardback.data.entity.FavoriteEntity;
import com.raonworks.boardback.data.entity.primaryKey.FavoritePk;
import com.raonworks.boardback.repository.resultSet.GetFavoriteListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {
  FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber, String userEmail);

  @Query(value =
          "SELECT" +
          "    U.email," +
          "    U.nickname," +
          "    U.profile_image as profileImage " +
          "FROM favorite as F " +
          "INNER JOIN user as U ON F.user_email=U.email " +
          "WHERE F.board_number=?1", nativeQuery = true)
  List<GetFavoriteListResultSet> getFavoriteList(Integer boardNum);

  @Transactional
  void deleteByBoardNumber(Integer boardNumber);
}
