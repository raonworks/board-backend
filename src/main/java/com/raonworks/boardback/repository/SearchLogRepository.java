package com.raonworks.boardback.repository;

import com.raonworks.boardback.data.entity.SearchLogEntity;
import com.raonworks.boardback.repository.resultSet.GetPopularListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer> {

  @Query("SELECT searchWord as searchWord, count(searchWord) as count " +
          "FROM search_log " +
          "WHERE relation is false " +
          "GROUP BY searchWord " +
          "ORDER BY count DESC LIMIT 15")
  public List<GetPopularListResultSet> getPopularList();
}
