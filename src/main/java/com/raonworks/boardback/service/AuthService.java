package com.raonworks.boardback.service;

import com.raonworks.boardback.data.dto.request.auth.SignInRequestDTO;
import com.raonworks.boardback.data.dto.request.auth.SignUpRequestDTO;
import com.raonworks.boardback.data.dto.response.auth.SignInResponseDTO;
import com.raonworks.boardback.data.dto.response.auth.SignUpResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

  ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO signUpRequestDTO);
  ResponseEntity<? super SignInResponseDTO> signIn(SignInRequestDTO signInRequestDTO);
}
