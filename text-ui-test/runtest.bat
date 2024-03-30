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

java -jar %jarloc% < ..\..\text-ui-test\input1.txt > ..\..\text-ui-test\ACTUAL1.TXT

cd ..\..\text-ui-test

FC ACTUAL1.TXT EXPECTED1.TXT >NUL && ECHO Test passed! || Echo Test failed!
