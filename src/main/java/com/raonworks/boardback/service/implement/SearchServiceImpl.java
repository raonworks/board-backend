package com.raonworks.boardback.service.implement;

import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.dto.response.search.GetPopularListResponseDTO;
import com.raonworks.boardback.data.dto.response.search.GetRelationListResponseDTO;
import com.raonworks.boardback.exception.custom.DatabaseException;
import com.raonworks.boardback.repository.SearchLogRepository;
import com.raonworks.boardback.repository.resultSet.GetPopularListResultSet;
import com.raonworks.boardback.repository.resultSet.GetRelationListResultSet;
import com.raonworks.boardback.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

  private static final Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);
  private final SearchLogRepository searchLogRepository;

  @Override
  public ResponseEntity<? super GetPopularListResponseDTO> getPopularList() {
    List<GetPopularListResultSet> resultSets;

    try {
      resultSets = searchLogRepository.getPopularList();
    } catch (Exception e) {
      e.printStackTrace();
      throw new DatabaseException();
    }

    return GetPopularListResponseDTO.success(resultSets);
  }

  @Override
  public ResponseEntity<? super GetRelationListResponseDTO> getRelationList(String searchWord) {

    List<GetRelationListResultSet> resultSets;

    try {
      resultSets = searchLogRepository.getRelationList(searchWord);
      
    } catch (Exception e) {
      e.printStackTrace();
      throw new DatabaseException();
    }

    return GetRelationListResponseDTO.success(resultSets);
  }

}
