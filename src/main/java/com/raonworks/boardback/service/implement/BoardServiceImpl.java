package com.raonworks.boardback.service.implement;

import com.raonworks.boardback.data.dto.request.board.PatchBoardRequestDTO;
import com.raonworks.boardback.data.dto.request.board.PostBoardRequestDTO;
import com.raonworks.boardback.data.dto.request.board.PostCommentRequestDTO;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.dto.response.board.*;
import com.raonworks.boardback.data.entity.*;
import com.raonworks.boardback.exception.custom.DatabaseException;
import com.raonworks.boardback.exception.custom.NoPermissionException;
import com.raonworks.boardback.exception.custom.NotExistsBoardException;
import com.raonworks.boardback.exception.custom.NotExistsUserException;
import com.raonworks.boardback.repository.*;
import com.raonworks.boardback.repository.resultSet.GetBoardResultSet;
import com.raonworks.boardback.repository.resultSet.GetCommentListResultSet;
import com.raonworks.boardback.repository.resultSet.GetFavoriteListResultSet;
import com.raonworks.boardback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final UserRepository userRepository;
  private final BoardRepository boardRepository;
  private final BoardListViewRepository boardListViewRepository;
  private final ImageRepository imageRepository;
  private final FavoriteRepository favoriteRepository;
  private final CommentRepository commentRepository;
  private final SearchLogRepository searchLogRepository;

  @Override
  public ResponseEntity<? super GetBoardResponseDTO> getBoard(Integer boardNum) {

    GetBoardResultSet resultSet = null;
    List<ImageEntity> imageEntities = new ArrayList<>();
    try {
      //글쓴이 포함 게시물 정보 불러오기
      resultSet = boardRepository.getBoard(boardNum);
      if (resultSet == null) {
        return GetBoardResponseDTO.notExistBoard();
      }

      //보드 이미지 불러오기
      imageEntities = imageRepository.findByBoardNumber(boardNum);

//      //조회 수 증가
//      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNum);
//      boardEntity.increaseViewCount();
//      boardRepository.save(boardEntity);

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
      if (!existEmail) return PostBoardResponseDTO.notExistUser();

      BoardEntity boardEntity = new BoardEntity(dto, email);
      boardRepository.save(boardEntity);

      int boardNumber = boardEntity.getBoardNumber();
      List<String> boardImageList = dto.getBoardImageList();
      List<ImageEntity> imageEntities = new ArrayList<>();

      for (String image : boardImageList) {
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
      if (!existUser) return PutFavoriteResponseDTO.notExistUser();

      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNum);
      if (null == boardEntity) return PutFavoriteResponseDTO.noExistBoard();

      FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(boardNum, email);
      if (favoriteEntity == null) {
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

  @Override
  public ResponseEntity<? super GetFavoriteListResponseDTO> getFavoriteList(Integer boardNum) {

    List<GetFavoriteListResultSet> resultSets = new ArrayList<>();

    try {

      boolean existsBoard = boardRepository.existsByBoardNumber(boardNum);
      if (!existsBoard) return GetFavoriteListResponseDTO.noExistBoard();

      resultSets = favoriteRepository.getFavoriteList(boardNum);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }

    return GetFavoriteListResponseDTO.success(resultSets);
  }

  @Override
  public ResponseEntity<? super PostCommentResponseDTO> postComment(PostCommentRequestDTO dto, Integer boardNum, String email) {
    try {

      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNum);
      if (null == boardEntity) return PostCommentResponseDTO.noExistBoard();

      boolean existUser = userRepository.existsByEmail(email);
      if (!existUser) return PostCommentResponseDTO.noExistUser();

      CommentEntity comment = new CommentEntity(dto, boardNum, email);
      commentRepository.save(comment);

      boardEntity.increaseCommentCount();
      boardRepository.save(boardEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();

    }
    return PostCommentResponseDTO.success();
  }

  @Override
  public ResponseEntity<? super GetCommentListResponseDTO> getCommentList(Integer boardNum) {

    List<GetCommentListResultSet> resultSets = new ArrayList<>();

    try {

      boolean existsBoard = boardRepository.existsByBoardNumber(boardNum);
      if (!existsBoard) return GetCommentListResponseDTO.noExistBoard();

      resultSets = commentRepository.getCommentList(boardNum);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }
    return GetCommentListResponseDTO.success(resultSets);
  }

  @Override
  public ResponseEntity<? super InceaseViewCountResponseDTO> inceaseViewCount(Integer boardNum) {
    try {
      //조회 수 증가
      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNum);
      if (null == boardEntity) return InceaseViewCountResponseDTO.noExistBoard();

      boardEntity.increaseViewCount();
      boardRepository.save(boardEntity);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }

    return InceaseViewCountResponseDTO.success();
  }

  @Override
  public ResponseEntity<? super DeleteBoardResponseDTO> deleteBoard(Integer boardNum, String email) {

    try {

      //유효한 유저 확인
      boolean existUser = userRepository.existsByEmail(email);
      if (!existUser) return DeleteBoardResponseDTO.noExistUser();

      //유효한 게시물 확인
      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNum);
      if (null == boardEntity) return DeleteBoardResponseDTO.noExistBoard();

      //작성자 확인
      String writerEmail = boardEntity.getWriterEmail();
      boolean sameEmail = email.equals(writerEmail);
      if (!sameEmail) return DeleteBoardResponseDTO.noPermission();

      //관계 데이터 삭제
      imageRepository.deleteByBoardNumber(boardNum);
      commentRepository.deleteByBoardNumber(boardNum);
      favoriteRepository.deleteByBoardNumber(boardNum);

      //게시물 삭제
      boardRepository.delete(boardEntity);

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }
    return DeleteBoardResponseDTO.success();
  }

  @Override
  public ResponseEntity<? super PatchBoardResponseDTO> patchBoard(Integer boardNum, String email, PatchBoardRequestDTO dto) {

    BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNum);
    if (null == boardEntity) throw new NotExistsBoardException();

    boolean existUser = userRepository.existsByEmail(email);
    if (!existUser) throw new NotExistsUserException();

    boolean sameEmail = email.equals(boardEntity.getWriterEmail());
    if (!sameEmail) throw new NoPermissionException();

    boardEntity.patchBoard(dto);
    boardRepository.save(boardEntity);

    imageRepository.deleteByBoardNumber(boardNum);
    List<String> boardImageList = dto.getBoardImageList();
    List<ImageEntity> imageEntities = new ArrayList<>();
    for (String boardImage : boardImageList) {
      ImageEntity imageEntity = new ImageEntity(boardNum, boardImage);
      imageEntities.add(imageEntity);
    }
    imageRepository.saveAll(imageEntities);

    return PatchBoardResponseDTO.success();
  }

  @Override
  public ResponseEntity<? super GetLatestBoardListResponseDTO> getLatestBoardList() {

    List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();
    boardListViewEntities = boardListViewRepository.findByOrderByWriteDatetimeDesc();

    return GetLatestBoardListResponseDTO.suceess(boardListViewEntities);
  }

  @Override
  public ResponseEntity<? super GetTop3BoardListResponseDTO> getTop3BoardList() {

    Date beforeWeek = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String sevenDaysAgo = simpleDateFormat.format(beforeWeek);

    List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();
    boardListViewEntities = boardListViewRepository.findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(sevenDaysAgo);

    return GetTop3BoardListResponseDTO.success(boardListViewEntities);
  }

  @Override
  public ResponseEntity<? super GetSearchBoardListResponseDTO> getSearchBoardList(String searchWord, String preSearchWord) {

    List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

    try {

      boardListViewEntities = boardListViewRepository.findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(searchWord, searchWord);
      SearchLogEntity searchLogEntity = new SearchLogEntity(searchWord, preSearchWord, false);
      searchLogRepository.save(searchLogEntity);

      boolean relation = preSearchWord != null;
      if (relation) {
        SearchLogEntity preSearchLogEntity = new SearchLogEntity(preSearchWord, searchWord, true);
        searchLogRepository.save(preSearchLogEntity);
      }

    } catch (Exception e) {
      e.printStackTrace();
      throw new DatabaseException();
    }
    return GetSearchBoardListResponseDTO.success(boardListViewEntities);
  }

  @Override
  public ResponseEntity<? super GetUserBoardListResponseDTO> getUserBoardList(String email) {

    List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

    try {

      boolean existUser = userRepository.existsByEmail(email);
      if (!existUser) throw new NotExistsUserException();

      boardListViewEntities = boardListViewRepository.findByWriterEmailOrderByWriteDatetimeDesc(email);

    } catch (NotExistsUserException e) {
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      throw new DatabaseException();
    }
    return GetUserBoardListResponseDTO.success(boardListViewEntities);
  }

}
