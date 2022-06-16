package ua.com.shulzhenko.util;

public class InvalidInputException extends Exception {
    /**
     * Constructor for InvalidInputException.
     *
     * @param message - giving message.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
