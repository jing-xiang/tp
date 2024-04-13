package longah.handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

/**
 * Handles the creation, loading, authentication, and resetting of the PIN.
 */
public class PINHandler {
    private static final String PIN_FILE_PATH = "./data/pin.txt";
    private static String savedPin;
    private static boolean authenticationEnabled;

    // @@author jing-xiang
    /**
     * Constructs a new PINHandler instance.
     */
    public PINHandler() {
        StorageHandler.initDir();
        loadPinAndAuthenticationEnabled();
        if (!Files.exists(Paths.get(PINHandler.getPinFilePath())) || savedPin.isEmpty()) {
            createPin();
        }
        if (authenticationEnabled) {
            authenticate();
        }
    }

    /**
     * Loads the saved PIN and authentication enabled state from the file.
     */
    public static void loadPinAndAuthenticationEnabled() {
        try {
            String[] data = new String(Files.readAllBytes(Paths.get(PIN_FILE_PATH))).split("\n");
            savedPin = data[0];
            if (data.length > 1) {
                authenticationEnabled = Boolean.parseBoolean(data[1].trim());
            }
            Logging.logInfo("User loaded successfully.");
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            UI.showMessage("Error reading saved PIN and authentication enabled state.");
        }
    }

    /**
     * Saves the PIN and authentication enabled state to the file.
     */
    public static void savePinAndAuthenticationEnabled() {
        try {
            String data = savedPin + "\n" + authenticationEnabled;
            Files.write(Paths.get(PIN_FILE_PATH), data.getBytes());
            Logging.logInfo("PIN saved successfully.");
        } catch (IOException e) {
            UI.showMessage("Error saving PIN and authentication enabled state.");
        }
    }

    /**
     * Returns the file path of the PIN file where the PIN is encrypted and saved.
     *
     * @return The file path of the PIN file where the PIN is encrypted and saved.
     */
    public static String getPinFilePath() {
        return PIN_FILE_PATH;
    }

    /**
     * Creates a new PIN for the user.
     */
    public static void createPin() {
        UI.showMessage("Create your 6-digit PIN:");
        String pin = UI.getUserInput();

        // check if the input is a 6-digit number
        while (pin.length() != 6 || !pin.matches("\\d{6}")) {
            if (Objects.equals(pin, "exit")) {
                System.exit(0);
            }
            UI.showMessage("Invalid PIN. Your PIN must be a 6-digit number. " +
                    "Please try again, or enter 'exit' to exit LongAh.");
            UI.showMessage("Enter a 6-digit PIN: ", false);
            pin = UI.getUserInput();
        }

        assert pin != null : "PIN should not be null.";
        assert pin.length() == 6 : "PIN should be 6 digits long.";

        try {
            // Encrypt PIN before saving
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPin = md.digest(pin.getBytes(StandardCharsets.UTF_8));
            String hashedPinHex = new BigInteger(1, hashedPin).toString(16);
            savedPin = hashedPinHex;
            savePinAndAuthenticationEnabled();
            UI.showMessage("PIN saved successfully! You can enter 'pin enable' to enable authentication upon startup.");
            Logging.logInfo("PIN saved successfully!");
        } catch (NoSuchAlgorithmException e) {
            UI.showMessage("Error saving PIN. Please try again.");
        }
    }

    /**
     * Authenticates the user by comparing the entered PIN with the saved PIN.
     */
    public static void authenticate() {
        if (!authenticationEnabled) {
            return;
        }
        assert savedPin != null : "Saved PIN should not be null.";

        UI.showMessage("Enter your PIN: ", false);
        String enteredPin = UI.getUserInput();
        assert enteredPin != null : "Entered PIN should not be null.";

        try {
            // Hash the entered PIN before comparing
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedEnteredPin = md.digest(enteredPin.getBytes(StandardCharsets.UTF_8));
            String hashedEnteredPinHex = new BigInteger(1, hashedEnteredPin).toString(16);

            while (!hashedEnteredPinHex.equals(savedPin)) {
                if (Objects.equals(enteredPin, "exit") || Objects.equals(enteredPin, "close")) {
                    UI.exit();
                }
                UI.showMessage("Invalid PIN. Please try again. Alternatively, enter 'exit' or 'close' " + 
                        "to exit LongAh.");
                UI.showMessage("Enter your PIN: ", false);
                enteredPin = UI.getUserInput();
                hashedEnteredPin = md.digest(enteredPin.getBytes(StandardCharsets.UTF_8));
                hashedEnteredPinHex = new BigInteger(1, hashedEnteredPin).toString(16);
            }
            Logging.logInfo("Login successful!");
            UI.showMessage("Login successful!");
        } catch (NoSuchAlgorithmException e) {
            UI.showMessage("Error authenticating PIN. Please try again.");
        }
    }

    /**
     * Resets the PIN for the user with a new PIN.
     */
    public static void resetPin() {
        UI.showMessage("Enter your current PIN: ", false);
        String enteredPin = UI.getUserInput();
        assert enteredPin != null : "Entered PIN should not be null.";

        try {
            // Hash the entered PIN before comparing
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedEnteredPin = md.digest(enteredPin.getBytes(StandardCharsets.UTF_8));
            String hashedEnteredPinHex = new BigInteger(1, hashedEnteredPin).toString(16);

            if (hashedEnteredPinHex.equals(savedPin)) {
                // If the entered PIN is correct, allow the user to create a new PIN
                createPin();
                Logging.logInfo("PIN reset successful!");
            } else {
                UI.showMessage("Invalid PIN. Please try again later.");
            }
        } catch (NoSuchAlgorithmException e) {
            UI.showMessage("Error resetting PIN. Please try again.");
        }
    }

    /**
     * Enables authentication upon startup.
     */
    public static void enablePin() {
        if (!authenticationEnabled) {
            authenticationEnabled = true;
            savePinAndAuthenticationEnabled();
            UI.showMessage("Authentication enabled upon startup.");
        } else {
            UI.showMessage("Authentication is already enabled.");
        }
    }

    /**
     * Disables authentication upon startup.
     */
    public static void disablePin() {
        if (authenticationEnabled) {
            authenticationEnabled = false;
            savePinAndAuthenticationEnabled();
            UI.showMessage("Authentication disabled upon startup.");
        } else {
            UI.showMessage("Authentication is already disabled.");
        }
    }

    /**
     * Returns the saved PIN.
     *
     * @return The saved PIN.
     */
    public static String getSavedPin() {
        return savedPin;
    }

    /**
     * Returns the authentication status.
     *
     * @return The authentication status.
     */
    public static boolean getAuthenticationStatus() {
        return authenticationEnabled;
    }
}
