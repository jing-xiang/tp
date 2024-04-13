@echo off
setlocal enableextensions
pushd %~dp0

cd ..
call gradlew clean shadowJar

if exist data\ rmdir /s /q data\

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b *.jar'
) do (
    set jarloc=%%a
)

java -jar %jarloc% < ..\..\text-ui-test\input\input5B.txt > ..\..\text-ui-test\actual_output\ACTUAL5B.TXT
java -jar %jarloc% < ..\..\text-ui-test\input\input1.txt > ..\..\text-ui-test\actual_output\ACTUAL1.TXT
java -jar %jarloc% < ..\..\text-ui-test\input\input2.txt > ..\..\text-ui-test\actual_output\ACTUAL2.TXT
java -jar %jarloc% < ..\..\text-ui-test\input\input3.txt > ..\..\text-ui-test\actual_output\ACTUAL3.TXT
java -jar %jarloc% < ..\..\text-ui-test\input\input4.txt > ..\..\text-ui-test\actual_output\ACTUAL4.TXT
java -jar %jarloc% < ..\..\text-ui-test\input\input5A.txt > ..\..\text-ui-test\actual_output\ACTUAL5A.TXT

cd ..\..\text-ui-test

FC actual_output\ACTUAL5B.TXT expected_output\EXPECTED5B.TXT >NUL && ECHO Test passed! || Echo Test failed!
FC actual_output\ACTUAL1.TXT expected_output\EXPECTED1.TXT >NUL && ECHO Test passed! || Echo Test failed!
FC actual_output\ACTUAL2.TXT expected_output\EXPECTED2.TXT >NUL && ECHO Test passed! || Echo Test failed!
FC actual_output\ACTUAL3.TXT expected_output\EXPECTED3.TXT >NUL && ECHO Test passed! || Echo Test failed!
FC actual_output\ACTUAL4.TXT expected_output\EXPECTED4.TXT >NUL && ECHO Test passed! || Echo Test failed!
FC actual_output\ACTUAL5A.TXT expected_output\EXPECTED5A.TXT >NUL && ECHO Test passed! || Echo Test failed!
