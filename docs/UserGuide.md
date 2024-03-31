# LongAh! User Guide

## Introduction

LongAh! is a CLI-based application designed to help users track debts within friend groups and determine the 
least transaction method of settling these debts. It is optimized for busy people with large transaction quantities 
among friends.

## Quick Start

1. Ensure that you have Java 11 or above installed. 
2. Download the latest version of `LongAh!` from [here](https://github.com/AY2324S2-CS2113-T15-1/tp/releases)
3. Copy the JAR file to the folder you want to use as the home folder for your LongAh! application.
4. Open a command terminal, navigate to the folder containing the JAR file and run the command:
```dtd
java -jar tp.jar
```
5. Upon starting the application, you will be prompted to enter your PIN. The user PIN is required to access the application.
The app will prompt you to create your own PIN if it is your first time using the application.
6. You can now start using LongAh! by entering commands into the command terminal.

## Table of Contents
- [LongAh! User Guide](#longah-user-guide)
  - [Introduction](#introduction)
  - [Quick Start](#quick-start)
  - [Table of Contents](#table-of-contents)
  - [Quick Command Reference](#quick-command-reference)
  - [Features](#features)
    - [Member Management](#member-management)
    - [Group Balances \& Expense Tracking](#group-balances--expense-tracking)
    - [Debt Simplification](#debt-simplification)
    - [Security](#security)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
  - [Command Format](#command-format)
    - [Viewing help: `help`](#viewing-help-help)
    - [Adding a member: `add member`](#adding-a-member-add-member)
    - [Adding a transaction: `add transaction`](#adding-a-transaction-add-transaction)
    - [Listing all members: `list members`](#listing-all-members-list-members)
    - [Listing all transactions: `list transactions`](#listing-all-transactions-list-transactions)
    - [Listing all debts: `list debts`](#listing-all-debts-list-debts)
    - [Find Transactions: `find transactions`](#find-transactions-find-transactions)
    - [Find Lender `find lender`](#find-lender-find-lender)
    - [Find Borrower `find borrower`](#find-borrower-find-borrower)
    - [Find Debts `find debts`](#find-debts-find-debts)
    - [Deleting a member: `delete member`](#deleting-a-member-delete-member)
    - [Deleting a transaction: `delete transaction`](#deleting-a-transaction-delete-transaction)
    - [Editing a member: `edit member`](#editing-a-member-edit-member)
    - [Editing a transaction: `edit transaction`](#editing-a-transaction-edit-transaction)
    - [Enabling the user PIN: `pin enable`](#enabling-the-user-pin-pin-enable)
    - [Disabling the user PIN: `pin disable`](#disabling-the-user-pin-pin-disable)
    - [Resetting user PIN: `pin reset`](#resetting-user-pin-pin-reset)
    - [Clearing all transactions `clear`](#clearing-all-transactions-clear)
    - [Settle a user's debts: `settleup`](#settle-a-users-debts-settleup)
    - [Exiting the application: `exit`](#exiting-the-application-exit)
  - [FAQ](#faq)
  - [Known Issues](#known-issues)

## Quick Command Reference
| Task                   | Command Expression                                                                                    |
|------------------------|-------------------------------------------------------------------------------------------------------|
| Help menu              | `help`                                                                                                |
| Add member             | `add member [name]`                                                                                   |
| Add transaction        | `add transaction [lender] p/[borrower1] a/[amount] p/[borrower2] a/[amount] ...`                      |
| Add dated transaction  | `add transaction lender t/[DD-MM-YY HHMM] p/[borrower1] a/[amount] p/[borrower2] a/[amount] ...`      |
| List members           | `list members`                                                                                        |
| List transactions      | `list transactions`                                                                                   |
| List debts             | `list debts`                                                                                          |
| Find transactions      | `find transactions [member]`                                                                          |
| Find lender            | `find lender [member]`                                                                                |
| Find borrower          | `find borrower [member]`                                                                              |
| Find debts             | `find debts [member]`                                                                                 |
| Delete member          | `delete member [member]`                                                                              |
| Delete transaction     | `delete transaction [transaction_index]`                                                              |
| Edit member            | `edit member [old_name] [new_name]`                                                                   |
| Edit transaction       | `edit transaction [transaction_index] [lender] p/[borrower1] a/[amount] p/[borrower2] a/[amount] ...` |
| Enable PIN             | `pin enable`                                                                                          |
| Disable PIN            | `pin disable`                                                                                         |
| Reset PIN              | `pin reset`                                                                                           |
| Clear all transactions | `clear`                                                                                               |
| Settle up              | `settleup [member]`                                                                                   |
| Exit                   | `exit`                                                                                                |


## Features
LongAh! comes with many features for you to manage your group expenses.

### Member Management
You can add, delete, and edit members in LongAh! to keep track of who is involved in the transactions.

### Group Balances & Expense Tracking
You can add transactions between members to keep track of who owes who. LongAh! can also display group balances and expenses at a glance.


### Debt Simplification
LongAh! calculates the simplest way to settle all debts between members and shows a list of all debts, reducing the amount of transanctions
needed to settle all debts between members.

### Security
LongAh! allows you to set a PIN to protect your data from unauthorized access. The PIN is required to access the application. This feature
is enabled by default.

### Saving the data
LongAh! data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.
The file is also created automatically if it does not exist.

### Editing the data file
LongAh! data is saved as a TXT file in the hard disk. Advanced users are welcome to edit the data file directly, but please ensure that the data is in the correct format.
The PIN TXT file contains the pin hash of each user's PIN for security purposes. It is not recommended to edit this file directly.


## Command Format
A command has the general structure:
```dtd
[COMMAND] [SUBCOMMAND] [EXPRESSION]
```
There are 5 main group of commands: 'add', 'delete', 'edit', 'find', 'list', along with other commands.

### Viewing help: `help`
Shows a help message containing all the commands available in LongAh!.

Format: `help`

Example of usage: `help`


### Adding a member: `add member`
Adds a new member to the list of members in LongAh!

Format: `add member [NAME]`

* Name of new member should not be a duplicate of an existing member.

Example of usage:
`add member Alice`

### Adding a transaction: `add transaction`
Adds a new transaction to the list of transactions in LongAh!

Format: `add transaction [LENDER] p/[BORROWER1] a/[AMOUNT] p/[BORROWER2] a/[AMOUNT] ...`
* The transaction supports 1 or more borrower(s), each with custom borrowed amounts.
* `p/` is the prefix for the borrower's name, and should be followed by the name of the borrower.
* `a/` is the prefix for the amount borrowed, and should be followed by the amount borrowed by that borrower from the lender.
* The `LENDER` and `BORROWER(s)` should be an existing member.

Example of usage:
`add transaction Alice p/Bob a/10`
OR
`add transaction Alice p/Bob a/10 p/Charlie a/20`


### Listing all members: `list members`
Shows a list of all current members in LongAh! along with their current balances.
* Positive balance indicate that the member is owed money by other member(s) in the group.
* Negative balance indicate that the member owes money to other member(s).
* A balance of 0 indicates that the member neither owes nor is owed money.

Format: `list members`

Example of usage:
```dtd
add member alice
add member bob
add transaction alice p/bob a/5

list members
    alice: $5.0
    bob: -$5.0
```


### Listing all transactions: `list transactions`
Shows a list of all transactions in LongAh!.

* Each transaction is indexed for easy reference and use in other commands.

Format: `list transactions`

Example of usage:
```dtd
add member alice
add member bob
add transaction alice p/bob a/5

list transactions
    1.
    Lender: alice
    Borrower 1: bob Owed amount: 5.00
```



### Listing all debts: `list debts`
Calculates the simplest way to repay all debts between all members and shows a list of all debts in LongAh!.

Format: `list debts`

Example of usage:
```dtd
add member alice
add member bob
add member charlie
add transaction alice p/bob a/3 p/charlie a/4
add transaction charlie p/alice a/5 p/bob a/1

list debts
    Best Way to Solve Debts:
    bob owes alice $2.0
    bob owes charlie $2.0
```
### Find Transactions: `find transactions`
Finds all transactions that involves the specified member. The member can be involved as a lender or a borrower in the transaction(s).

Format: `find transactions [MEMBER]`
* The `MEMBER` should be an existing member.

Example of usage: `find transactions Alice`

### Find Lender `find lender`
Finds all transactions where the specified member is the lender.

Format: `find lender [MEMBER]`
* The `MEMBER` should be an existing member.

Example of usage: `find lender Alice`

### Find Borrower `find borrower`
Finds all transactions where the specified member is a borrower.

Format: `find borrower [MEMBER]`
* The `MEMBER` should be an existing member.

Example of usage: `find borrower Alice`

### Find Debts `find debts`
Finds all debts that the specified member has with other members.

Format: `find debts [MEMBER]`
* The `MEMBER` should be an existing member.

Example of usage: `find debts Alice`

### Deleting a member: `delete member`
Deletes a member from the list of members in LongAh!.

Format: `delete member [MEMBER]`
* The `MEMBER` should be an existing member.
* All transactions involving the member will be deleted.
* All debts involving the member will be recalculated.

Example of usage: `delete member Alice`


### Deleting a transaction: `delete transaction`
Deletes a transaction from the list of transactions in LongAh!.

Format: `delete transaction [TRANSACTION_INDEX]`
* The `TRANSACTION_INDEX` should be an existing transaction number.
* All debts involving the transaction will be recalculated.
* The transaction number can be found by using the `list transactions` command and taking the corresponding index of 
the transaction that you want to delete.

Example of usage: `delete transaction 3`


### Editing a member: `edit member`
Edits the name of a member in the list of members in LongAh!.

Format: `edit member [OLD_NAME] [NEW_NAME]`
* The `OLD_NAME` should be an existing member.
* The `NEW_NAME` should not be a duplicate of an existing member.
* All transactions involving the member will be updated.

Example of usage: `edit member Alice Bob`

### Editing a transaction: `edit transaction`
Edits the details of a transaction in the list of transactions in LongAh!.

Format: `edit transaction [TRANSACTION_INDEX] [LENDER] p/[BORROWER1] a/[AMOUNT] p/[BORROWER2] a/[AMOUNT] ...`
* The `TRANSACTION_INDEX` should be an existing transaction number.
* The `LENDER` and `BORROWER(s)` should be an existing member.
* Allows for edits to the lender and the borrowers involved in the transaction, as well as the amount.
* The transaction number can be found by using the `list transactions` command and taking the corresponding index.
* All debts involving the transaction will be recalculated.

Example of usage: `edit transaction 3 Charlie p/Bob a/3 p/Alice a/5`

### Enabling the user PIN: `pin enable`
Enables the user to set a PIN for the application. (enabled by default)

Format: `pin enable`

Example of usage: `pin enable`


### Disabling the user PIN: `pin disable`
Disables the user PIN for the application. 

Format: `pin disable`

Example of usage: `pin disable`


### Resetting user PIN: `pin reset`
Resets the user PIN for the application. Follow the instructions as prompted to reset the PIN.

Format: `pin reset`
* The new PIN should only contain numbers (0-9).

Example of usage: `pin reset`


### Clearing all transactions `clear`
Clear all previous transactions logged in LongAh!. Members balances will be reset to 0.

Format: `clear`

Example of usage: `clear`


### Settle a user's debts: `settleup`
Settles all debts of the specified member with all other members. A transaction will be created to settle the debts and reset
the debt balance of the specified member to 0, while updating the balance(s) of all relevant lender(s).

Format: `settleup [MEMBER]`
* The `MEMBER` should be an existing member.
* The `MEMBER` should be a valid debtor in the group (i.e. the member should owe money to other members).

Example of usage:
```dtd
add member alice
add member bob
add member charlie
add transaction alice p/bob a/3 p/charlie a/4
add transaction charlie p/alice a/6 p/bob a/1

list debts
    Best Way to Solve Debts:
    bob owes alice $1.0
    bob owes charlie $3.0

settleup bob
    bob has repaid alice $1.0
    bob has repaid charlie $3.0
    bob has no more debts!

list members
    alice: $0.0
    bob: $0.0
    charlie: $0.0
``` 

### Views the balances of all members in the form of a chart: `view chart`
Shows a chart of the balances of all members in the group.

Format: `view chart`

Example of usage: `view chart`
```dtd
add member alice
add member bob
add member charlie
add transaction alice p/bob a/100
add transaction charlie p/alice a/6 p/bob a/1

view chart
        Loading balances chart...
```

A separate window will pop up displaying the balances of all members in the group in the form of a category chart.

They are color-coded to show the balance status of each member.

A separate tooltip will show the exact balance of each member.

![viewChart.png](diagrams%2FviewChart.png)

### Exiting the application: `exit`
Exits the application.

Format: `exit`

Example of usage: `exit`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Install LongAh! on the other computer and replace the empty members, pin, and transaction TXT files it creates with the files containing your data.


## Known Issues

