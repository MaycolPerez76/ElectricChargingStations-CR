package com.user.service;

import com.user.model.Usuario;
import com.user.model.Vehiculo;

import java.util.List;

public interface VehiculoService {

    Vehiculo registrarVehiculo(Usuario propietario,
                               Vehiculo vehiculo);

    Vehiculo buscarPorId(int idVehiculo);

    Vehiculo buscarPorPlaca(String placa);

    List<Vehiculo> obtenerTodos();

    boolean eliminarVehiculo(int idVehiculo);

}