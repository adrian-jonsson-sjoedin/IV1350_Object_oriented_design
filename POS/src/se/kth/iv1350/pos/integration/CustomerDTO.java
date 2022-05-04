package se.kth.iv1350.pos.integration;

/**
 * DTO for a member
 */
public class CustomerDTO {
    private final String name;
    private final long personalNr;

    /**
     * @param name       Is the full name of the member
     * @param personalNr What is used to identify a customer
     */
    public CustomerDTO(String name, long personalNr) {
        this.name = name;
        this.personalNr = personalNr;
    }

    public String getName() {
        return name;
    }

    public long getPersonalNr() {
        return personalNr;
    }
}
