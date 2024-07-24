package com.raonworks.boardback.service;

import com.raonworks.boardback.data.dto.request.board.PostBoardRequestDTO;
import com.raonworks.boardback.data.dto.request.board.PostCommentRequestDTO;
import com.raonworks.boardback.data.dto.response.board.*;
import org.springframework.http.ResponseEntity;

public interface BoardService {
  ResponseEntity<? super GetBoardResponseDTO> getBoard(Integer boardNum);
  ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO dto, String email);
  ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(Integer boardNum, String email);
  ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(Integer boardNum);
  ResponseEntity<? super PostCommentResponseDTO> postComment(PostCommentRequestDTO dto, Integer boardNum, String email);
}
