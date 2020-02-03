REM Below command is to set the activedirectory path to the main project folder
cd C:\workspace1\com.apache.RAIS

REM Below command is to run - Validation xml file
mvn clean test -Dsurefire.suiteXmlFiles=ValidationTest.xml
