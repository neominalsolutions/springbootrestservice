package com.akbank.springbootrestservice.dtos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto { // validation yönetimi ve swagger documentation ApiProperty ayarları

  public String Id;
  @JsonProperty(value = "title", defaultValue = "title1", required = true)
  @NotEmpty(message = "Boş geçilemez")
  @NotNull(message = "Boş geçilemez")
  @NotBlank(message = "Boşluklu geçilemez")
  public String title;

  // @JsonProperty(value = "body1", defaultValue = "body1", required = true)
  public String body;
  public boolean published;
  public LocalDate publishedAt;

  public List<TagDto> tags;

}
