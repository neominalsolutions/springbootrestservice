package com.akbank.springbootrestservice.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ValidationErrorResponse {
  private List<ValidationResult> errors = new ArrayList<>();
}
