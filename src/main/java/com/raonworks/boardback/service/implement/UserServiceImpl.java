package com.raonworks.boardback.service.implement;

import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.dto.response.user.GetSignInUserResponseDTO;
import com.raonworks.boardback.data.entity.UserEntity;
import com.raonworks.boardback.repository.UserRepository;
import com.raonworks.boardback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(String email) {
    UserEntity userEntity = null;
    try {
      userEntity = userRepository.findByEmail(email);
      if(null == userEntity) return GetSignInUserResponseDTO.notExistUser();

    } catch (Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }
    return GetSignInUserResponseDTO.success(userEntity);
  }
}
