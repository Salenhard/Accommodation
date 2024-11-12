package salen.Hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccommodationAlreadyExistsAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccommodationAlreadyExists.class)
    String handle(AccommodationAlreadyExists ex)
    {
        return ex.getMessage();
    }
}
