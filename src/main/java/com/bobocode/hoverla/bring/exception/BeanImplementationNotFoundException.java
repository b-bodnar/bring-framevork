package com.bobocode.hoverla.bring.exception;

public class BeanImplementationNotFoundException extends RuntimeException{

  public BeanImplementationNotFoundException() {
  }

  public BeanImplementationNotFoundException(String message) {
    super(message);
  }

  public BeanImplementationNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
