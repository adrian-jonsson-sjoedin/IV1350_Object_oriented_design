package se.kth.iv1350.pos.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent the membership database used to check against to se if a customer is a member and thus eligible
 * for a discount. It currently only contains a hardcode members list and no functionality to add new members.
 */
public class MemberDatabase {
    private List<CustomerDTO> members = new ArrayList<>();

    /**
     * Package private constructor that creates the placeholder membership list.
     */
    MemberDatabase() {
        members.add(new CustomerDTO("John Doe", 2001011111));
    }

    /**
     * Checks if a customer is a member.
     * @param personalNr The id that is used to identify a member.
     * @return True if they are a member, otherwise false.
     */
    public boolean customerIsMember(int personalNr) {
        for (CustomerDTO customer : members) {
            if (customer.getPersonalNr() == personalNr) {
                return true;
            }
        }
        return false;
    }

    public List<CustomerDTO> getMembers() {
        return members;
    }
}
