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
import java.util.*;

public class DataBase {
    //Reiteracion de si misma para usarse como atributo del cual a traves de este pasaran los datos del constructor privado al metodo getInstance() 
    private static DataBase instance;
    private Map<Integer, Persona> personas = new HashMap<>();
    private Map<Integer, Vehiculo> vehiculos = new HashMap<>();
    private Map<Integer, Estacion> estaciones = new HashMap<>();
    private int nextPersonaId = 103; 
    private int nextVehiculoId = 1;
    private int nextEstacionId = 1;

    //Constructor privado donde almacenara los todo los datos
    private DataBase() {
        cargarDatos();
    }
    
    //Metodo para crear un Database unico y del cual no se puedan crear otros 
    public static synchronized DataBase getInstance() {
        if (instance == null) instance = new DataBase();
        return instance;
    }
    
    /**
     * Carga los datos desde archivos JSON y los pasa al constructor
     */
    private void cargarDatos() {
    if (DataPersistence.existenDatosGuardados()) {
        // Cargar desde archivos
        System.out.println("Cargando datos guardados...");
        personas = DataPersistence.cargarPersonas();
        vehiculos = DataPersistence.cargarVehiculos();
        estaciones = DataPersistence.cargarEstaciones();
        
        // Cargar contadores
        Map<String, Integer> counters = DataPersistence.cargarContadores();
        nextPersonaId = counters.getOrDefault("nextPersonaId", 1);
        nextVehiculoId = counters.getOrDefault("nextVehiculoId", 1);
        nextEstacionId = counters.getOrDefault("nextEstacionId", 1);
        
        System.out.println("Datos cargados exitosamente");
        System.out.println("   - Personas: " + personas.size());
        System.out.println("   - Vehiculos: " + vehiculos.size());
        System.out.println("   - Estaciones: " + estaciones.size());
    } else {
        // Primera ejecución - generar datos de prueba
        System.out.println("Primera ejecución - generando datos de prueba...");
        poblarDatosFalsos();
        guardarDatos(); // Guardar los datos iniciales
        System.out.println("Datos de prueba generados y guardados");
    }
}


    
    /**
     * Guarda todos los datos en archivos JSON
     */
    public void guardarDatos() {
        DataPersistence.guardarTodo(this);
    }

    private void poblarDatosFalsos() {
        // 2. Crear Pacientes
        Persona p1 = new Persona();
        p1.setIdPersona(1); 
        p1.setNombreCompleto("Juan Pérez Badilla"); 
        p1.setTelefono("77770001");
        p1.setIdentificacion("1198332");
        p1.setCorreoElectronico("Juanp@gmail.com");
        
        Persona p2 = new Persona();
        p2.setIdPersona(2); 
        p2.setNombreCompleto("María García Mendez Lopez"); 
        p2.setTelefono("77770002");
        p2.setIdentificacion("70102832");
        
        personas.put(p1.getIdPersona(), p1);
        personas.put(p2.getIdPersona(), p2);  
        
 
    
        //ultimo
          nextPersonaId = 3;
    }

    public void recargarDesdeArchivos() {
    if (DataPersistence.existenDatosGuardados()) {
        personas = DataPersistence.cargarPersonas();
        vehiculos = DataPersistence.cargarVehiculos();
        estaciones = DataPersistence.cargarEstaciones();

        Map<String, Integer> counters = DataPersistence.cargarContadores();
        nextPersonaId = counters.getOrDefault("nextPersonaId", nextPersonaId);
        nextEstacionId = counters.getOrDefault("nextEstacionId", nextEstacionId);
        nextVehiculoId = counters.getOrDefault("nextVehiculoId", nextVehiculoId);
    }
}

    
    public Map<Integer, Persona> getPersonas() { 
        return personas; 
    }
    
    public Map<Integer, Vehiculo> getVehiculos() {
        return vehiculos;
    }
    
    public Map<Integer, Estacion> getEstaciones() {
        return estaciones;
    }
    
    public int generarProximoIdPersona() { 
        return nextPersonaId++; 
    }
    
    public int generarProximoIdVehiculo() {
        return nextVehiculoId++;
    }
    
    public int generarProximoIdEstacion() {
        return nextEstacionId++;
    }

    
    // ========== MÉTODOS PARA OBTENER CONTADORES (usados por DataPersistence) ==========
    
    public int getNextPersonaId() {
        return nextPersonaId;
    }
    
    public int getNextVehiculoId() {
        return nextVehiculoId;
    }
    
    public int getNextEstacionId() {
        return nextEstacionId;
    }
}
