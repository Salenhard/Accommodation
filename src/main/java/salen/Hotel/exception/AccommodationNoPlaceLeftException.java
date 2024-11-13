package salen.Hotel.exception;

public class AccommodationNoPlaceLeftException extends RuntimeException {
    public AccommodationNoPlaceLeftException()
    {
        super("No place left");
    }
}
