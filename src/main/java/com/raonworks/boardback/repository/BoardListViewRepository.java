package com.raonworks.boardback.repository;

import com.raonworks.boardback.data.entity.BoardListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {
  List<BoardListViewEntity> findByOrderByWriteDatetimeDesc();

  //특정일보다 큰 데이터 중에서 최신 3개 목록 불러오기 
  List<BoardListViewEntity> findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(String WriteDatetime);

  List<BoardListViewEntity> findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(String title, String content);
}
