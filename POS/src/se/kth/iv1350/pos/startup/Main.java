package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.controller.OperationFailedException;
import se.kth.iv1350.pos.integration.ExternalSystemsCreator;
import se.kth.iv1350.pos.integration.InvalidEanCodeException;
import se.kth.iv1350.pos.integration.InventoryDBUnresponsiveException;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.view.View;

import java.io.IOException;

/**
 * Contains the main method of the POS. Starts the whole application.
 *
 * @author Adrian Jonsson Sjoedin
 */

public class Main {
    /**
     * Main method used to start the application.
     *
     * @param args Does not require any parameters or command line inputs.
     */
    public static void main(String[] args) throws InvalidEanCodeException, InventoryDBUnresponsiveException,
            OperationFailedException, IOException {
        try {
            ExternalSystemsCreator externalSystems = new ExternalSystemsCreator();
            Printer printer = new Printer();
            Controller ctrl = new Controller(externalSystems, printer);
            View view = new View(ctrl, printer);
            view.simulateOneSale();
        }catch (IOException ex){
            System.out.println("The process failed to run");
            ex.printStackTrace();
        }
    }
}
