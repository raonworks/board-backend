package com.raonworks.boardback.controller;

import com.raonworks.boardback.data.dto.request.board.PostBoardRequestDTO;
import com.raonworks.boardback.data.dto.request.board.PostCommentRequestDTO;
import com.raonworks.boardback.data.dto.response.board.*;
import com.raonworks.boardback.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/{boardNumber}")
  public ResponseEntity<? super GetBoardResponseDTO> getBoard(@PathVariable Integer boardNumber) {
    ResponseEntity<? super GetBoardResponseDTO> response = boardService.getBoard(boardNumber);
    return response;
  }

  @PostMapping("")
  public ResponseEntity<? super PostBoardResponseDTO> postBoard(
          @RequestBody @Valid PostBoardRequestDTO dto,
          @AuthenticationPrincipal String email) {

    ResponseEntity<? super PostBoardResponseDTO> response = boardService.postBoard(dto, email);
    return response;
  }

  @PutMapping("/{boardNumber}/favorite")
  public ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(@PathVariable Integer boardNumber, @AuthenticationPrincipal String email) {
    ResponseEntity<? super PutFavoriteResponseDTO> response = boardService.putFavorite(boardNumber, email);
    return response;
  }

  @GetMapping("/{boardNumber}/favorite-list")
  public ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(@PathVariable Integer boardNumber) {
    ResponseEntity<? super GetFavoriteListResponseDTO> response = boardService.getFavoriteList(boardNumber);
    return response;
  }

  @PostMapping("/{boardNumber}/comment")
  public ResponseEntity<? super PostCommentResponseDTO> postComment(
          @RequestBody @Valid PostCommentRequestDTO dto,
          @PathVariable Integer boardNumber,
          @AuthenticationPrincipal String email) {
    ResponseEntity<? super PostCommentResponseDTO> response = boardService.postComment(dto, boardNumber, email);
    return response;
  }

}
