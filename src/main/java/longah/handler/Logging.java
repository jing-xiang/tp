package longah.handler;

import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

public class Logging {
    private static Logger LongAhLogger = Logger.getLogger("LongAh");

    /**
     * Constructs a new Logging instance.
     * Creates a log file to store log data.
     */
    public Logging() {
        try {
            FileHandler handler = new FileHandler("./log/LongAh.log");
            handler.setFormatter(new SimpleFormatter());
            LongAhLogger.addHandler(handler);
            LongAhLogger.setUseParentHandlers(false);
        } catch (IOException e) {
            LongAhLogger.log(Level.WARNING, "Log data may not be saved due to permission.");
        }
    }

    /**
     * Logs the message with the specified level.
     * 
     * @param level The level of the log message
     * @param message The message to be logged
     */
    public static void log(Level level, String message) {
        LongAhLogger.log(level, message);
    }

    /**
     * Logs an info message.
     * 
     * @param message The message to be logged
     */
    public static void logInfo(String message) {
        LongAhLogger.log(Level.INFO, message);
    }

    /**
     * Logs a warning message.
     * 
     * @param message The message to be logged
     */
    public static void logWarning(String message) {
        LongAhLogger.log(Level.WARNING, message);
    }
}
