package com.raonworks.boardback.service;

import com.raonworks.boardback.data.dto.response.user.GetSignInUserResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(String email);
}
