package com.raonworks.boardback.repository;

import com.raonworks.boardback.data.entity.FavoriteEntity;
import com.raonworks.boardback.data.entity.primaryKey.FavoritePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {
  FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber, String userEmail);

}
