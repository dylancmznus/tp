#!/usr/bin/env bash

# Change to script directory
cd "${0%/*}"
cd ..

# Run Gradle tests
./gradlew clean test

# Check test results
if [ $? -eq 0 ]; then
    echo "All tests passed!"
    exit 0
else
    echo "Some tests failed!"
    exit 1
fi
