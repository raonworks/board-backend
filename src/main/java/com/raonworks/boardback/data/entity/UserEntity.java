package com.raonworks.boardback.data.entity;

import com.raonworks.boardback.data.dto.request.auth.SignUpRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {

  @Id
  private String email;
  private String password;
  private String telNumber;
  private String address;
  private String addressDetail;
  private boolean agreedPersonal;

  @Setter
  private String nickname;

  @Setter
  private String profileImage;

  public UserEntity(SignUpRequestDTO dto) {
    this.email = dto.getEmail();
    this.password = dto.getPassword();
    this.nickname = dto.getNickname();
    this.telNumber = dto.getTelNumber();
    this.address = dto.getAddress();
    this.addressDetail = dto.getAddressDetail();
    this.agreedPersonal = dto.getAgreedPersonal();
  }

}
