package com.raonworks.boardback.controller;

import com.raonworks.boardback.data.dto.request.user.PatchNicknameRequestDTO;
import com.raonworks.boardback.data.dto.request.user.PatchProfileImageRequestDTO;
import com.raonworks.boardback.data.dto.response.user.GetSignInUserResponseDTO;
import com.raonworks.boardback.data.dto.response.user.GetUserResponseDTO;
import com.raonworks.boardback.data.dto.response.user.PatchNicknameResponseDTO;
import com.raonworks.boardback.data.dto.response.user.PatchProfileImageResponseDTO;
import com.raonworks.boardback.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

  @PatchMapping("/nickname")
  public ResponseEntity<? super PatchNicknameResponseDTO> patchNickname(
          @RequestBody @Valid PatchNicknameRequestDTO req,
          @AuthenticationPrincipal String email) {

    ResponseEntity<? super PatchNicknameResponseDTO> result = userService.patchNickname(req, email);
    return result;
  }

  @PatchMapping("/profile-image")
  public ResponseEntity<? super PatchProfileImageResponseDTO> patchProfileImage(
          @RequestBody @Valid PatchProfileImageRequestDTO req,
          @AuthenticationPrincipal String email
  ) {
    ResponseEntity<? super PatchProfileImageResponseDTO> result = userService.patchProfileImage(req, email);
    return result;
  }

}
