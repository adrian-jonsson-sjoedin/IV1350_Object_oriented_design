package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.controller.OperationFailedException;
import se.kth.iv1350.pos.integration.InvalidEanCodeException;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.model.ItemInBasket;
import se.kth.iv1350.pos.model.ScannedItemDTO;

import java.io.IOException;
import java.util.List;

/**
 * Placeholder class simulating the user interface for the application. Only contains hard code method calls.
 *
 * @author Adrian Jonsson Sjoedin.
 */
public class View {
    private Controller ctrl;
    private Printer printer;
    private ErrorMessageHandler errorMessageHandler;

    /**
     * Constructor that creates a new instance of View, uses the specified controller for all calls to other layers
     * as well as passing a reference to the printer.
     *
     * @param ctrl    The controller that is used by view for calls to other layers.
     * @param printer The printer that will be used to print the receipt,
     */
    public View(Controller ctrl, Printer printer) throws IOException {
        this.ctrl = ctrl;
        this.printer = printer;
        this.errorMessageHandler = new ErrorMessageHandler();
    }

    /**
     * Simulates a sale from start from finish be calling all systems operations in controller
     */
    public void simulateOneSale() throws InvalidEanCodeException {
        ctrl.initializeNewSale();
        System.out.println();
        System.out.println("\t\t New sale started");
        System.out.println("------------------------------------------------------");
        try {
            ctrl.scanAndAddNewItemFromInventoryToSale(3006, 1);
            ctrl.scanAndAddNewItemFromInventoryToSale(6880, 3);
            ctrl.scanAndAddNewItemFromInventoryToSale(4680, 1);
            ctrl.scanAndAddNewItemFromInventoryToSale(3006, 1);
            ctrl.scanAndAddNewItemFromInventoryToSale(2222, 1);
            ctrl.scanAndAddNewItemFromInventoryToSale(1111, 5);
            //invalid EAN code
            ctrl.scanAndAddNewItemFromInventoryToSale(94011, 1);
        }catch(InvalidEanCodeException | OperationFailedException ex){
            handleException("Could not register item");

        }

        List<ItemInBasket> currentBasket = ctrl.getCurrentBasket();
        displayCurrentBasket(currentBasket);

        System.out.println("------------------------------------------------------");
        System.out.printf("Price: %-1.2f:-%n", ctrl.getRunningTotal());
        System.out.printf("VAT: %-1.2f:-%n", ctrl.getTotalVat());

        if (ctrl.addDiscount(9404075179L)) {
            System.out.println(">>> Discount added");
        } else {
            System.out.println(">>> Not a member.");
        }

        System.out.printf("Total: %-1.2f:-%n", ctrl.getTotal());
        ctrl.endSaleWithPayment(200);
        System.out.println("Amount paid is: " + 200 + ":-");
        System.out.printf("Change is: %.2f:-%n", ctrl.getChange());
        System.out.printf("The current balance left in the register is: %.2f:-%n", ctrl.getRegisterBalance());
        ctrl.getReceipt();
        System.out.println("------------------------------------------------------");
        System.out.println("\t\t Sale has ended");
        System.out.println("------------------------------------------------------");

    }

    private void handleException(String msg) {
        errorMessageHandler.showErrorMsg(msg);
    }
    private void displayCurrentBasket(List<ItemInBasket> currentBasket) {
        for (ItemInBasket itemInBasket : currentBasket) {
            printToString(itemInBasket);
        }

    }

    private void printToString(ItemInBasket item) {
        String itemName = item.getItemName();
        double itemPrice = item.getItemPrice();
        int itemQuantity = item.getQuantity();
        System.out.printf("%-20s %1d * %1.2f:- %n ", itemName, itemQuantity, itemPrice);
        System.out.println();
    }

    private void displayInvalidItem(List<ScannedItemDTO> basket) {
        for (ScannedItemDTO itemInBasket : basket) {
            if (!itemInBasket.isAdded()) {
                System.out.println("Invalid item: " + itemInBasket.getEanCode());
            }
        }
    }
}
