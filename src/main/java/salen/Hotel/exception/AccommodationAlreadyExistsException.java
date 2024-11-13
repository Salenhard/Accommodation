package salen.Hotel.exception;

public class AccommodationAlreadyExistsException extends RuntimeException {
    public AccommodationAlreadyExistsException(Long id) {
        super("Accommodation with id: " + id + " already exists.");
    }
}
