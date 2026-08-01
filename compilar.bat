@echo off
chcp 65001 >nul
if not exist out mkdir out
javac -encoding UTF-8 -d out src\modelo\*.java src\validacion\*.java src\interfaz\*.java src\Main.java
if %ERRORLEVEL% NEQ 0 (
    echo Error de compilacion.
    exit /b 1
)
echo Compilacion exitosa.
