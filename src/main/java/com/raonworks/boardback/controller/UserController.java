package com.raonworks.boardback.controller;

import com.raonworks.boardback.data.dto.response.user.GetSignInUserResponseDTO;
import com.raonworks.boardback.data.dto.response.user.GetUserResponseDTO;
import com.raonworks.boardback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  @GetMapping("")
  public ResponseEntity<? super GetSignInUserResponseDTO> getSignInUser(@AuthenticationPrincipal String email) {
    ResponseEntity<? super GetSignInUserResponseDTO> response = userService.getSignInUser(email);
    return response;
  }

  @GetMapping("/{email}")
  public ResponseEntity<? super GetUserResponseDTO> getUser(@PathVariable String email) {
    ResponseEntity<? super GetUserResponseDTO> result = userService.getUser(email);
    return result;
  }

}
