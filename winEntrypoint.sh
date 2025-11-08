#!/bin/sh

# 1. Use 'rm -rf' directly instead of 'sudo rm -r'. 
# 'sudo' is not needed/available in Git Bash for local file operations.
set -e
if [ -d "project" ]; then
    # Use standard 'rm -rf' (recursive, force)
    rm -rf project/src/main/java
else
    echo "Directory 'project' does not exist yet."
fi

# Standard mkdir -p works fine in Git Bash
mkdir -p project/src/main/java
mkdir -p project/src/test/java

# Variable handling works fine in Git Bash
CODE_EDITOR_RUN_ONLY="${CODE_EDITOR_RUN_ONLY:-$EXAM_RUN_ONLY}"
CODE_EDITOR_MODE="${CODE_EDITOR_MODE:-$EXAM_MODE}"

# Use ';' as a command separator to keep the logic compact
if [ -z "$EDITOR_FILES" ]; then
    # Globbing works fine in Git Bash
    cp -rf "./student/${EXERCISE}/"*.java ./project/src/main/java
else
    # The 'tr' command is fully supported in Git Bash
    cd ./student/
    cp -rf $(echo "$EDITOR_FILES" | tr ',' ' ') ../project/src/main/java
    cd -
fi

# Standard file copying
cp -rf "./tests/StopAfterFailureExtension.java" ./project/src/main/java
cp -rf "./tests/${EXERCISE}_test"/*.java ./project/src/main/java
cp ./pom.xml ./project

cd project

# find and redirection work fine in Git Bash
find . -name "*.java" > sources.txt

# Maven execution (requires mvn to be in the PATH)
mvn compile -Dmaven.repo.local=./tests_utility \
            -Dmaven.compiler.include=@sources.txt exec:java \
            -Dexec.args="--details=tree --disable-banner --select-class ${EXERCISE}Test"