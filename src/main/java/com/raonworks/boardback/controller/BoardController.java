package com.raonworks.boardback.controller;

import com.raonworks.boardback.data.dto.request.board.PostBoardRequestDTO;
import com.raonworks.boardback.data.dto.response.board.PostBoardResponseDTO;
import com.raonworks.boardback.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @PostMapping("")
  public ResponseEntity<? super PostBoardResponseDTO> postBoard(
          @RequestBody @Valid PostBoardRequestDTO dto,
          @AuthenticationPrincipal String email) {

    ResponseEntity<? super PostBoardResponseDTO> response = boardService.postBoard(dto, email);
    return response;
  }
}
