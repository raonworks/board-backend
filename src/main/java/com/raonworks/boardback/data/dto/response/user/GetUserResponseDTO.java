package com.raonworks.boardback.data.dto.response.user;

import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class GetUserResponseDTO extends ResponseDTO {

  private String email;
  private String nickname;
  private String profileImage;

  private GetUserResponseDTO(UserEntity userEntity) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    this.email = userEntity.getEmail();
    this.nickname = userEntity.getNickname();
    this.profileImage = userEntity.getProfileImage();
  }

  public static ResponseEntity<GetUserResponseDTO> success(UserEntity userEntity) {
    GetUserResponseDTO response = new GetUserResponseDTO(userEntity);
    return ResponseEntity.ok(response);
  }

  public static ResponseEntity<ResponseDTO> noExist() {
    ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
    return ResponseEntity.badRequest().body(result);
  }

}
