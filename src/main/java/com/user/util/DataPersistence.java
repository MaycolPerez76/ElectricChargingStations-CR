/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user.util;

/**
 *
 * @author mayco
 */
import com.user.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Clase para manejar la persistencia de datos en archivos JSON
 * Guarda y carga: Citas, Pacientes, Odontólogos, Horarios y Facturas
 */
public class DataPersistence {
    
    private static final String DATA_DIRECTORY = "data";
    private static final String PERSONAS_FILE = DATA_DIRECTORY + "/personas.json";
    private static final String VEHICULOS_FILE = DATA_DIRECTORY + "/vehiculos.json";
    private static final String ESTACIONES_FILE = DATA_DIRECTORY + "/estaciones.json";
    private static final String COUNTERS_FILE = DATA_DIRECTORY + "/counters.json";
    
    private static Gson gson = DataSerializer.getGson();
    
    /**
     * Inicializa el directorio de datos si no existe
     */
    public static void initializeDataDirectory() {
        File directory = new File(DATA_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("Directorio de datos creado: " + DATA_DIRECTORY);
        }
    }
    
    // ========== GUARDAR DATOS ==========
    
    /**
     * Guarda todas las citas en el archivo JSON
     * @param personas
     * @return 
     */
    public static boolean guardarPersonas(Map<Integer, Persona> personas) {
        return guardarDatos(PERSONAS_FILE, personas);
    }
    
    /**
     * Guarda todos los pacientes en el archivo JSON
     * @param vehiculos
     * @return 
     */
    public static boolean guardarVehiculos(Map<Integer, Vehiculo> vehiculos) {
        return guardarDatos(VEHICULOS_FILE, vehiculos);
    }
    
    /**
     * Guarda todos los odontólogos en el archivo JSON
     * @param estaciones
     * @return 
     */
    public static boolean guardarEstaciones(Map<Integer, Estacion> estaciones) {
        return guardarDatos(ESTACIONES_FILE, estaciones);
    }
    
    /**
     * Guarda los contadores de IDs
     * @param nextPersonaId
     * @param nextVehiculoId
     * @param nextEstacionId
     * @return 
     */
    public static boolean guardarContadores(int nextPersonaId, int nextVehiculoId, int nextEstacionId) {
    Map<String, Integer> counters = new HashMap<>();
    counters.put("nextPersonaId", nextPersonaId);
    counters.put("nextVehiculoId", nextVehiculoId);
    counters.put("nextEstacionId", nextEstacionId);
    return guardarDatos(COUNTERS_FILE, counters);
}


    
    /**
     * Guarda todos los datos de la base de datos
     * @param db
     * @return 
     */
    public static boolean guardarTodo(DataBase db) {
    initializeDataDirectory();
    
    boolean exito = true;
    exito &= guardarPersonas(db.getPersonas());
    exito &= guardarVehiculos(db.getVehiculos());
    exito &= guardarEstaciones(db.getEstaciones());
    exito &= guardarContadores(
        db.getNextPersonaId(), 
        db.getNextVehiculoId(), 
        db.getNextEstacionId()
    );
    
    if (exito) {
        System.out.println("✓ Todos los datos guardados exitosamente");
    } else {
        System.err.println("✗ Error al guardar algunos datos");
    }
    
    return exito;
}

    
    // ========== CARGAR DATOS ==========
    
    /**
     * Carga las citas desde el archivo JSON
     * @return 
     */
    public static Map<Integer, Persona> cargarPersonas() {
        Type type = new TypeToken<Map<Integer, Persona>>(){}.getType();
        Map<Integer, Persona> personas = cargarDatos(PERSONAS_FILE, type);
        return personas != null ? personas : new HashMap<>();
    } 
        
    /**
     * Carga los vehiculos desde el archivo JSON
     * @return 
     */
    public static Map<Integer, Vehiculo> cargarVehiculos() {
        Type type = new TypeToken<Map<Integer, Vehiculo>>(){}.getType();
        Map<Integer, Vehiculo> vehiculos = cargarDatos(PERSONAS_FILE, type);
        return vehiculos != null ? vehiculos : new HashMap<>();
    }
       
    /**
     * Carga las estaciones desde el archivo JSON
     * @return 
     */
    public static Map<Integer, Estacion> cargarEstaciones() {
        Type type = new TypeToken<Map<Integer, Estacion>>(){}.getType();
        Map<Integer, Estacion> estaciones = cargarDatos(PERSONAS_FILE, type);
        return estaciones != null ? estaciones : new HashMap<>();
    }
    
    
    /**
     * Carga los horarios desde el archivo JSON
     */
     /*
    public static Map<Integer, Horario> cargarHorarios() {
        Type type = new TypeToken<Map<Integer, Horario>>(){}.getType();
        Map<Integer, Horario> horarios = cargarDatos(ESTACIONES_FILE, type);
        return horarios != null ? horarios : new HashMap<>();
    }
    */
    
    
    
    /**
     * Carga los contadores de IDs
     * @return 
     */
    public static Map<String, Integer> cargarContadores() {
    Type type = new TypeToken<Map<String, Integer>>(){}.getType();
    Map<String, Integer> counters = cargarDatos(COUNTERS_FILE, type);
    
    if (counters == null) {
        counters = new HashMap<>();
        counters.put("nextCitaId", 1);
        counters.put("nextFacturaId", 1);
        counters.put("nextHorarioId", 1);
        counters.put("nextPacienteId", 103); // NUEVA LÍNEA
    }
    
    return counters;
}

    
    // ========== MÉTODOS AUXILIARES GENÉRICOS ==========
    
    /**
     * Método genérico para guardar datos en un archivo JSON
     */
    private static <T> boolean guardarDatos(String archivo, T datos) {
        try (Writer writer = new FileWriter(archivo)) {
            gson.toJson(datos, writer);
            System.out.println("Datos guardados: " + archivo);
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar " + archivo + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Método genérico para cargar datos desde un archivo JSON
     */
    private static <T> T cargarDatos(String archivo, Type type) {
        File file = new File(archivo);
        
        if (!file.exists()) {
            System.out.println("Archivo no encontrado: " + archivo + " (se creará al guardar)");
            return null;
        }
        
        try (Reader reader = new FileReader(archivo)) {
            T datos = gson.fromJson(reader, type);
            System.out.println("Datos cargados: " + archivo);
            return datos;
        } catch (IOException e) {
            System.err.println("Error al cargar " + archivo + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Verifica si existen archivos de datos guardados
     * @return 
     */
    public static boolean existenDatosGuardados() {
        return new File(PERSONAS_FILE).exists() || 
               new File(VEHICULOS_FILE).exists() ||
               new File(ESTACIONES_FILE).exists();
    }
}