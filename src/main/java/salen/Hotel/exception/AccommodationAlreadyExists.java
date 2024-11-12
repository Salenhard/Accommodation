package salen.Hotel.exception;

public class AccommodationAlreadyExists extends RuntimeException {
    public AccommodationAlreadyExists(Long id) {
        super("Accommodation with id: " + id + " already exists.");
    }
}
