package salen.Hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccommodationNameNotAllowedAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccommodationNameNotAllowedException.class)
    String handle(AccommodationNameNotAllowedException ex)
    {
        return ex.getMessage();
    }
}
