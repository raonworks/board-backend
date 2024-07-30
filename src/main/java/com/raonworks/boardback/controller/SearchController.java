package com.raonworks.boardback.controller;

import com.raonworks.boardback.data.dto.response.search.GetPopularListResponseDTO;
import com.raonworks.boardback.data.dto.response.search.GetRelationListResponseDTO;
import com.raonworks.boardback.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

  private final SearchService searchService;

  @GetMapping("/popular-list")
  public ResponseEntity<? super GetPopularListResponseDTO> getPopularList() {
    ResponseEntity<? super GetPopularListResponseDTO> response = searchService.getPopularList();
    return response;
  }

  @GetMapping("/{searchWord}/relation-list")
  public ResponseEntity<? super GetRelationListResponseDTO> getRelationList(
          @PathVariable String searchWord) {
    ResponseEntity<? super GetRelationListResponseDTO> response = searchService.getRelationList(searchWord);
    return response;
  }

}
