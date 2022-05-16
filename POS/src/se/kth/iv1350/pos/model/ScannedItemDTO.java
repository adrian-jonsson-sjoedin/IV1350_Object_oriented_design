package se.kth.iv1350.pos.model;

/**
 * This class is used to send information to <code>View</code> about whether the item got added or not as well as
 * the items EAN code
 */
public class ScannedItemDTO {
    private final boolean added;
    private final int eanCode;

    public ScannedItemDTO(boolean added, int eanCode) {
        this.added = added;
        this.eanCode = eanCode;
    }

    public boolean isAdded() {
        return added;
    }

    public int getEanCode() {
        return eanCode;
    }
}
