package com.raonworks.boardback.service;

import com.raonworks.boardback.data.dto.response.search.GetPopularListResponseDTO;
import com.raonworks.boardback.data.dto.response.search.GetRelationListResponseDTO;
import org.springframework.http.ResponseEntity;

public interface SearchService {

  ResponseEntity<? super GetPopularListResponseDTO> getPopularList();

  ResponseEntity<? super GetRelationListResponseDTO> getRelationList(String searchWord);
}
