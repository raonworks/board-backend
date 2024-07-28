package com.raonworks.boardback.controller;

import com.raonworks.boardback.data.dto.request.board.PatchBoardRequestDTO;
import com.raonworks.boardback.data.dto.request.board.PostBoardRequestDTO;
import com.raonworks.boardback.data.dto.request.board.PostCommentRequestDTO;
import com.raonworks.boardback.data.dto.response.board.*;
import com.raonworks.boardback.exception.custom.AlreadyEmailExistException;
import com.raonworks.boardback.exception.custom.NotExistsUserException;
import com.raonworks.boardback.exception.dto.ErrorResponseDTO;
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

  //게시물 등록하기
  @PostMapping("")
  public ResponseEntity<? super PostBoardResponseDTO> postBoard(
          @RequestBody @Valid PostBoardRequestDTO dto,
          @AuthenticationPrincipal String email) {

    ResponseEntity<? super PostBoardResponseDTO> response = boardService.postBoard(dto, email);
    return response;
  }

  //에러 테스트
  @GetMapping("/error")
  public ResponseEntity<ErrorResponseDTO> testPage() {
    throw new NotExistsUserException();
  }

  //게시물 가져오기
  @GetMapping("/{boardNumber}")
  public ResponseEntity<? super GetBoardResponseDTO> getBoard(@PathVariable Integer boardNumber) {
    ResponseEntity<? super GetBoardResponseDTO> response = boardService.getBoard(boardNumber);
    return response;
  }

  //게시물 삭제
  @DeleteMapping("/{boardNumber}")
  public ResponseEntity<? super DeleteBoardResponseDTO> deleteBoard(
          @PathVariable Integer boardNumber,
          @AuthenticationPrincipal String email) {

    ResponseEntity<? super DeleteBoardResponseDTO> response = boardService.deleteBoard(boardNumber, email);
    return response;
  }

  //좋아요 달기
  @PutMapping("/{boardNumber}/favorite")
  public ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(@PathVariable Integer boardNumber, @AuthenticationPrincipal String email) {
    ResponseEntity<? super PutFavoriteResponseDTO> response = boardService.putFavorite(boardNumber, email);
    return response;
  }

  //좋아요 리스트 받아오기
  @GetMapping("/{boardNumber}/favorite-list")
  public ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(@PathVariable Integer boardNumber) {
    ResponseEntity<? super GetFavoriteListResponseDTO> response = boardService.getFavoriteList(boardNumber);
    return response;
  }

  //댓글 달기
  @PostMapping("/{boardNumber}/comment")
  public ResponseEntity<? super PostCommentResponseDTO> postComment(
          @RequestBody @Valid PostCommentRequestDTO dto,
          @PathVariable Integer boardNumber,
          @AuthenticationPrincipal String email) {
    ResponseEntity<? super PostCommentResponseDTO> response = boardService.postComment(dto, boardNumber, email);
    return response;
  }

  //댓글 리스트 받아오기
  @GetMapping("/{boardNumber}/comment-list")
  public ResponseEntity<? super GetCommentListResponseDTO> getCommentList(@PathVariable Integer boardNumber) {
    ResponseEntity<? super GetCommentListResponseDTO> response = boardService.getCommentList(boardNumber);
    return response;
  }

  //조회 카운트 증가
  @GetMapping("/{boardNumber}/increase-view-count")
  public ResponseEntity<? super InceaseViewCountResponseDTO> inceaseViewCount(
          @PathVariable Integer boardNumber
  ) {
    ResponseEntity<? super InceaseViewCountResponseDTO> result = boardService.inceaseViewCount(boardNumber);
    return result;
  }

  //
  @PatchMapping("/{boardNumber}")
  public ResponseEntity<? super PatchBoardResponseDTO> patchBoard(
          @RequestBody @Valid PatchBoardRequestDTO req,
          @PathVariable Integer boardNumber,
          @AuthenticationPrincipal String email
  ) {
    ResponseEntity<? super PatchBoardResponseDTO> response = boardService.patchBoard(boardNumber, email, req);
    return response;
  }

}
