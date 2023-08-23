package com.example.demopost.exception;


import java.sql.Date;
import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handlerNotFoundException(@NotNull NotFoundException ex, WebRequest req) {
    return new ErrorResponse(HttpStatus.NOT_FOUND,ex.getMessage(), LocalDateTime.now());
  }
  @ExceptionHandler(InternalServerException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handlerInternalServer(@NotNull InternalServerException ex,WebRequest req) {
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),LocalDateTime.now());
  }
}
