@echo off

set envBatPath=C:\Git\lumira_base\lumira
set workDirectory=C:\Git\lumira_base\lumira\products\Server
set logsPath=C:\Users\i842311\Documents\Maven\DevTasks\531\logs

::load Lumira Server Maven build environment variables
cd %envBatPath%
call ws_env2
::this points to the project directory that you want to build
cd %workDirectory%

:START
rem CLS
echo.
echo.

echo Start
set count=5

mkdir %logsPath%

:loop
echo.
echo Start job %count%

echo Building...
call mvn install 1> %logsPath%\log_%count%.txt 2>&1

findstr "FAILURE" %logsPath%\log_%count%.txt
if %errorlevel% ==0 (
	echo There is a build failure!
	echo Error detected in log_%count%.txt
	goto end
) else (
	echo Successful build...
	set /a count -= 1
	rem del %logsPath%\log_%count%.txt
)

if %count% GTR 0 goto loop

echo End of one round

:end
pause
