package com.raonworks.boardback.service.implement;

import com.raonworks.boardback.data.dto.response.search.GetPopularListResponseDTO;
import com.raonworks.boardback.repository.SearchLogRepository;
import com.raonworks.boardback.repository.resultSet.GetPopularListResultSet;
import com.raonworks.boardback.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

  private final SearchLogRepository searchLogRepository;

  @Override
  public ResponseEntity<? super GetPopularListResponseDTO> getPopularList() {

    List<GetPopularListResultSet> resultSets = new ArrayList<>();
    resultSets = searchLogRepository.getPopularList();

    return GetPopularListResponseDTO.success(resultSets);
  }

}
