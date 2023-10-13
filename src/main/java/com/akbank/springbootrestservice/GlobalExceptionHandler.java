package com.akbank.springbootrestservice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.akbank.springbootrestservice.utils.ValidationErrorResponse;
import com.akbank.springbootrestservice.utils.ValidationResult;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody // json formatında dosyayı response body ekleyebilmek için kullandığımız
                // anotasyon
  ValidationErrorResponse OnMethodArgumentNotValidException(MethodArgumentNotValidException e) {

    System.out.println("400 Bad Request");

    var errorResponse = new ValidationErrorResponse();
    var errors = errorResponse.getErrors();
    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
      var errorResult = new ValidationResult();
      errorResult.setFieldName(fieldError.getField());
      errorResult.setMessage(fieldError.getDefaultMessage());
      errors.add(errorResult);

    }

    errorResponse.setErrors(errors);

    return errorResponse;

  }

}
