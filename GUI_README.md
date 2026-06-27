# 🔋 Sistema de Estaciones de Carga Eléctrica - GUI Interactiva

## 📋 Descripción

Interfaz gráfica interactiva para el sistema de gestión de estaciones de carga eléctrica, desarrollada en Java Swing con soporte para dos tipos de usuarios:

- **Usuario Regular**: Acceso limitado a sus datos personales
- **Administrador**: Control total del sistema

## 🎯 Características

### Login
- Autenticación de usuario y administrador
- Selección de tipo de usuario en la interfaz
- Validación de credenciales
- Interfaz moderna con gradiente azul

### Panel de Usuario Regular

#### 1. **Estaciones** 🏪
   - Lista completa de estaciones de carga disponibles
   - Información: ID, Nombre, Ubicación, Cargadores disponibles, Estado
   - Vista actualizada en tiempo real

#### 2. **Mi Vehículos** 🚗
   - Lista de vehículos propios registrados
   - Información: Placa, Marca, Modelo, Capacidad de Batería
   - Filtrado automático por propietario

#### 3. **Mis Reservas** 📅
   - Historial de reservas personales
   - Información: ID, Vehículo, Fecha/Hora, Estado
   - Información detallada de cada reserva

#### 4. **Mis Cargas** ⚡
   - Historial de cargas realizadas
   - Información: ID, Vehículo, Estación, Energía consumida, Costo total
   - Estadísticas personales de consumo y gasto

### Panel de Administrador

#### 1. **Estaciones** 🏪
   - Gestión completa de estaciones
   - Opciones: Agregar, Editar, Eliminar
   - Vista de todos los cargadores disponibles

#### 2. **Usuarios** 👥
   - Lista de todos los usuarios registrados
   - Información: Identificación, Nombre, Email, Teléfono, Estado
   - Opción para desactivar usuarios

#### 3. **Vehículos** 🚗
   - Vista completa de vehículos del sistema
   - Información: Placa, Propietario, Marca, Modelo, Capacidad Batería
   - Total de vehículos registrados

#### 4. **Cargas** ⚡
   - Monitoreo de todas las cargas del sistema
   - Información: ID, Vehículo, Estación, Propietario, Energía, Costo, Fecha
   - Estadísticas: Energía total consumida, Ingresos totales

#### 5. **Reservas** 📅
   - Gestión de reservas del sistema
   - Información: ID, Usuario, Vehículo, Fecha/Hora, Estado
   - Opciones: Confirmar, Cancelar reservas

#### 6. **Reportes** 💰
   - Panel de estadísticas completas del sistema
   - Resumen general:
     * Total de estaciones y usuarios
     * Total de vehículos registrados
   - Estadísticas de cargas:
     * Total de cargas realizadas
     * Energía total consumida (kWh)
     * Ingresos totales (₡)
   - Resumen de reservas
   - Opción para exportar reportes

## 🔐 Credenciales de Prueba

### Administrador
- **Usuario**: admin
- **Contraseña**: admin123

### Usuario Regular
- **Usuario**: (Usar identificación de usuario registrado)
- **Contraseña**: user123

Ejemplo:
- **Usuario**: 12345678
- **Contraseña**: user123

## 🚀 Cómo Ejecutar

### Windows
```bash
cd ElectricChargingStations-CR
run.bat
```

### Linux/Mac
```bash
cd ElectricChargingStations-CR
chmod +x run.sh
./run.sh
```

O compilar manualmente:
```bash
cd src/main/java
javac -d ../../../target/classes -cp "." main/ElectricChargingStationsCR.java com/user/gui/*.java
java -cp ../../../target/classes main.ElectricChargingStationsCR
```

## 📁 Estructura de Archivos

```
com/user/gui/
├── LoginFrame.java       # Pantalla de login
├── UsuarioPanel.java     # Panel para usuario regular
├── AdminPanel.java       # Panel para administrador
└── MainWindow.java       # Ventana principal y controlador
```

## 🎨 Interfaz Visual

- **Colores principales**: Azul (#2196F3) y Rojo (#F44336)
- **Tipografía**: Arial
- **Componentes**: Tables, Buttons, Panels, Tabs
- **Diseño**: Responsive y adaptable a diferentes tamaños de ventana

## 🔄 Flujo de Navegación

```
Inicio
  ↓
LoginFrame (Seleccionar tipo de usuario)
  ├─→ Administrador → AdminPanel (6 pestañas)
  │                       ├─ Estaciones
  │                       ├─ Usuarios
  │                       ├─ Vehículos
  │                       ├─ Cargas
  │                       ├─ Reservas
  │                       └─ Reportes
  │
  └─→ Usuario Regular → UsuarioPanel (4 pestañas)
                              ├─ Estaciones
                              ├─ Mi Vehículos
                              ├─ Mis Reservas
                              └─ Mis Cargas

En cualquier momento → Cerrar Sesión → Volver a LoginFrame
```

## 📊 Datos Cargados

La aplicación carga automáticamente:
- ✅ Lista de usuarios registrados
- ✅ Lista de estaciones de carga
- ✅ Lista de vehículos del sistema
- ✅ Historial de cargas
- ✅ Reservas realizadas

## 🔧 Requisitos

- Java 8 o superior
- Swing (incluido en JDK)
- Gson 2.10.1 (para manejo de JSON)

## 📝 Notas

- Los datos se cargan desde las listas internas (memoria)
- Las modificaciones se reflajan en la interfaz en tiempo real
- La autenticación es básica para demostración
- El sistema soporta múltiples usuarios simultáneamente

## 👨‍💻 Desarrollador

Maycol Pérez
Proyecto: Sistema de Estaciones de Carga Eléctrica - Costa Rica

---

**Última actualización**: Junio 2026
**Versión GUI**: 1.0
