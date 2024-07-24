package com.raonworks.boardback.service.implement;

import com.raonworks.boardback.data.dto.request.board.PostBoardRequestDTO;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.dto.response.board.GetBoardResponseDTO;
import com.raonworks.boardback.data.dto.response.board.PostBoardResponseDTO;
import com.raonworks.boardback.data.dto.response.board.PutFavoriteResponseDTO;
import com.raonworks.boardback.data.entity.BoardEntity;
import com.raonworks.boardback.data.entity.FavoriteEntity;
import com.raonworks.boardback.data.entity.ImageEntity;
import com.raonworks.boardback.repository.BoardRepository;
import com.raonworks.boardback.repository.FavoriteRepository;
import com.raonworks.boardback.repository.ImageRepository;
import com.raonworks.boardback.repository.UserRepository;
import com.raonworks.boardback.repository.resultSet.GetBoardResultSet;
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
  private final FavoriteRepository favoriteRepository;

  @Override
  public ResponseEntity<? super GetBoardResponseDTO> getBoard(Integer boardNum) {

    GetBoardResultSet resultSet = null;
    List<ImageEntity> imageEntities = new ArrayList<>();
    try {
      //글쓴이 포함 게시물 정보 불러오기
      resultSet = boardRepository.getBoard(boardNum);
      if(resultSet == null) {
        return GetBoardResponseDTO.notExistBoard();
      }

      //보드 이미지 불러오기
      imageEntities = imageRepository.findByBoardNumber(boardNum);

      //조회 수 증가
      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNum);
      boardEntity.increaseViewCount();
      boardRepository.save(boardEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }

    return GetBoardResponseDTO.success(resultSet, imageEntities);
  }

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

  @Override
  public ResponseEntity<? super PutFavoriteResponseDTO> putFavorite(Integer boardNum, String email) {
    try {
        boolean existUser = userRepository.existsByEmail(email);
        if(!existUser) return PutFavoriteResponseDTO.notExistUser();

        BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNum);
        if(null == boardEntity) return PutFavoriteResponseDTO.noExistBoard();

        FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(boardNum, email);
        if(favoriteEntity == null) {
          favoriteEntity = new FavoriteEntity(email, boardNum);
          favoriteRepository.save(favoriteEntity);
          boardEntity.inceaseFavoriteCount();
        } else {
          favoriteRepository.delete(favoriteEntity);
          boardEntity.deceaseFavoriteCount();
        }

        boardRepository.save(boardEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }

    return PutFavoriteResponseDTO.success();
  }
}
