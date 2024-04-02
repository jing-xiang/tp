package longah.node;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents objects where the time element is concerned
 */
public class DateTime {
    private LocalDateTime dateTime;

    /**
     * Constructs a new DateTime object based on a String representation of the date time expression
     *
     * @param dateTimeExpression String representation of a date time expression
     * @throws LongAhException If the date time expression does not follow the intended date time format
     */
    public DateTime(String dateTimeExpression) throws LongAhException {
        try {
            this.dateTime = LocalDateTime.parse(dateTimeExpression.trim(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new LongAhException(ExceptionMessage.INVALID_TIME_FORMAT);
        }
    }

    /**
     * Converts the date time object into a String expression suitable for storage
     *
      * @return A string representation of the date time object suitable for storage
     */
    public String toStorageString() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Converts the date time object into a String expression suitable for printing
     *
     * @return A string representation of the date time object suitable for printing
     */
    public String toString() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma"));
    }

}
