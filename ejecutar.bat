@echo off
chcp 65001 >nul
if not exist out\Main.class (
    call compilar.bat
    if %ERRORLEVEL% NEQ 0 exit /b 1
)
java -cp out Main
