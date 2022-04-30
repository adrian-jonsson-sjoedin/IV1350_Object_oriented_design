package se.kth.iv1350.pos.integration;

import java.util.List;

/**
 * This class represents the external inventory system for the application. It currently only contains a hardcoded
 * inventory.
 */
public class ExternalInventorySystem {
    private List<ItemDTO> inventory;

    /**
     * Package private constructor that creates the placeholder inventory.
     */
    ExternalInventorySystem() {
        inventory.add(new ItemDTO("Apple, aroma", 3006, 5, 5));
        inventory.add(new ItemDTO("Cinnamon bun", 6880, 7.95, 5));
        inventory.add(new ItemDTO("Red paprika", 4680, 10.95, 5));
        inventory.add(new ItemDTO("Carlsberg 330ml", 1111, 11.95, 10));
        inventory.add(new ItemDTO("Tampongs", 2222, 26.95, 2));
    }

    /**
     * This method takes an eanCode and checks if an item with that eanCode is already in the inventory.
     *
     * @param eanCode Used to search for the item in the list.
     * @return True if an item with the parameter is already in the inventory, otherwise returns false.
     */
    public boolean checkIfItemExists(int eanCode) {
        for (ItemDTO itemDTO : inventory) {
            if (itemDTO.getEanCode() == eanCode) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the item in the inventory and retrieves a reference to it.
     *
     * @param eanCode Identifies which item that should be retrieved.
     * @return Returns a reference to that item.
     */
    public ItemDTO retrieveItem(int eanCode) {
        ItemDTO itemToRetrieve;
        if (checkIfItemExists(eanCode)) {
            for (ItemDTO itemDTO : inventory) {
                if (itemDTO.getEanCode() == eanCode) {
                    itemToRetrieve = itemDTO;
                    return itemToRetrieve;
                }
            }
        }
        return null;
    }

    public List<ItemDTO> getInventory() {
        return inventory;
    }
}
