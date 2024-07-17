package com.raonworks.boardback.controller;

import com.raonworks.boardback.data.dto.request.auth.SignUpRequestDTO;
import com.raonworks.boardback.data.dto.response.auth.SignUpResponseDTO;
import com.raonworks.boardback.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/sign-up")
  public ResponseEntity<? super SignUpResponseDTO> signUp(@RequestBody @Valid SignUpRequestDTO dto) {
    ResponseEntity<? super SignUpResponseDTO> response = authService.signUp(dto);
    return response;
  }
}
