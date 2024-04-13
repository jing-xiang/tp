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

set ERROR_COUNT=0
set FAILED_TESTS=

java -jar %jarloc% < ..\..\text-ui-test\input\input5B.txt > ..\..\text-ui-test\actual_output\ACTUAL5B.TXT
java -jar %jarloc% < ..\..\text-ui-test\input\input1.txt > ..\..\text-ui-test\actual_output\ACTUAL1.TXT
java -jar %jarloc% < ..\..\text-ui-test\input\input2.txt > ..\..\text-ui-test\actual_output\ACTUAL2.TXT
java -jar %jarloc% < ..\..\text-ui-test\input\input3.txt > ..\..\text-ui-test\actual_output\ACTUAL3.TXT
java -jar %jarloc% < ..\..\text-ui-test\input\input4.txt > ..\..\text-ui-test\actual_output\ACTUAL4.TXT
java -jar %jarloc% < ..\..\text-ui-test\input\input5A.txt > ..\..\text-ui-test\actual_output\ACTUAL5A.TXT

cd ..\..\text-ui-test

FC actual_output\ACTUAL5B.TXT expected_output\EXPECTED5B.TXT >NUL && set /a ERROR_COUNT=ERROR_COUNT || (set /a ERROR_COUNT+=1 && set FAILED_TESTS=%FAILED_TESTS% 5B)
FC actual_output\ACTUAL1.TXT expected_output\EXPECTED1.TXT >NUL && set /a ERROR_COUNT=ERROR_COUNT || (set /a ERROR_COUNT+=1 && set FAILED_TESTS=%FAILED_TESTS% 1)
FC actual_output\ACTUAL2.TXT expected_output\EXPECTED2.TXT >NUL && set /a ERROR_COUNT=ERROR_COUNT || (set /a ERROR_COUNT+=1 && set FAILED_TESTS=%FAILED_TESTS% 2)
FC actual_output\ACTUAL3.TXT expected_output\EXPECTED3.TXT >NUL && set /a ERROR_COUNT=ERROR_COUNT || (set /a ERROR_COUNT+=1 && set FAILED_TESTS=%FAILED_TESTS% 3)
FC actual_output\ACTUAL4.TXT expected_output\EXPECTED4.TXT >NUL && set /a ERROR_COUNT=ERROR_COUNT || (set /a ERROR_COUNT+=1 && set FAILED_TESTS=%FAILED_TESTS% 4)
FC actual_output\ACTUAL5A.TXT expected_output\EXPECTED5A.TXT >NUL && set /a ERROR_COUNT=ERROR_COUNT || (set /a ERROR_COUNT+=1 && set FAILED_TESTS=%FAILED_TESTS% 5A)

if %ERROR_COUNT% EQU 0 (
    Echo Test passed!
) else (
    Echo %ERROR_COUNT% tests failed:%FAILED_TESTS%
)
