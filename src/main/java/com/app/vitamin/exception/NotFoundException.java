package com.app.vitamin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotFoundException extends RuntimeException {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(NotFoundException.class);

  public NotFoundException(String message) {
    super(message);
    LOGGER.error(message);
  }
}
