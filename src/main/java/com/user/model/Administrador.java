/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user.model;
import com.user.algoritmos.EstacionesGrafo;
import com.user.listas.*;

/**
 * Administrador: operaciones de gestión sobre las listas del sistema.
 */
public class Administrador {
    private ListaEstaciones listaEstaciones;
    private ListaUsuarios listaUsuarios;
    private ListaVehiculos listaVehiculos;
    private ListaCargas listaCargas;
    private ListaReservas listaReservas;
    private EstacionesGrafo estacionesGrafo;

    public Administrador(ListaEstaciones le, ListaUsuarios lu, ListaVehiculos lv,
                         ListaCargas lc, ListaReservas lr) {
        this.listaEstaciones = le;
        this.listaUsuarios = lu;
        this.listaVehiculos = lv;
        this.listaCargas = lc;
        this.listaReservas = lr;
        this.estacionesGrafo = new EstacionesGrafo(); // ya tiene datos
    }

    public void listarEstaciones() {
        System.out.println(listaEstaciones.toReporte());
    }

    public void agregarEstacion(Estacion e) {
        if (e == null) return;
        listaEstaciones.agregarEstacion(e);
    }

    public void modificarEstacion(int id, String nuevoEstado, int nuevosCargadores) {
        int pos = listaEstaciones.consultarEstacionXID(id);
        if (pos < 0) {
            System.out.println("Estación no encontrada: " + id);
            return;
        }
        Estacion est = listaEstaciones.getEstacion(pos);
        if (nuevoEstado != null) est.setEstado(nuevoEstado);
        if (nuevosCargadores >= 0) est.setCantidadCargadores(nuevosCargadores);
        listaEstaciones.modificarEstacion(pos, est);
    }

    public void listarUsuarios() {
        System.out.println(listaUsuarios.toReporte());
    }

    public Usuario buscarUsuario(String identificacion) {
        int pos = listaUsuarios.consultarUsuarioXIdentificacion(identificacion);
        if (pos < 0) return null;
        return listaUsuarios.getUsuario(pos);
    }

    public void desactivarUsuario(String identificacion) {
        int pos = listaUsuarios.consultarUsuarioXIdentificacion(identificacion);
        if (pos >= 0) {
            Usuario u = listaUsuarios.getUsuario(pos);
            u.setActivo(false);
            listaUsuarios.modificarUsuario(pos, u);
            System.out.println("Usuario desactivado: " + identificacion);
        } else {
            System.out.println("Usuario no encontrado: " + identificacion);
        }
    }

    public void listarCargas() {
        System.out.println(listaCargas.toReporte());
    }

    public void cargasPorEstacion(int idEstacion) {
        int pos = listaEstaciones.consultarEstacionXID(idEstacion);
        if (pos < 0) {
            System.out.println("Estación no encontrada: " + idEstacion);
            return;
        }
        Estacion e = listaEstaciones.getEstacion(pos);
        ListaCargas filtro = listaCargas.filtroXEstacion(e);
        System.out.println(filtro.toReporte());
    }

    public double energiaTotal() {
        double suma = 0.0;
        for (int i = 0; i < listaCargas.getACarga(); i++) {
            Carga c = listaCargas.getCarga(i);
            if (c != null) suma += c.getEnergiaConsumida();
        }
        return suma;
    }

    public void listarReservas() {
        System.out.println(listaReservas.toReporte());
    }

    public void cambiarEstadoReserva(String idReserva, String nuevoEstado) {
        int pos = listaReservas.consultarReservaXID(idReserva);
        if (pos >= 0) {
            Reserva r = listaReservas.getReserva(pos);
            r.setEstado(nuevoEstado);
            listaReservas.modificarReserva(pos, r);
            System.out.println("Reserva " + idReserva + " actualizada a: " + nuevoEstado);
        } else {
            System.out.println("Reserva no encontrada: " + idReserva);
        }
    }

    public void estacionConMasCargas() {
        int mejorId = -1;
        int max = -1;
        for (int i = 0; i < listaEstaciones.getAEstacion(); i++) {
            Estacion e = listaEstaciones.getEstacion(i);
            ListaCargas filtro = listaCargas.filtroXEstacion(e);
            if (filtro.getACarga() > max) {
                max = filtro.getACarga();
                mejorId = e.getIdEstacion();
            }
        }
        if (mejorId >= 0) {
            System.out.println("Estación con más cargas: " + listaEstaciones.getEstacion(listaEstaciones.consultarEstacionXID(mejorId)).getNombre() + " (" + max + ")");
        } else {
            System.out.println("No hay estaciones o cargas registradas.");
        }
    }

    public double ingresosTotales() {
        double total = 0.0;
        for (int i = 0; i < listaCargas.getACarga(); i++) {
            Carga c = listaCargas.getCarga(i);
            if (c != null) total += c.getPrecioRegular() * c.getEnergiaConsumida();
        }
        return total;
    }

    private int obtenerDistancia(String ubi1, String ubi2) {
        if (ubi1 == null || ubi2 == null) return 999;
        if (ubi1.equalsIgnoreCase(ubi2)) return 0;
        if (ubi1.equalsIgnoreCase("San José")) {
            if (ubi2.equalsIgnoreCase("Cartago")) return 22;
            if (ubi2.equalsIgnoreCase("Heredia")) return 11;
            if (ubi2.equalsIgnoreCase("Alajuela")) return 18;
        }
        if (ubi1.equalsIgnoreCase("Cartago")) {
            if (ubi2.equalsIgnoreCase("San José")) return 22;
            if (ubi2.equalsIgnoreCase("Heredia")) return 30;
            if (ubi2.equalsIgnoreCase("Alajuela")) return 40;
        }
        if (ubi1.equalsIgnoreCase("Heredia")) {
            if (ubi2.equalsIgnoreCase("San José")) return 11;
            if (ubi2.equalsIgnoreCase("Cartago")) return 30;
            if (ubi2.equalsIgnoreCase("Alajuela")) return 10;
        }
        if (ubi1.equalsIgnoreCase("Alajuela")) {
            if (ubi2.equalsIgnoreCase("San José")) return 18;
            if (ubi2.equalsIgnoreCase("Cartago")) return 40;
            if (ubi2.equalsIgnoreCase("Heredia")) return 10;
        }
        return 999;
    }

    public void distanciaEntreUbicaciones(String ubi1, String ubi2) {
        int d = obtenerDistancia(ubi1, ubi2);
        if (d >= 999) System.out.println("Distancia desconocida entre " + ubi1 + " y " + ubi2);
        else System.out.println("Distancia entre " + ubi1 + " y " + ubi2 + ": " + d + " km");
    }

    public void estacionMasCercana(String ubicacion) {
        int mejor = -1;
        int menor = Integer.MAX_VALUE;
        for (int i = 0; i < listaEstaciones.getAEstacion(); i++) {
            Estacion e = listaEstaciones.getEstacion(i);
            int d = obtenerDistancia(ubicacion, e.getUbicacion());
            if (d < menor) {
                menor = d;
                mejor = i;
            }
        }
        if (mejor >= 0) System.out.println("Estación más cercana a " + ubicacion + ": " + listaEstaciones.getEstacion(mejor).getNombre() + " (" + menor + " km)");
        else System.out.println("No hay estaciones registradas.");
    }

    // Menú simple para interacción por consola
    public void menu() {
        System.out.println("Menú de Administrador (métodos disponibles):");
        System.out.println("1. listarEstaciones()\n2. agregarEstacion(Estacion e)\n3. modificarEstacion(id,estado,cargadores)");
        System.out.println("4. listarUsuarios()\n5. buscarUsuario(id)\n6. desactivarUsuario(id)");
        System.out.println("7. listarCargas()\n8. cargasPorEstacion(id)\n9. energiaTotal()");
        System.out.println("10. listarReservas()\n11. cambiarEstadoReserva(idReserva, estado)");
        System.out.println("12. estacionConMasCargas()\n13. ingresosTotales()\n14. estacionMasCercana(ubicacion)");
    }
}
