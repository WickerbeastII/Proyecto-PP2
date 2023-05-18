REM ""

set mysqldBin="%CD%/mysql-8.0.33-winx64/bin/mysqld.exe"

mkdir sqlDB

%mysqldBin% --initialize-insecure --user=mysql --console --basedir=sqlDB

pause