#!/bin/bash
# Script para ejecutar la aplicacion GUI en Linux/Mac

cd "$(dirname "$0")"

echo "Compilando proyecto..."
javac -d target/classes -cp "." src/main/java/main/*.java src/main/java/com/user/gui/*.java src/main/java/com/user/model/*.java src/main/java/com/user/listas/*.java src/main/java/com/user/estructures/*.java src/main/java/com/user/algoritmos/*.java

echo ""
echo "Iniciando aplicacion..."
java -cp target/classes main.ElectricChargingStationsCR
