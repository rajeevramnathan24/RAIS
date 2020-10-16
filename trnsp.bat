REM Below command is to set the activedirectory path to the main project folder
cd C:\workspace1\com.apache.RAIS


REM Below command is to run - CRUD operations xml file

mvn clean test -Dsurefire.suiteXmlFiles=transport_Authorization.xml

