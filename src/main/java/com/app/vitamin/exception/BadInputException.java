package com.app.vitamin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BadInputException extends RuntimeException {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(BadInputException.class);

  public BadInputException(String message) {
    super(message);
    LOGGER.error(message);
  }
}
