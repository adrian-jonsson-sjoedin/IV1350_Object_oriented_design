package se.kth.iv1350.pos.integration;

/**
 * Represent the exception of the database being unreachable.
 */
public class InventoryDBUnresponsiveException extends RuntimeException{
    /**
     * Creates a new instance representing the exception thrown.
     * @param message describes the error.
     */
    public InventoryDBUnresponsiveException(String message) {
        super(message);

    }
}
