package com.raonworks.boardback.service;

import com.raonworks.boardback.data.dto.response.user.GetSignInUserResponseDTO;
import com.raonworks.boardback.data.dto.response.user.GetUserResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(String email);

  ResponseEntity<? super GetUserResponseDTO> getUser(String email);
}
