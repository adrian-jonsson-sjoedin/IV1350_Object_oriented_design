package se.kth.iv1350.pos.integration;

/**
 * DTO for the item
 */
public class ItemDTO {
    private String itemName;
    private int eanCode;
    private double itemPrice;
    private double vatRate;

    /**
     * Created an item with the specified parameters.
     * @param itemName Describes the item.
     * @param eanCode Unique identifier for the item.
     * @param itemPrice The price for the item.
     * @param vatRate The items VAT rate.
     */
  public ItemDTO(String itemName, int eanCode, double itemPrice, double vatRate) {
        this.itemName = itemName;
        this.eanCode = eanCode;
        this.itemPrice = itemPrice;
        this.vatRate = vatRate;
    }

    public String getItemName() {
        return itemName;
    }

    public int getEanCode() {
        return eanCode;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getVatRate() {
        return vatRate;
    }
}
