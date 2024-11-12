package salen.Hotel.exception;

public class AccommodationNameNotAllowedException extends RuntimeException {
    public AccommodationNameNotAllowedException(String name) {
        super("Name cannot contain: " + name);
    }
}
