@ECHO OFF
For /F "tokens=1* delims==" %%A IN (sca.properties) DO (
	IF "%%A"=="path" set myPath=%%B	
	)
cd analyzer
call mvnw clean package
call mvnw exec:java -Dexec.args=%myPath%
cd ..
start "" http://localhost:8080/analyzer/web/
php -S localhost:8080
PAUSE