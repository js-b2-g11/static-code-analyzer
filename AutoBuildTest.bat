@ECHO OFF
For /F "tokens=1* delims==" %%A IN (sca.properties) DO (
	IF "%%A"=="path" set myPath=%%B	
	)
cd analyzer
call mvnw clean package
call mvn exec:java -Dexec.mainClass=com.philips.bootcamp.analyzer.Main -Dexec.args=%myPath%
cd ..
php -S localhost:8080
start "" http://localhost:8080/analyzer/web/
PAUSE