#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew clean shadowJar

cd text-ui-test
rm -rf ./data/

java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input/input1.txt > actual_output/ACTUAL1.TXT
java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input/input2.txt > actual_output/ACTUAL2.TXT
java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input/input3.txt > actual_output/ACTUAL3.TXT
java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input/input4.txt > actual_output/ACTUAL4.TXT

ERROR_COUNT=0

cp expected_output/EXPECTED1.TXT expected_output/EXPECTED1-UNIX.TXT
dos2unix expected_output/EXPECTED1-UNIX.TXT actual_output/ACTUAL1.TXT
diff expected_output/EXPECTED1-UNIX.TXT actual_output/ACTUAL1.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
else
    ERROR_COUNT=$((ERROR_COUNT+1))
    echo "Test failed!"
fi

cp expected_output/EXPECTED2.TXT expected_output/EXPECTED2-UNIX.TXT
dos2unix expected_output/EXPECTED2-UNIX.TXT actual_output/ACTUAL2.TXT
diff expected_output/EXPECTED2-UNIX.TXT actual_output/ACTUAL2.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
else
    ERROR_COUNT=$((ERROR_COUNT+1))
    echo "Test failed!"
fi

cp expected_output/EXPECTED3.TXT expected_output/EXPECTED3-UNIX.TXT
dos2unix expected_output/EXPECTED3-UNIX.TXT actual_output/ACTUAL3.TXT
diff expected_output/EXPECTED3-UNIX.TXT actual_output/ACTUAL3.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
else
    ERROR_COUNT=$((ERROR_COUNT+1))
    echo "Test failed!"
fi

cp expected_output/EXPECTED4.TXT expected_output/EXPECTED4-UNIX.TXT
dos2unix expected_output/EXPECTED4-UNIX.TXT actual_output/ACTUAL4.TXT
diff expected_output/EXPECTED4-UNIX.TXT actual_output/ACTUAL4.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
else
    ERROR_COUNT=$((ERROR_COUNT+1))
    echo "Test failed!"
fi

exit $ERROR_COUNT