package se.kth.iv1350.pos.integration;

/**
 * DTO for a member
 */
public class CustomerDTO {
    private final String name;
    private final int personalNr;

    /**
     * @param name       Is the full name of the member
     * @param personalNr What is used to identify a customer
     */
    public CustomerDTO(String name, int personalNr) {
        this.name = name;
        this.personalNr = personalNr;
    }

    public String getName() {
        return name;
    }

    public int getPersonalNr() {
        return personalNr;
    }
}
