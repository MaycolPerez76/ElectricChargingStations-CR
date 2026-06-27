@echo off
REM Script para ejecutar la aplicacion GUI
cd /d "%~dp0"

REM Compilar si es necesario
echo Compilando proyecto...
javac -d target\classes -cp "." src\main\java\main\*.java src\main\java\com\user\gui\*.java src\main\java\com\user\model\*.java src\main\java\com\user\listas\*.java src\main\java\com\user\estructures\*.java src\main\java\com\user\algoritmos\*.java

REM Ejecutar
echo.
echo Iniciando aplicacion...
java -cp target\classes main.ElectricChargingStationsCR
