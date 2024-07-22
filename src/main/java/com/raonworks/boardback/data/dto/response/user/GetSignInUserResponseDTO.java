package com.raonworks.boardback.data.dto.response.user;
import com.raonworks.boardback.common.ResponseCode;
import com.raonworks.boardback.common.ResponseMessage;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetSignInUserResponseDTO extends ResponseDTO {
  private String email;
  private String nickname;
  private String profileImage;

  private GetSignInUserResponseDTO(UserEntity userEntity) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.email = userEntity.getEmail();
    this.nickname = userEntity.getNickname();
    this.profileImage = userEntity.getProfileImage();
  }

  public static ResponseEntity<GetSignInUserResponseDTO> success(UserEntity userEntity) {
    GetSignInUserResponseDTO response = new GetSignInUserResponseDTO(userEntity);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  public static ResponseEntity<ResponseDTO> notExistUser() {
    ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXIST_USER, ResponseMessage.NOT_EXIST_USER);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
  }
}
