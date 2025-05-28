package se.kth.iv1350.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Simple file logger for error reporting
 * 
 * @author Viktor Sandström
 * @author Adrian Boström
 */
public class FileLogger {
    private static final String LOG_FILE = "error-log.txt";

    /**
     * Writes down exception to file: "error-log.txt"
     * 
     * @param e The exception to be written to the file
     */
    public void logException(Exception e) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println(LocalDateTime.now() + " - " + e.getClass().getSimpleName() + ": " + e.getMessage());
            for (StackTraceElement elem : e.getStackTrace()) {
                out.println("\tat " + elem);
            }
        } catch (IOException io) {
            System.out.println("Could not write to log file: " + io.getMessage());
        }
    }
}