@echo OFF
echo ***Clean the Project***
cd.
Call mvn clean -Dmaven.clean.failOnError=false
cd.
echo ***Test Started***
Call mvn integration-test -Dwebdriver.driver=firefox -Dmetafilter="+websiteTesting" > FirefoxExecutionLogs.txt | type FirefoxExecutionLogs.txt
CALL mvn serenity:aggregate
echo ***Opening Results***
cd.
cd target\site\serenity
index.html