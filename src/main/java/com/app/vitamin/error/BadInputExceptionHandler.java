package com.app.vitamin.error;

import com.app.vitamin.exception.BadInputException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BadInputExceptionHandler {

  @ResponseBody
  @ExceptionHandler(BadInputException.class)
  ResponseEntity<CustomErrorResponse> invalidInputHandler(BadInputException ex) {
    CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now()
        , HttpStatus.BAD_REQUEST.value()
        , ex.getMessage(), false);
    return new ResponseEntity(customErrorResponse, HttpStatus.BAD_REQUEST);
  }
}
