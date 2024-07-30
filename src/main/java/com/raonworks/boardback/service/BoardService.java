package com.raonworks.boardback.service;

import com.raonworks.boardback.data.dto.request.board.PatchBoardRequestDTO;
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

  ResponseEntity<? super GetCommentListResponseDTO> getCommentList(Integer boardNum);

  ResponseEntity<? super InceaseViewCountResponseDTO> inceaseViewCount(Integer boardNum);

  ResponseEntity<? super DeleteBoardResponseDTO> deleteBoard(Integer boardNum, String email);

  ResponseEntity<? super PatchBoardResponseDTO> patchBoard(Integer boardNum, String email, PatchBoardRequestDTO dto);

  ResponseEntity<? super GetLatestBoardListResponseDTO> getLatestBoardList();

  ResponseEntity<? super GetTop3BoardListResponseDTO> getTop3BoardList();

  ResponseEntity<? super GetSearchBoardListResponseDTO> getSearchBoardList(String searchWord, String preSearchWord);

  ResponseEntity<? super GetUserBoardListResponseDTO> getUserBoardList(String email);
}
