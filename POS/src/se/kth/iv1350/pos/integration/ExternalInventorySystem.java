package se.kth.iv1350.pos.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the external inventory system for the application. It currently only contains a hardcoded
 * inventory that can not be extended.
 */
public class ExternalInventorySystem {
    private List<ItemDTO> inventory = new ArrayList<>();

    /**
     * Package private constructor that creates the placeholder inventory.
     */
    ExternalInventorySystem() {
        inventory.add(new ItemDTO("Apple, aroma", 3006, 5, 12));
        inventory.add(new ItemDTO("Cinnamon bun", 6880, 7.95, 12));
        inventory.add(new ItemDTO("Red paprika", 4680, 10.95, 12));
        inventory.add(new ItemDTO("Carlsberg 330ml", 1111, 11.95, 10));
        inventory.add(new ItemDTO("Tampongs", 2222, 26.95, 25));
    }

    /**
     * DEPRECATED for seminar 4 since exception handling is introduced
     * <p>
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
     * @throws InvalidEanCodeException for any EAN codes that is not in the inventory. Is checked
     * @throws InventoryDBUnresponsiveException for simulated database error. Is unchecked
     */
    public ItemDTO retrieveItem(int eanCode) throws InvalidEanCodeException, InventoryDBUnresponsiveException {
        ItemDTO itemToRetrieve;
        if(eanCode == 6969){
            throw new InventoryDBUnresponsiveException("Inventory database unreachable");
        }
        for (ItemDTO itemDTO : inventory) {
            if (itemDTO.getEanCode() == eanCode) {
                itemToRetrieve = itemDTO;
                return itemToRetrieve;
            }
        }
        throw new InvalidEanCodeException(eanCode);
    }

    public List<ItemDTO> getInventory() {
        return inventory;
    }
}
