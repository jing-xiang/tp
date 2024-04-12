# Text UI Testing

## Overview

We perform comprehensive testing through text ui testing to ensure that output from all commands are as expected. Multiple files are input to simulate multiple uses of the application. Purpose of each application session can be found below.

## Test Files

### Group 1 Files

Consists of `input1.txt` and `EXPECTED1.TXT`.

Testing Purpose: Successful execution of `help`, `list`, `add`, `find` and `edit` commands (cumulatively member-level commands).

### Group 2 Files

Consists of `input2.txt` and `EXPECTED2.TXT`.

Testing Purpose: Unsuccessful execution of `help`, `list`, `add`, `find` and `edit` commands (cumulatively member-level commands). Does not include incorrect commands. Adds group and enables PIN for subsequent tests.