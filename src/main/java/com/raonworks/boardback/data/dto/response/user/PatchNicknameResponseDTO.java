package com.raonworks.boardback.data.dto.response.user;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchNicknameResponseDTO extends ResponseDTO {

  private PatchNicknameResponseDTO() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

  }

  public static ResponseEntity<PatchNicknameResponseDTO> success() {
    PatchNicknameResponseDTO result = new PatchNicknameResponseDTO();
    return ResponseEntity.ok(result);
  }

}
