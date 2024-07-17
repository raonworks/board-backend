package com.raonworks.boardback.service.implement;

import com.raonworks.boardback.data.dto.request.auth.SignInRequestDTO;
import com.raonworks.boardback.data.dto.request.auth.SignUpRequestDTO;
import com.raonworks.boardback.data.dto.response.ResponseDTO;
import com.raonworks.boardback.data.dto.response.auth.SignInResponseDTO;
import com.raonworks.boardback.data.dto.response.auth.SignUpResponseDTO;
import com.raonworks.boardback.data.entity.UserEntity;
import com.raonworks.boardback.provider.JwtProvider;
import com.raonworks.boardback.repository.UserRepository;
import com.raonworks.boardback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  private final JwtProvider jwtProvider;

  @Override
  public ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO dto) {

    try {
      String email = dto.getEmail();
      boolean existedEmail = userRepository.existsByEmail(email);
      if (existedEmail) return SignUpResponseDTO.duplicateEmail();

      String nickname = dto.getNickname();
      boolean existedNickname = userRepository.existsByNickname(nickname);
      if(existedNickname) return SignUpResponseDTO.duplicateNickname();

      String telNumber = dto.getTelNumber();
      boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
      if(existedTelNumber) return SignUpResponseDTO.duplicateTelNumber();

      String password = dto.getPassword();
      String encodedPassword = passwordEncoder.encode(password);
      dto.setPassword(encodedPassword);

      UserEntity userEntity = new UserEntity(dto);
      userRepository.save(userEntity);

    } catch(Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }
    return SignUpResponseDTO.success();
  }

  @Override
  public ResponseEntity<? super SignInResponseDTO> signIn(SignInRequestDTO dto) {

    String token = null;

    try {
      String email = dto.getEmail();
      UserEntity userEntity = userRepository.findByEmail(email);
      if(userEntity == null) return SignInResponseDTO.SignInFailed();

      String password = dto.getPassword();
      String encodedPassword = userEntity.getPassword();
      boolean isMatched = passwordEncoder.matches(password, encodedPassword);

      if(!isMatched) return SignInResponseDTO.SignInFailed();

      token = jwtProvider.create(email);

    } catch(Exception e) {
      e.printStackTrace();
      return ResponseDTO.databaseError();
    }
    return SignInResponseDTO.success(token);
  }


}
