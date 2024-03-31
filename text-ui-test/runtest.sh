#!/usr/bin/env bash

# change to script directory
cd "${0%/*}"

cd ..
./gradlew clean shadowJar

cd text-ui-test
rm -rf ./data/

java  -jar $(find ../build/libs/ -mindepth 1 -print -quit) < input1.txt > ACTUAL1.TXT

cp EXPECTED1.TXT EXPECTED1-UNIX.TXT
dos2unix EXPECTED1-UNIX.TXT ACTUAL1.TXT
diff EXPECTED1-UNIX.TXT ACTUAL1.TXT
if [ $? -eq 0 ]
then
    echo "Test passed!"
    exit 0
else
    echo "Test failed!"
    exit 1
fi
