package com.akbank.springbootrestservice.utils;

import lombok.Data;

@Data
public class ValidationResult {

  private String fieldName;
  private String message;

}
