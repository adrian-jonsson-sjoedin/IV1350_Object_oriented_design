package se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class creates a log file for possible errors
 */
public class LogHandler {
    private static final String FILE_NAME = "POS_Exception_log.txt";
    private PrintWriter exceptionLog;

    /**
     * Creates an instance of the log handler. Initialize the file writer and creates the file.
     *
     * @throws IOException
     */
    public LogHandler() throws IOException {
        exceptionLog = new PrintWriter(new FileWriter(FILE_NAME), true);
    }

    /**
     * Writes a log entry describing a thrown exception.
     *
     * @param exception The exception to be logged.
     */
    public void logException(Exception exception) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(", Exception was thrown: ");
        logMsgBuilder.append("<" + exception.getMessage() + ">");
        exceptionLog.println(logMsgBuilder);
        exception.printStackTrace(exceptionLog);
        exceptionLog.println("\n");
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
