@echo off
setlocal enableextensions
pushd %~dp0

cd ..
call gradlew clean test

if %errorlevel% equ 0 (
    echo All tests passed!
    exit /b 0
) else (
    echo Some tests failed!
    exit /b 1
)
