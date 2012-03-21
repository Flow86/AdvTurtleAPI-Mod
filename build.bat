@ECHO OFF

set MODDIR=%~dp0
set MCPDIR=%MODDIR%\..

CALL :COPYCLIENT
CALL :COMPILE

pause

GOTO :EOF

REM ---------------------------------------------------------------------------

:COMPILE
	set OLDCD=%CD%
	cd %MCPDIR%
	cmd /C recompile.bat
	cd %OLDCD%
	set OLDCD=
GOTO :EOF

REM ---------------------------------------------------------------------------

:COPYCLIENT
	xcopy /Y /E %MODDIR%\src\*  %MCPDIR%\src\minecraft\net\minecraft\src
GOTO :EOF
