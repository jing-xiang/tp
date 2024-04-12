package longah.handler;

import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

public class Logging {
    private static final String loggerDir = "./log";
    private static final String loggerFile = "./log/LongAh.log";
    private static Logger longAhLogger = Logger.getLogger("LongAh");

    /**
     * Constructs a new Logging instance.
     * Creates a log file to store log data.
     */
    public Logging() {
        // @@author FeathersRe
        try {
            File f = new File(loggerDir);
            if (!f.exists()) {
                f.mkdir();
            }
            f = new File(loggerFile);
            if (!f.exists()) {
                f.createNewFile();
            }
            
            FileHandler handler = new FileHandler(loggerFile);
            handler.setFormatter(new SimpleFormatter());
            longAhLogger.addHandler(handler);
            longAhLogger.setUseParentHandlers(false);
            disableConsoleLogging();
        } catch (IOException e) {
            longAhLogger.log(Level.WARNING, "Log data may not be saved due to permission.");
        }
        // @@author
    }

    /**
     * Logs the message with the specified level.
     * 
     * @param level The level of the log message
     * @param message The message to be logged
     */
    public static void log(Level level, String message) {
        longAhLogger.log(level, message);
    }

    /**
     * Logs an info message.
     * 
     * @param message The message to be logged
     */
    public static void logInfo(String message) {
        longAhLogger.log(Level.INFO, message);
    }

    /**
     * Logs a warning message.
     * 
     * @param message The message to be logged
     */
    public static void logWarning(String message) {
        longAhLogger.log(Level.WARNING, message);
    }

    public static void disableConsoleLogging() {
        Handler[] handlers = longAhLogger.getHandlers();
        for (Handler handler : handlers) {
            if (handler instanceof ConsoleHandler) {
                handler.setLevel(Level.OFF);
            }
        }
    }
}
