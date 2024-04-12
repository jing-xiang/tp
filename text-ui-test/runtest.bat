@echo off
setlocal enableextensions
pushd %~dp0

cd ..
call gradlew clean shadowJar

rmdir /s /q data\

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b *.jar'
) do (
    set jarloc=%%a
)

java -jar %jarloc% < ..\..\text-ui-test\input\input1.txt > ..\..\text-ui-test\actual_output\ACTUAL1.TXT

cd ..\..\text-ui-test

FC actual_output\ACTUAL1.TXT expected_output\EXPECTED1.TXT >NUL && ECHO Test passed! || Echo Test failed!
