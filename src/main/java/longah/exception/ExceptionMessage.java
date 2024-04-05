package longah.exception;

public enum ExceptionMessage {
    // [Cause of Exception]([Message to be printed], [Type of Exception])
    // General Exceptions
    INVALID_INDEX ("Invalid index.", ExceptionType.WARNING),

    // Member Exceptions
    DUPLICATE_MEMBER ("Duplicate member.", ExceptionType.INFO),
    INVALID_MEMBER_NAME ("Invalid member name.", ExceptionType.INFO),
    MEMBER_NOT_FOUND ("Member not found.", ExceptionType.INFO),
    NO_MEMBERS_FOUND ("Member list is empty.", ExceptionType.INFO),

    // Group and Group List Exceptions
    INVALID_GROUP_NAME ("Invalid group name.", ExceptionType.INFO),
    DUPLICATE_GROUP ("Duplicate group.", ExceptionType.INFO),
    EMPTY_GROUP_LIST ("Group list is empty.", ExceptionType.INFO),
    GROUP_NOT_FOUND ("Group not found.", ExceptionType.INFO),

    // Transaction Exceptions
    INVALID_TRANSACTION_FORMAT ("Invalid transaction format.", ExceptionType.WARNING),
    INVALID_TIME_FORMAT ("Invalid DateTime format.", ExceptionType.WARNING),
    INVALID_TRANSACTION_VALUE ("Invalid transaction value.", ExceptionType.WARNING),
    INVALID_VALUE_FORMAT ("Invalid value format.", ExceptionType.WARNING),

    // TransactionList Exceptions
    NO_TRANSACTION_FOUND ("No transactions found.", ExceptionType.INFO),
    NO_DEBTS_FOUND ("No debts found.", ExceptionType.INFO),
    TRANSACTIONS_SUMMED_UP ("No pending payments.", ExceptionType.INFO),
    INVALID_DATE_TIME_FILTER ("Invalid datetime filter. The to date your are searching for " +
            "is before the from date.", ExceptionType.INFO),

    // Data Storage Exceptions
    STORAGE_FILE_NOT_FOUND ("File not found.", ExceptionType.WARNING),
    STORAGE_FILE_NOT_CREATED ("File not created.", ExceptionType.WARNING),
    STORAGE_FILE_NOT_READ ("File not read.", ExceptionType.WARNING),
    STORAGE_FILE_NOT_WRITTEN ("File not written.", ExceptionType.WARNING),
    INVALID_STORAGE_CONTENT ("Invalid content in storage file, line ignored.", ExceptionType.WARNING),
    STORAGE_FILE_CORRUPTED ("Storage file is corrupted." + 
                "We recommend running 'clear' or manually resolving the error data.", ExceptionType.WARNING),
    IO_EXCEPTION ("An error occurred while reading/writing to the file.", ExceptionType.WARNING),
    // Ui exceptions
    INVALID_COMMAND ("Invalid command. Use 'help' to see the list of commands.",
            ExceptionType.INFO),
    COMMAND_NOT_IMPLEMENTED ("This feature has yet to be implemented.",
            ExceptionType.INFO),
    INVALID_ADD_COMMAND ("Invalid command format." +
            " Use 'add member NAME' or 'add transaction LENDER p/BORRWER1 a/AMOUNT1 ...\n" +
            "or 'add group GROUP_NAME'",
            ExceptionType.INFO),
    INVALID_LIST_COMMAND ("Invalid command format." +
            " Use 'list members', 'list transactions', or 'list debts'",
            ExceptionType.INFO),
    INVALID_FIND_COMMAND ("Invalid command format." +
            " Use 'find transactions NAME' or 'find debts NAME'",
            ExceptionType.INFO),

    INVALID_FILTER_COMMAND ("Invalid filter command." +
            "Use 'filter CRITERIA'",
            ExceptionType.INFO),
    INVALID_FILTER_DATETIME_COMMAND("Invalid filter datetime command." +
            " Use 'filter datetime b/DateTime' or 'filter datetime a/DateTime' or " +
            "'filter datetime a/Datetime b/Datetime' or 'filter datetime Datetime",
            ExceptionType.INFO),
    INVALID_SETTLEUP_COMMAND ("Invalid command format." +
            " Use 'settleup PERSON'",
            ExceptionType.INFO),
    INVALID_DELETE_COMMAND ("Invalid command format." +
            " Use 'delete transaction INDEX' or 'delete member NAME' or 'delete group GROUP_NAME'",
            ExceptionType.INFO),
    INVALID_CLEAR_COMMAND ("Invalid command format." +
            " Use 'clear'",
            ExceptionType.INFO),
    INVALID_RESET_COMMAND ("Invalid command format." +
            " Use 'reset password'",
            ExceptionType.INFO),
    INVALID_EDIT_COMMAND("Invalid command format." +
            " Use 'edit transaction INDEX NEW_TRANSACTION' or 'edit member INDEX NEW_NAME'",
            ExceptionType.INFO),
    INVALID_PIN_COMMAND("Invalid command format." +
            " Use 'pin edit' or 'pin enable' or 'pin disable'",
            ExceptionType.INFO),
    INVALID_EXIT_COMMAND ("Invalid command format." +
            " Use 'exit'", ExceptionType.INFO),
    INVALID_VIEW_COMMAND ("Invalid command format." +
            " Use 'view chart'", ExceptionType.INFO),
    INVALID_HELP_COMMAND ("Invalid command format." +
            " Use 'help'", ExceptionType.INFO),
    INVALID_SWITCH_GROUP_COMMAND ("Invalid command format." +
            " Use 'group GROUP_NAME'", ExceptionType.INFO);

    private final String message;
    private final ExceptionType type;

    /**
     * Constructor for ExceptionMessage.
     * 
     * @param message The message to be printed when the exception is called.
     */
    ExceptionMessage(String message, ExceptionType type) {
        this.message = message;
        this.type = type;
    }

    /**
     * Returns the message of the exception.
     * 
     * @return The message of the exception.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns the type of the exception.
     * 
     * @return The type of the exception.
     */
    public ExceptionType getType() {
        return this.type;
    }
}
