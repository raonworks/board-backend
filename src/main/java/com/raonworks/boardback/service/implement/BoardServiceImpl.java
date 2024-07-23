package com.raonworks.boardback.service.implement;

import com.raonworks.boardback.data.dto.request.board.PostBoardRequestDTO;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.dto.response.board.PostBoardResponseDTO;
import com.raonworks.boardback.data.entity.BoardEntity;
import com.raonworks.boardback.data.entity.ImageEntity;
import com.raonworks.boardback.repository.BoardRepository;
import com.raonworks.boardback.repository.ImageRepository;
import com.raonworks.boardback.repository.UserRepository;
import com.raonworks.boardback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final UserRepository userRepository;
  private final BoardRepository boardRepository;
  private final ImageRepository imageRepository;

  @Override
  public ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO dto, String email) {
    try {
      boolean existEmail = userRepository.existsByEmail(email);
      if(!existEmail) return PostBoardResponseDTO.notExistUser();

      BoardEntity boardEntity = new BoardEntity(dto, email);
      boardRepository.save(boardEntity);

      int boardNumber = boardEntity.getBoardNumber();
      List<String> boardImageList = dto.getBoardImageList();
      List<ImageEntity> imageEntities = new ArrayList<>();

      for(String image : boardImageList) {
        ImageEntity imageEntity = new ImageEntity(boardNumber, image);
        imageEntities.add(imageEntity);
      }

      imageRepository.saveAll(imageEntities);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }

    return PostBoardResponseDTO.success();
  }
}
