package com.raonworks.boardback.service.implement;

import com.raonworks.boardback.data.dto.request.user.PatchNicknameRequestDTO;
import com.raonworks.boardback.data.dto.request.user.PatchProfileImageRequestDTO;
import com.raonworks.boardback.data.dto.response.user.GetSignInUserResponseDTO;
import com.raonworks.boardback.data.dto.response.user.GetUserResponseDTO;
import com.raonworks.boardback.data.dto.response.user.PatchNicknameResponseDTO;
import com.raonworks.boardback.data.dto.response.user.PatchProfileImageResponseDTO;
import com.raonworks.boardback.data.entity.UserEntity;
import com.raonworks.boardback.exception.custom.AlreadyNicknameException;
import com.raonworks.boardback.exception.custom.DatabaseException;
import com.raonworks.boardback.exception.custom.NoPermissionException;
import com.raonworks.boardback.exception.custom.NotExistsUserException;
import com.raonworks.boardback.repository.UserRepository;
import com.raonworks.boardback.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(String email) {

    UserEntity userEntity = null;

    try {
      userEntity = userRepository.findByEmail(email);
      if (null == userEntity) return GetSignInUserResponseDTO.notExistUser();

    } catch (Exception e) {
      e.printStackTrace();
      throw new DatabaseException();
    }
    return GetSignInUserResponseDTO.success(userEntity);
  }

  @Override
  public ResponseEntity<? super GetUserResponseDTO> getUser(String email) {

    UserEntity userEntity = null;

    try {
      userEntity = userRepository.findByEmail(email);
      if (null == userEntity) throw new NotExistsUserException();
    } catch (NotExistsUserException e) {
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      throw new DatabaseException();
    }
    return GetUserResponseDTO.success(userEntity);
  }

  @Override
  public ResponseEntity<? super PatchNicknameResponseDTO> patchNickname(PatchNicknameRequestDTO dto, String email) {

    try {
      UserEntity userEntity = userRepository.findByEmail(email);
      if (null == userEntity) throw new NotExistsUserException();

      String nickname = dto.getNickname();
      boolean exists = userRepository.existsByNickname(nickname);
      if (exists) throw new AlreadyNicknameException();

      userEntity.setNickname(nickname);
      userRepository.save(userEntity);

    } catch (NotExistsUserException | AlreadyNicknameException e) {
      throw e;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DatabaseException();
    }

    return PatchNicknameResponseDTO.success();
  }

  @Override
  public ResponseEntity<? super PatchProfileImageResponseDTO> patchProfileImage(PatchProfileImageRequestDTO dto, String email) {

    try {
      UserEntity userEntity = userRepository.findByEmail(email);
      if (null == userEntity) throw new NotExistsUserException();

      String profileImage = dto.getProfileImage();
      userEntity.setProfileImage(profileImage);
      userRepository.save(userEntity);

    } catch (NotExistsUserException e) {
      throw e;

    } catch (Exception e) {
      e.printStackTrace();
      throw new DatabaseException();
    }

    return PatchProfileImageResponseDTO.success();
  }

}
