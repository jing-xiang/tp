package longah.handler;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PINHandlerTest {

    /**
     * Tests the successful file creation when the PINHandler is constructed.
     */
    @Test
    public void pinHandlerConstructor_fileCreationSuccess() {
        try {
            File f = new File("./data/pin.txt");
            System.setIn(new ByteArrayInputStream("123456\n".getBytes(StandardCharsets.UTF_8)));
            System.setIn(new ByteArrayInputStream("123456\n".getBytes(StandardCharsets.UTF_8)));
            new PINHandler();
            assertTrue(f.exists());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the successful creation of a PIN with a valid PIN entered.
     */
    @Test
    public void createPin_validPIN_success() {
        try {
            File f = new File("./data/pin.txt");
            System.setIn(new ByteArrayInputStream("123456\n".getBytes(StandardCharsets.UTF_8)));
            System.setIn(new ByteArrayInputStream("123456\n".getBytes(StandardCharsets.UTF_8)));
            new PINHandler();
            assertTrue(f.exists());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the successful authentication of a PIN with a valid entered PIN.
     */
    @Test
    public void authenticate_validPIN_success() {
        try {
            File f = new File("./data/pin.txt");
            System.setIn(new ByteArrayInputStream("123456\n".getBytes(StandardCharsets.UTF_8)));
            new PINHandler();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPin = md.digest("123456".getBytes(StandardCharsets.UTF_8));
            String hashedEnteredPinHex = new BigInteger(1, hashedPin).toString(16);
            assertEquals((hashedEnteredPinHex), PINHandler.getSavedPin());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful authentication of a PIN with an invalid entered PIN.
     */
    @Test
    public void authenticate_invalidPIN_authenticateFailure() {
        try {
            System.setIn(new ByteArrayInputStream("1234567\n".getBytes(StandardCharsets.UTF_8)));
            new PINHandler();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPin = md.digest("1234567\n".getBytes(StandardCharsets.UTF_8));
            String hashedEnteredPinHex = new BigInteger(1, hashedPin).toString(16);
            assertNotEquals((hashedEnteredPinHex), PINHandler.getSavedPin());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful creation of a PIN with an invalid entered PIN.
     */
    @Test
    public void createPin_invalidPIN_failure() {
        try {
            File f = new File("./data/pin.txt");
            System.setIn(new ByteArrayInputStream("1234567\n1234567\n".getBytes(StandardCharsets.UTF_8)));
            new PINHandler();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPin = md.digest("1234567\n".getBytes(StandardCharsets.UTF_8));
            String hashedEnteredPinHex = new BigInteger(1, hashedPin).toString(16);
            assertNotEquals((hashedEnteredPinHex), PINHandler.getSavedPin());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the successful enabling of PIN authentication with valid login.
     */
    @Test
    public void enablePin_validPIN_success() {
        try {
            File f = new File("./data/pin.txt");
            System.setIn(new ByteArrayInputStream("123456\n".getBytes(StandardCharsets.UTF_8)));
            new PINHandler();
            System.setIn(new ByteArrayInputStream("123456\n".getBytes(StandardCharsets.UTF_8)));
            PINHandler.enablePin();
            assertTrue(PINHandler.getAuthenticationStatus());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the successful disabling of PIN authentication with valid login.
     */
    @Test
    public void disablePin_validPIN_success() {
        try {
            File f = new File("./data/pin.txt");
            PINHandler.enablePin();
            System.setIn(new ByteArrayInputStream("123456\n".getBytes(StandardCharsets.UTF_8)));
            PINHandler.disablePin();
            assertTrue(!PINHandler.getAuthenticationStatus());
        } catch (Exception e) {
            fail();
        }
    }
}
