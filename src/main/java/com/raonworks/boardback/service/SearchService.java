package com.raonworks.boardback.service;

import com.raonworks.boardback.data.dto.response.search.GetPopularListResponseDTO;
import org.springframework.http.ResponseEntity;

public interface SearchService {

  ResponseEntity<? super GetPopularListResponseDTO> getPopularList();
}
