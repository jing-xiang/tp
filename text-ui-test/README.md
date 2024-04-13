# Text UI Testing

## Overview

We perform comprehensive testing through text ui testing to ensure that output from all commands are as expected. Multiple files are input to simulate multiple uses of the application. Purpose of each application session can be found below. A series of tests will be run, of which all tests will need to be successful for the Text UI Test to be completed successfully.

Chart command is not tested here due to its use of a GUI.

## How To Use

When running tests on a Windows system, run the following command from the specificied directory:
```
./runtest.bat
```

When running tests on a UNIX-based system, run the following command from the specified directory:
```
./runtest.sh
```

Warning: Text UI Testing has been configured to clear all past data records to simulate a fresh application starting when the above commands are invoked. This WILL result in loss of data from previous runs.

## Test Files

### Group 1 Files

Consists of `input1.txt` and `EXPECTED1.TXT`.

Testing Purpose: Successful execution of `help`, `list`, `add`, `find` and `edit` commands (cumulatively member-level commands).

### Group 2 Files

Consists of `input2.txt` and `EXPECTED2.TXT`.

Testing Purpose: Unsuccessful execution of `help`, `list`, `add`, `find` and `edit` commands (cumulatively member-level commands). Does not include incorrect commands. Adds group and enables PIN for subsequent tests.

### Group 3 Files

Consists of `input3.txt` and `EXPECTED3.txt`.

Testing Purpose: Successful execution of `filter`, `delete`, `clear`, `settle`, `group` and `pin` commands (cumulatively group-level and account-level commands).

### Group 4 Files

Consists of `input4.txt` and `EXPECTED4.txt`.

Testing Purpose: Unsuccessful execution of `filter`, `delete`, `clear`, `settle`, `group` and `pin` commands (cumulatively group-level and account-level commands). Includes incorrect commands.
