package com.raonworks.boardback.service;

import com.raonworks.boardback.data.dto.request.board.PostBoardRequestDTO;
import com.raonworks.boardback.data.dto.response.board.PostBoardResponseDTO;
import org.springframework.http.ResponseEntity;

public interface BoardService {
  ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO dto, String email);
}
