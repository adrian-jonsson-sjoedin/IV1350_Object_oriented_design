package se.kth.iv1350.pos.integration;

/**
 * This class handles errors regarding invalid EAN code.
 */
public class InvalidEanCodeException extends Exception {
    private final int invalidEanCode;

    /**
     * Creates a new instance of the exception with a message detailing which EAN code was invalid.
     * @param invalidEanCode The invalid EAN code.
     */
    public InvalidEanCodeException(int invalidEanCode) {
        super("EAN code [" + invalidEanCode + "] does not correspond to an item in inventory");
        this.invalidEanCode = invalidEanCode;
    }

    /**
     * Getter for invalidEanCode.
     * @return the invalid EAN code for this instance.
     */
    public int getInvalidEanCode() {
        return invalidEanCode;
    }
}
