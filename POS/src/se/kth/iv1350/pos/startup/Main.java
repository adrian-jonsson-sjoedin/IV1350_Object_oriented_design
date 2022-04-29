package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.ExternalSystemsCreator;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.view.View;

/**
 * Contains the main method of the POS. Starts the whole application.
 * @author Adrian Jonsson Sjoedin
 */

public class Main {
    /**
     * Main method used to start the application.
     * @param args Does not require any parameters or command line inputs.
     */
    public static void main(String[] args) {
        ExternalSystemsCreator externalSystems = new ExternalSystemsCreator();
        Printer printer = new Printer();
        Controller ctrl = new Controller(externalSystems, printer);
        View view = new View(ctrl, printer);
    }
}
