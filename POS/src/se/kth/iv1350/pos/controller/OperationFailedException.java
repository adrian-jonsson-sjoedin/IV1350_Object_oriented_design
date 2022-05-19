package se.kth.iv1350.pos.controller;

/**
 * Used for generic errors thrown in the controller.
 */
public class OperationFailedException extends Exception{
    /**
     * Gives us a description and the cause of the exception.
     *
     * @param message description about the exception.
     * @param cause the cause of the exception.
     */
    public OperationFailedException(String message, Exception cause) {
        super(message, cause);
    }
}
