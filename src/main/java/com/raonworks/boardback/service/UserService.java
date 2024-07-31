package com.raonworks.boardback.service;

import com.raonworks.boardback.data.dto.request.user.PatchNicknameRequestDTO;
import com.raonworks.boardback.data.dto.request.user.PatchProfileImageRequestDTO;
import com.raonworks.boardback.data.dto.response.user.GetSignInUserResponseDTO;
import com.raonworks.boardback.data.dto.response.user.GetUserResponseDTO;
import com.raonworks.boardback.data.dto.response.user.PatchNicknameResponseDTO;
import com.raonworks.boardback.data.dto.response.user.PatchProfileImageResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(String email);

  ResponseEntity<? super GetUserResponseDTO> getUser(String email);

  ResponseEntity<? super PatchNicknameResponseDTO> patchNickname(PatchNicknameRequestDTO dto, String email);

  ResponseEntity<? super PatchProfileImageResponseDTO> patchProfileImage(PatchProfileImageRequestDTO dto, String email);
}
