set mysqlBin="%CD%/mysql-8.0.33-winx64/bin/mysql.exe"

%mysqlBin% -u root --skip-password < createRoot.sql

pause