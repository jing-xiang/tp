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
    local error_count_ref="$4"
    local failed_tests_ref="$5"
    
    cp "$expected_output" "$expected_output-UNIX.TXT"
    dos2unix "$expected_output-UNIX.TXT" "$actual_output"
    diff "$expected_output-UNIX.TXT" "$actual_output" > /dev/null
    
    if [ $? -eq 0 ]; then
        echo "Test $test_name passed!"
    else
        echo "Test $test_name failed!"
        eval "$error_count_ref+=1"
        eval "$failed_tests_ref+=\" $test_name\""
    fi
}

# Initialize error count variable
ERROR_COUNT=0

# Initialize variable to store names of failed tests
FAILED_TESTS=""

# Run tests
check_test "expected_output/EXPECTED1.TXT" "actual_output/ACTUAL1.TXT" "1" ERROR_COUNT FAILED_TESTS
check_test "expected_output/EXPECTED2.TXT" "actual_output/ACTUAL2.TXT" "2" ERROR_COUNT FAILED_TESTS
check_test "expected_output/EXPECTED3.TXT" "actual_output/ACTUAL3.TXT" "3" ERROR_COUNT FAILED_TESTS
check_test "expected_output/EXPECTED4.TXT" "actual_output/ACTUAL4.TXT" "4" ERROR_COUNT FAILED_TESTS
check_test "expected_output/EXPECTED5A.TXT" "actual_output/ACTUAL5A.TXT" "5A" ERROR_COUNT FAILED_TESTS
check_test "expected_output/EXPECTED5B.TXT" "actual_output/ACTUAL5B.TXT" "5B" ERROR_COUNT FAILED_TESTS

# Output test results
if [ $ERROR_COUNT -eq 0 ]; then
    echo "All tests passed!"
else
    echo "$ERROR_COUNT tests failed: $FAILED_TESTS"
fi

# Exit with error count
exit $ERROR_COUNT