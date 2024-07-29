package com.raonworks.boardback.data.dto.response.search;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.repository.resultSet.GetPopularListResultSet;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetPopularListResponseDTO extends ResponseDTO {

  private List<String> popularWordList;

  private GetPopularListResponseDTO(List<GetPopularListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    this.popularWordList = new ArrayList<String>();

    for (GetPopularListResultSet resultSet : resultSets) {
      String popularWord = resultSet.getSearchWord();
      this.popularWordList.add(popularWord);
    }

  }

  public static ResponseEntity<GetPopularListResponseDTO> success(List<GetPopularListResultSet> resultSets) {
    GetPopularListResponseDTO result = new GetPopularListResponseDTO(resultSets);
    return ResponseEntity.ok(result);
  }
}
