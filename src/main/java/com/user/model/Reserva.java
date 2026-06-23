package com.user.model;

/**
 *
 * @author myava
 */
public class Reserva {

    private String idReserva;
    private Usuario usuario;
    private Vehiculo vehiculo;
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
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    public String getIdReserva() {
        return idReserva;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
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
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", usuario=" + usuario.getNombreCompleto() +
                ", vehiculo=" + vehiculo.getPlaca() +
                ", fechaHora=" + fechaHora +
                ", estado=" + estado +
                '}';
    }

    @Override
    public String toString() {
        return toDetalle();
    }
}
