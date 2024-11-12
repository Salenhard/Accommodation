package salen.Hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReputationBadgeAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReputationBadgeNotFoundException.class)
    String handle(ReputationBadgeNotFoundException ex)
    {
        return ex.getMessage();
    }
}
