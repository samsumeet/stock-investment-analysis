package com.app.vitamin.error;

import com.app.vitamin.exception.DatabaseException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class DatabaseExceptionHandler {
  @ResponseBody
  @ExceptionHandler(DatabaseException.class)
  ResponseEntity<CustomErrorResponse> userNotFoundHandler(DatabaseException ex) {
    CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now()
        , HttpStatus.INTERNAL_SERVER_ERROR.value()
        , ex.getMessage(),false);
    return new ResponseEntity(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}