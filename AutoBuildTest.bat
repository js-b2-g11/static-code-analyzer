@ECHO OFF
cd helloworld
call mvn compile
call mvn exec:java -Dexec.mainClass="com.philips.bootcamp.helloworld.App"
call mvn test
PAUSE