package com.raonworks.boardback.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "image")
@Table(name = "image")
public class ImageEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer sequence;
  private Integer boardNumber;
  private String image;

  public ImageEntity(Integer boardNumber, String image) {
    this.boardNumber = boardNumber;
    this.image = image;
  }
}
