package salen.Hotel.exception;

public class AccommodationNotFoundException extends RuntimeException {
    public AccommodationNotFoundException(Long id) {
        super("Accommodation with id: " + id + " is not found.");
    }
}
