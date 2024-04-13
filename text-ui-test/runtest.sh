#!/usr/bin/env bash

# Change to script directory
cd "${0%/*}"

# Change directory to project root
cd ..

# Build project
./gradlew clean shadowJar

# Change directory to text-ui-test
cd text-ui-test

# Function to check test result
check_test() {
    local expected_output="$1"
    local actual_output="$2"
    local test_name="$3"
    
    cp "$expected_output" "$expected_output-UNIX.TXT"
    dos2unix "$expected_output-UNIX.TXT" "$actual_output"
    diff "$expected_output-UNIX.TXT" "$actual_output"
    
    return $?
}

# Initialize error count variable
ERROR_COUNT=0

# Initialize variable to store names of failed tests
FAILED_TESTS=""

# Run tests
check_test "expected_output/EXPECTED1.TXT" "actual_output/ACTUAL1.TXT" "1"
if [ $? -ne 0 ]; then
    ERROR_COUNT=$((ERROR_COUNT + 1))
    FAILED_TESTS+=" 1"
fi

check_test "expected_output/EXPECTED2.TXT" "actual_output/ACTUAL2.TXT" "2"
if [ $? -ne 0 ]; then
    ERROR_COUNT=$((ERROR_COUNT + 1))
    FAILED_TESTS+=" 2"
fi

check_test "expected_output/EXPECTED3.TXT" "actual_output/ACTUAL3.TXT" "3"
if [ $? -ne 0 ]; then
    ERROR_COUNT=$((ERROR_COUNT + 1))
    FAILED_TESTS+=" 3"
fi

check_test "expected_output/EXPECTED4.TXT" "actual_output/ACTUAL4.TXT" "4"
if [ $? -ne 0 ]; then
    ERROR_COUNT=$((ERROR_COUNT + 1))
    FAILED_TESTS+=" 4"
fi

check_test "expected_output/EXPECTED5A.TXT" "actual_output/ACTUAL5A.TXT" "5A"
if [ $? -ne 0 ]; then
    ERROR_COUNT=$((ERROR_COUNT + 1))
    FAILED_TESTS+=" 5A"
fi

check_test "expected_output/EXPECTED5B.TXT" "actual_output/ACTUAL5B.TXT" "5B"
if [ $? -ne 0 ]; then
    ERROR_COUNT=$((ERROR_COUNT + 1))
    FAILED_TESTS+=" 5B"
fi

# Output test results
if [ $ERROR_COUNT -eq 0 ]; then
    echo "All tests passed!"
else
    echo "$ERROR_COUNT tests failed: $FAILED_TESTS"
fi

# Exit with error count
exit $ERROR_COUNT