package com.raonworks.boardback.repository;

import com.raonworks.boardback.data.entity.BoardListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {
  List<BoardListViewEntity> findByOrderByWriteDatetimeDesc();
}
