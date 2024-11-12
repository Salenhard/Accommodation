package salen.Hotel.exception;

public class ReputationBadgeNotFoundException extends RuntimeException {
    public ReputationBadgeNotFoundException(String badge) {
        super("Reputation badge: " + badge + " is not exists");
    }
}
