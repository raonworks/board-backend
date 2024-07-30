package com.raonworks.boardback.repository;

import com.raonworks.boardback.data.entity.SearchLogEntity;
import com.raonworks.boardback.repository.resultSet.GetPopularListResultSet;
import com.raonworks.boardback.repository.resultSet.GetRelationListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer> {

  @Query("SELECT " +
          "searchWord AS searchWord, " +
          "count(searchWord) AS count " +
          "FROM search_log " +
          "WHERE relation IS FALSE " +
          "GROUP BY searchWord " +
          "ORDER BY count DESC LIMIT 15")
  public List<GetPopularListResultSet> getPopularList();

  @Query("SELECT " +
          "relationWord as searchWord, " +
          "count(relationWord) as count  " +
          "FROM search_log " +
          "WHERE searchWord=?1 " +
          "AND relationWord IS NOT NULL " +
          "GROUP BY relationWord " +
          "ORDER BY count DESC LIMIT 15")
  public List<GetRelationListResultSet> getRelationList(String searchWord);
}
