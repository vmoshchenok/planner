# Author
Anna Moshchenok

# Preconditions
Need to have JAVA installed in you system non lower that version 8 (if you use macOS run $ brew install --cask java8)
Need to have Maven installed (for MacOS run $ brew install maven)
Need to be [IntelliJ IDEA](https://www.jetbrains.com/idea/) installed

# Run tests
run the command $mvn surefire-report:report in the root of the project
or run tests in the IDEA, they are here /planner/src/test/java/toDoTests

#Test Results
Open the surefire report in any browser: target/site/surefire-report.html
OR
Open emailable report target/surefire-reports/emailable-report.html

Screenshots of failed tests will be here build/reports/tests






