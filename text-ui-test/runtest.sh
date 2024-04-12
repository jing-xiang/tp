#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew clean shadowJar

cd text-ui-test
rm -rf ./data/

java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input/input1.txt > actual_output/ACTUAL1.TXT

cp expected_output/EXPECTED1.TXT expected_output/EXPECTED1-UNIX.TXT
dos2unix expected_output/EXPECTED1-UNIX.TXT actual_output/ACTUAL1.TXT
diff expected_output/EXPECTED1-UNIX.TXT actual_output/ACTUAL1.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi

cp expected_output/EXPECTED2.TXT expected_output/EXPECTED2-UNIX.TXT
dos2unix expected_output/EXPECTED2-UNIX.TXT actual_output/ACTUAL2.TXT
diff expected_output/EXPECTED2-UNIX.TXT actual_output/ACTUAL2.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi

cp expected_output/EXPECTED3.TXT expected_output/EXPECTED3-UNIX.TXT
dos2unix expected_output/EXPECTED3-UNIX.TXT actual_output/ACTUAL3.TXT
diff expected_output/EXPECTED3-UNIX.TXT actual_output/ACTUAL3.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi

cp expected_output/EXPECTED4.TXT expected_output/EXPECTED4-UNIX.TXT
dos2unix expected_output/EXPECTED4-UNIX.TXT actual_output/ACTUAL4.TXT
diff expected_output/EXPECTED4-UNIX.TXT actual_output/ACTUAL4.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi
