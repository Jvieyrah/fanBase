package com.betrybe.fanbase.advice;

import com.betrybe.fanbase.exception.SeriesCharacterAlreadyExistsException;
import com.betrybe.fanbase.exception.SeriesCharacterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<String> handleNotFoundException(SeriesCharacterNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado!");
  }

  @ExceptionHandler
  public ResponseEntity<String> handleAlreadyExistsException(SeriesCharacterAlreadyExistsException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Personagem já cadastrado!");
  }

}
