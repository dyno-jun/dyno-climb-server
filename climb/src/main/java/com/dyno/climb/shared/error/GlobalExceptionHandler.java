package com.dyno.climb.shared.error;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });

    //      return ResponseEntity.status(HttpStatus.BAD_REQUEST) // 400 Bad Request
    //          .body(new ErrorResponse(ex.getMessage()));

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserAlreadyExistsError.class)
  public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsError ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST) // 400 Bad Request
        .body(new ErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler(BadRequestError.class)
  public ResponseEntity<?> handleUserAlreadyExistsException(BadRequestError ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST) // 400 Bad Request
        .body(new ErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(final Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponse(ex.getMessage()));
  }

  public static class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }
}
