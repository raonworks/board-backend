package com.raonworks.boardback.service;

import com.raonworks.boardback.data.dto.request.auth.SignUpRequestDTO;
import com.raonworks.boardback.data.dto.response.auth.SignUpResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO signUpRequestDTO);
}
