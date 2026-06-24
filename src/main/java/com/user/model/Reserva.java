package com.user.model;

/**
 *
 * @author myava
 */
public class Reserva {

    private String idReserva;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private Estacion estacion;
    private String fechaHora;
    private String estado;

    public Reserva() {
    }

    public Reserva(String idReserva,
            Usuario usuario,
            Vehiculo vehiculo,
            Estacion estacion,
            String fechaHora,
            String estado) {

        this.idReserva = idReserva;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.estacion = estacion;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String toDetalle() {
        return "Reserva{"
                + "idReserva=" + idReserva
                + ", usuario=" + usuario.getNombreCompleto()
                + ", vehiculo=" + vehiculo.getPlaca()
                + ", estacion=" + (estacion != null ? estacion.getNombre() : "Sin estación")
                + ", fechaHora=" + fechaHora
                + ", estado=" + estado
                + '}';
    }

    @Override
    public String toString() {
        return toDetalle();
    }
}
