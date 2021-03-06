package se.kth.iv1350.pos.util;

import se.kth.iv1350.pos.model.SaleObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

/**
 * This class prints the total revenue generated over multiple sales to a text file
 */
public class TotalRevenueFileOutput implements SaleObserver {
    private static final String FILE_NAME = "Revenue_log.txt";
    private PrintWriter fileOutput;
    private double total;

    /**
     * Creates a new instance. This should only be done once.
     */
    public TotalRevenueFileOutput() {
        total = 0d;
    }

    /**
     * Updates the total revenue and prints it to a text file
     *
     * @param amountPaid the amount to add to the total revenue.
     */
    @Override
    public void totalRevenue(double amountPaid) {
        total += amountPaid;
        try {
            printToFile(total);
        } catch (IOException ex) {
            Logger.getLogger(TotalRevenueFileOutput.class.getName());
        }
    }

    private void printToFile(double amount) throws IOException {
        fileOutput = new PrintWriter(new FileWriter(FILE_NAME), true);
        fileOutput.println("------------------------------------------------------");
        fileOutput.println("Time: " + LocalTime.now());
        fileOutput.println("Date: " + LocalDate.now());
        fileOutput.printf("The total revenue is: %-1.2f:-%n", amount);
        fileOutput.println();
        fileOutput.println("------------------------------------------------------");
    }
}
