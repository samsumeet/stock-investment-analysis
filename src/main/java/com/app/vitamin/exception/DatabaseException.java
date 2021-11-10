package com.app.vitamin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseException extends RuntimeException {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(DatabaseException.class);

  public DatabaseException(String message) {
    super(message);
    LOGGER.error(message);

  }
}