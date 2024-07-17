package com.raonworks.boardback.data.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDTO {
  @NotBlank
  private String email;

  @NotBlank
  private String password;
}
