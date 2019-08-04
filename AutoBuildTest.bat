@ECHO OFF
For /F "tokens=1* delims==" %%A IN (sca.properties) DO (
	IF "%%A"=="path" set myPath=%%B	
	)
cd analyzer
call mvnw clean compile assembly:single
call mvnw test
java -jar target/analyzer-0.0.1-SNAPSHOT-jar-with-dependencies.jar %myPath%
cd ..
php -S localhost:8080
PAUSE