package com.raonworks.boardback.data.dto.response.user;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchProfileImageResponseDTO extends ResponseDTO {

  private PatchProfileImageResponseDTO() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

  }

  public static ResponseEntity<PatchProfileImageResponseDTO> success() {
    PatchProfileImageResponseDTO result = new PatchProfileImageResponseDTO();
    return ResponseEntity.ok(result);
  }

}
