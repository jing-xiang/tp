# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### UI and I/O

### Storage

### Member and MemberList

### Transaction and TransactionList
<ins>Transaction Overview</ins>

The Transaction class is responsible for representing a single transaction in the LongAh application between 2 members.
It contains information about the lender, borrowers, and the amount involved in the transaction.


<ins>Class Fields</ins>
* lender: Represents the member who lent the money.
* subtransactions: An ArrayList of Subtransaction objects, representing individual borrowings within the transaction.

<ins>Transaction Constructor</ins>

`Transaction(Member lender, ArrayList<Member> borrowers, double amount)`
* Parses the given user input and creates a new Transaction object with the specified lender, borrowers, and amount.

`Transaction(Member lender, ArrayList<Subtransaction> subtransactions,
MemberList members)`
* Constructs a transaction instance using specified lender, subtransactions, and member list.
* This constructor is used for storage methods only.

<ins>Transaction Methods</ins>

*parseTransaction*: Parses the user input to extract lender and borrowers, then adds them to the transaction.

*addBorrower*: Adds a borrower to the transaction.

*getLender*: Returns the lender of the transaction.

*isLender*: Checks if a given member is the lender of the transaction.

*isborrower*: Checks if a given member is a borrower in the transaction.

*isInvolved*: Checks if a given member is involved in the transaction.

*toStorageString*: Converts the transaction to a string format for storage.

*getSubtransactions*: Returns the list of subtransactions in the transaction.

*editTransaction*: Edits the transaction based on new user input.

*deleteMember*: Deletes a member from the transaction and returns true if transaction needs to be removed.


<ins>TransactionList Overview</ins>

The TransactionList class is responsible for managing a list of transactions in the LongAh application. It provides methods to add, delete, and retrieve transactions from the list.
<ins>Usage Example</ins>

<ins>Class Fields</ins>

*transactions*: An ArrayList of Transaction objects representing the list of transactions in LongAh!.

<ins>TransactionList Methods</ins>

*addTransaction*: Parses user input and adds a new transaction to the list.

*getTransactionListSize*: Returns the size of the transaction list.

*remove*: Removes a transaction from the list based on the index.

*clear*: Clears all transactions from the list.

*getTransaction*: Returns the list of transactions.

*listTransactions*: Lists all transactions in the transaction list.

*findLender*: Finds all transaction where a specified member is the lender.

*findBorrower*: Finds all transaction where a specified member is a borrower.

*findTransactions*: Finds a transaction based on member name.

*editTransactionList*: Edits a transaction in the list based on user input.

*findDebts*: Finds all debts owed by a specified member.

*deleteMember*: Deletes a member from all transactions in the list.

<ins>Example Usage</ins>

Adding a new transaction:
![addTransaction.png](diagrams%2Faddtransaction.png)

Given below is an example usage scenario and how the Transaction class behaves at each step:

```
Step 1. The user enters a new transaction using the 'add transaction' command with the lender, borrower(s), and amount(s) specified.
Step 2. The TransactionList class takes in the user input and creates a new Transaction object with the specified details for the specified memberList.
Step 3. The Transaction class parses the user input to extract the lender and borrower(s) and adds them to the transaction.
Step 4. The Transaction object is added to the TransactionList, which stores the list of transactions.
Step 5: The Logger class logs the new transaction for information tracking, and logs a warning if an invalid transaction format is entered.
Step 6: The Group class then updates the best transaction solution and member balances based on the new transaction.
Step 7: The StorageHandler class saves the updated transaction list to the file for future reference.
```
Code Snippet
```
MemberList members = group.getMemberList();
TransactionList transactions = group.getTransactionList();
transactions.addTransaction(taskExpression, members);
group.updateTransactionSolution();
group.saveAllData();
```
<ins>Conclusion</ins>

The Transaction class provides functionality for managing transactions between
facilitates the lending of money and ensures data integrity by validating input and managing member interactions.

The TransactionList class provides essential functionalities for managing a list of transactions.
Its methods facilitate the addition, removal, editing, and retrieval of transactions, ensuring efficient management of transactions within a group.

### PIN

<ins> Overview </ins>

The PINHandler class is responsible for managing the creation, loading, authentication, and resetting of a
Personal Identification Number (PIN) used for authentication in the LongAh application. It uses SHA-256 hashing to
securely store and compare PINs. The PINHandler class interacts with the StorageHandler class to save and load the PIN
and authentication status.

<ins>Implementation Details </ins>

*Data Storage:*

The PIN and authentication enabled status are saved in a file located at ./data/pin.txt.
The file format is as follows:

`hashed PIN`<br />
`authenticationEnabled`<br />

<ins> Class Structure </ins>

The PINHandler class has the following static fields:

*logger*: A logger object for logging messages.

*PIN_FILE_PATH*: The path to the file where the PIN and authentication status are saved.

*savedPin*: The hashed PIN saved in the file.

*authenticationEnabled*: A boolean flag indicating whether authentication is enabled.

<ins> Constructor </ins>

The PINHandler constructor initializes the savedPin and authenticationEnabled fields by loading them from the file using
the loadPinAndAuthenticationEnabled method.

If the file does not exist or the savedPin is empty, it calls the createPin method to create a new PIN.

<ins> Methods </ins>

*loadPinAndAuthenticationEnabled*: Loads the saved PIN and authentication enabled status from the file.

*savePinAndAuthenticationEnabled*: Saves the PIN and authentication enabled status to the file.

*getPinFilePath*: Returns the file path of the PIN file.

*createPin*: Prompts the user to create a new 6-digit PIN and hashes it before saving.

*authenticate*: Authenticates the user by comparing the entered PIN with the saved PIN.

*resetPin*: Resets the PIN for the user by prompting for the current PIN and creating a new PIN if the current
PIN is correct.

*enablePin*: Enables authentication upon startup.

*disablePin*: Disables authentication upon startup.

*getSavedPin*: Returns the saved PIN.

*getAuthenticationStatus*: Returns the authentication status.

<ins> Usage Example </ins>

![pinhandler longah.png](diagrams%2Fpinhandler%20longah.png)


Given below is an example usage scenario and how the PIN creation and authentication mechanism behaves at each step:
```
Step 1. The user launches the application for the first time. The PINHandler initializes, loading the saved PIN and 
authentication enabled status from the file. If no PIN exists, it prompts the user to create a new PIN.

Step 2. The user creates a new 6-digit PIN using the createPin method. The entered PIN is hashed using SHA-256 before 
saving it to the file.

Step 3. The user closes the application and relaunches it. The PINHandler loads the saved PIN and authentication 
enabled status from the file again.

Step 4. The user attempts to log in by entering their PIN. The authenticate method hashes the entered PIN and 
compares it with the saved hashed PIN. If they match, the user is successfully authenticated.

Step 5. The user decides to reset their PIN by entering their current PIN and creating a new one using the resetPin 
method.

Step 6. The user disables authentication upon startup using the 'pin disable' command. The authenticationEnabled flag 
is set to false and saved to the file.

Step 7. The user relaunches the application, and authentication is no longer required since it has been disabled. 
The user can proceed with the application and do any actions without entering a PIN.
```
* Code Snippet
```
// Initialize PINHandler
PINHandler pinHandler = new PINHandler();

// Check if authentication is enabled
if (PINHandler.getAuthenticationStatus()) {
// Authenticate user
PINHandler.authenticate();
} else {
// Authentication is disabled, proceed with application logic
}
```

<ins> Design Considerations </ins>

Resetting PIN: The resetPin() method allows users to change their PIN by first verifying their current PIN. This adds
an extra layer of security to prevent unauthorized PIN changes.

Authentication Management: Users have the option to enable or disable authentication upon startup using the 'pin enable'
and 'pin disable' commands. This flexibility allows users to customize their authentication preferences based on their
security needs and convenience.


<ins> Conclusion </ins>

The PINHandler class provides a secure and convenient way to manage user authentication using a PIN.
Its design allows for easy integration into the LongAh application and can be extended to support additional
authentication features if required.

### Class Diagram

### Sequence Diagram

## Product scope
### Target user profile

Busy people with large transaction quantities among friends

### Value proposition

- Help users to find the least transactions solution to a large quantity of transactions
- Allow users to view past expenses of a group

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|user|to be able to find the least transactions needed to resolve amounts owed by various members of my various groups|-|
|v1.0|user|add transactions involving multiple people in a group|keep track of people involved and value of the transaction|
|v1.0|user|edit transactions|fix mistakes made when entering transactions|
|v1.0|user|delete transactions|clear erroneous transactions which I do not intend to keep|
|v1.0|user|keep a log of my data|retain memory of past transactions in past runs of the platform|
|v1.0|user|have easy access command to clear my pending debts|-|
|v1.0|user|be able to organise people into groups|minimise the occurence of being affected by typos|
|v1.0|user|add members to a group|add them to future transactions|
|v1.0|user|restart data for a group|reduce clutter of the application|
|v2.0|new user|view help commands|have an easy reference for commands while using the application|
|v2.0|user|enable the use of passwords for my application|prevent wrongful access to my records|
|v2.0|user|disable the password|have an easier time allowing people to view my records|
|v2.0|user|edit my password|change my password in case it has been compromised|
|v2.0|user|have my password be encrypted|ensure my password cannot be easily found out|
|v2.0|user|edit members in my group|change their nicknames which I store within the application|
|v2.0|user|delete current members|keep my groups neat and free of people who are no longer part of them|
|v2.0|user|create more groups|use the application for multiple groups of friends without data overlapping|
|v2.0|forgetful user|time of transactions to be saved|reference when each transaction were made|
|v2.0|user|search for specific transactions|find out information relating to the transaction in case I need to affect it|
|v2.1|advanced user|merge different groups together|combine groups which have large overlaps in members|
|v2.1|user|setup expenditure limits|be notified when someone have too large of a debt|
|v2.1|advanced user|create equal share transactions|add multiple people to a transaction without having to type their associated value to each of them|
|v2.1|advanced user|have command shortcuts|input commands faster|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* Lender - Member making payments on behalf of other members
* Borrower - Members being paid for by the lender
* Transaction - Payment made by ONE Lender on behalf of MULTIPLE Borrower
* Subtransaction - Subset of Transaction,

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
