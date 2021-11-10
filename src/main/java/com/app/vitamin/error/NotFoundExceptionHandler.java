package com.app.vitamin.error;

import com.app.vitamin.exception.BadInputException;
import com.app.vitamin.exception.NotFoundException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class NotFoundExceptionHandler {
  @ResponseBody
  @ExceptionHandler(NotFoundException.class)
  ResponseEntity<CustomErrorResponse> invalidInputHandler(BadInputException ex) {
    CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now()
        , HttpStatus.NOT_FOUND.value()
        , ex.getMessage(),false);
    return new ResponseEntity(customErrorResponse, HttpStatus.NOT_FOUND);
  }
}
