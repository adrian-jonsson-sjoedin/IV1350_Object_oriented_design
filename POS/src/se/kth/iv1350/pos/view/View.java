package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.Printer;

/**
 * Placeholder class simulating the user interface for the application. Only contains hard code method calls.
 *
 * @author Adrian Jonsson Sjoedin.
 */
public class View {
    private Controller ctrl;
    private Printer printer;

    /**
     * Constructor that creates a new instance of View, uses the specified controller for all calls to other layers
     * as well as passing a reference to the printer.
     *
     * @param ctrl    The controller that is used by view for calls to other layers.
     * @param printer The printer that will be used to print the receipt,
     */
    public View(Controller ctrl, Printer printer) {
        this.ctrl = ctrl;
        this.printer = printer;
    }

    /**
     * Simulates a sale from start from finish be calling all systems operations in controller
     */
    public void simulateOneSale() {
        ctrl.initializeNewSale();
        System.out.println();
        System.out.println("\t\t New sale started");
        System.out.println("------------------------------------------------------");
        ctrl.scanAndAddNewItemFromInventoryToSale(3006,1);
        ctrl.scanAndAddNewItemFromInventoryToSale(6880, 3);
        ctrl.scanAndAddNewItemFromInventoryToSale(4680, 1);
        ctrl.displayCurrentSaleInfo();
    }
}
