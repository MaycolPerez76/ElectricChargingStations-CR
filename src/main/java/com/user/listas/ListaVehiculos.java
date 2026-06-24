package com.user.listas;

/**
 *
 * @author myava
 */
import com.user.model.*;

public class ListaVehiculos {

    private Vehiculo listaVehiculos[];
    private int tamMaximo;
    private int aVehiculo;

    public ListaVehiculos() {
        tamMaximo = 30;
        listaVehiculos = new Vehiculo[tamMaximo];
        aVehiculo = 0;
    }

    public ListaVehiculos(int tam) {
        tamMaximo = tam;
        listaVehiculos = new Vehiculo[tamMaximo];
        aVehiculo = 0;
    }

    public void agregarVehiculo(Vehiculo nuevo) {
        if (nuevo == null) {
            System.out.println("Error: No se puede agregar un vehículo nulo.");
            return;
        }
        if (nuevo.getPropietario() == null) {
            System.out.println("Error: El vehículo debe tener un propietario válido.");
            return;
        }
        if (aVehiculo < tamMaximo) {
            listaVehiculos[aVehiculo++] = nuevo;
        } else {
            System.out.println("Error: Lista de vehículos llena.");
        }
    }

    public void modificarVehiculo(int pos, Vehiculo nuevo) {
        if (nuevo == null) {
            System.out.println("Error: No se puede modificar con un vehículo nulo.");
            return;
        }
        if (nuevo.getPropietario() == null) {
            System.out.println("Error: El vehículo debe tener un propietario válido.");
            return;
        }
        if (pos >= 0 && pos < aVehiculo) {
            listaVehiculos[pos] = nuevo;
        } else {
            System.out.println("Error: Posición inválida.");
        }
    }

    public void reemplazarVehiculo(String idNuevo, Vehiculo nuevo) {
        if (nuevo == null) {
            System.out.println("Error: No se puede reemplazar con un vehículo nulo.");
            return;
        }
        if (nuevo.getPropietario() == null) {
            System.out.println("Error: El vehículo debe tener un propietario válido.");
            return;
        }
        int pos = consultarVehiculoXID(idNuevo);
        if (pos >= 0) {
            listaVehiculos[pos] = nuevo;
        } else {
            System.out.println("Error: Vehículo con ID " + idNuevo + " no encontrado.");
        }
    }

    public void cambiarEstado(String idNuevo, int estadoNuevo) {
        int pos = consultarVehiculoXID(idNuevo);
        if (pos >= 0) {
            listaVehiculos[pos].setEstado(estadoNuevo);
        } else {
            System.out.println("Error: Vehículo con ID " + idNuevo + " no encontrado.");
        }
    }

    public int consultarVehiculoXID(String id) {
        if (id == null) {
            return -1;
        }
        for (int i = 0; i < aVehiculo; i++) {
            if (listaVehiculos[i] != null && listaVehiculos[i].getIdVehiculo().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public String getPlaca(int pos) {
        if (pos >= 0 && pos < aVehiculo && listaVehiculos[pos] != null) {
            return listaVehiculos[pos].getPlaca();
        }
        return null;
    }

    public Vehiculo getVehiculo(int pos) {
        if (pos >= 0 && pos < aVehiculo) {
            return listaVehiculos[pos];
        }
        return null;
    }

    public int getTamMaximo() {
        return tamMaximo;
    }

    public int getAVehiculo() {
        return aVehiculo;
    }

    public ListaVehiculos filtroXActivos() {
        ListaVehiculos temporal = new ListaVehiculos(tamMaximo);
        for (int i = 0; i < aVehiculo; i++) {
            if (listaVehiculos[i] != null && listaVehiculos[i].getEstado() == 1) {
                temporal.agregarVehiculo(listaVehiculos[i]);
            }
        }
        return temporal;
    }

    public ListaVehiculos filtroXDefectuosos() {
        ListaVehiculos temporal = new ListaVehiculos(tamMaximo);
        for (int i = 0; i < aVehiculo; i++) {
            if (listaVehiculos[i] != null && listaVehiculos[i].getEstado() == 3) {
                temporal.agregarVehiculo(listaVehiculos[i]);
            }
        }
        return temporal;
    }

    public ListaVehiculos filtroXPlaca(String placa) {
        ListaVehiculos temporal = new ListaVehiculos(tamMaximo);
        if (placa == null) {
            return temporal;
        }
        for (int i = 0; i < aVehiculo; i++) {
            if (listaVehiculos[i] != null && listaVehiculos[i].getPlaca().equalsIgnoreCase(placa)) {
                temporal.agregarVehiculo(listaVehiculos[i]);
            }
        }
        return temporal;
    }

    public ListaVehiculos unirLista(ListaVehiculos listaA, ListaVehiculos listaB) {
        int tamañoUnion = listaA.getAVehiculo() + listaB.getAVehiculo();
        ListaVehiculos temporal = new ListaVehiculos(tamañoUnion);
        for (int i = 0; i < listaA.getAVehiculo(); i++) {
            temporal.agregarVehiculo(listaA.getVehiculo(i));
        }
        for (int i = 0; i < listaB.getAVehiculo(); i++) {
            temporal.agregarVehiculo(listaB.getVehiculo(i));
        }
        return temporal;
    }

    public String toReporte() {
        StringBuilder sal = new StringBuilder("LISTA DE VEHICULOS GENERAL\n");
        for (int i = 0; i < aVehiculo; i++) {
            if (listaVehiculos[i] != null) {
                sal.append(listaVehiculos[i].toDetalle()).append("\n");
            }
        }
        return sal.toString();
    }

    /**
     * Carga inicial de 20 vehículos de ejemplo, asignando cada uno a un usuario
     * de la lista proporcionada. Si hay menos de 20 usuarios, se cargan solo
     * los vehículos que puedan tener propietario (nunca se crea un vehículo sin
     * dueño).
     */
    public void cargarListaVehiculos(ListaUsuarios usuarios) {
        String[][] datos = {
            {"V001", "EV-001", "Nissan", "Leaf", "40"},
            {"V002", "EV-002", "BYD", "Dolphin", "45"},
            {"V003", "EV-003", "BYD", "Yuan Plus", "60"},
            {"V004", "EV-004", "Tesla", "Model 3", "57"},
            {"V005", "EV-005", "Tesla", "Model Y", "75"},
            {"V006", "EV-006", "Hyundai", "Kona Electric", "64"},
            {"V007", "EV-007", "Hyundai", "Ioniq 5", "77"},
            {"V008", "EV-008", "Kia", "EV6", "77"},
            {"V009", "EV-009", "Kia", "Niro EV", "64"},
            {"V010", "EV-010", "Volvo", "EX30", "69"},
            {"V011", "EV-011", "Volvo", "XC40 Recharge", "78"},
            {"V012", "EV-012", "BMW", "iX1", "66"},
            {"V013", "EV-013", "BMW", "i4", "81"},
            {"V014", "EV-014", "MG", "MG4", "64"},
            {"V015", "EV-015", "JAC", "EJS1", "31"},
            {"V016", "EV-016", "Geely", "Geometry C", "70"},
            {"V017", "EV-017", "Dongfeng", "Nammi", "42"},
            {"V018", "EV-018", "Changan", "E-Star", "33"},
            {"V019", "EV-019", "Audi", "Q4 e-tron", "82"},
            {"V020", "EV-020", "BYD", "Seagull", "39"}
        };

        int cantidadUsuarios = usuarios.getAUsuario();
        int limite = Math.min(datos.length, cantidadUsuarios);

        for (int i = 0; i < limite; i++) {
            Usuario propietario = usuarios.getUsuario(i);
            if (propietario != null) {
                agregarVehiculo(new Vehiculo(
                        datos[i][0],
                        datos[i][1],
                        datos[i][2],
                        datos[i][3],
                        Double.parseDouble(datos[i][4]),
                        "CCS2",
                        80,
                        propietario,
                        1
                ));
            }
        }

        if (limite < datos.length) {
            System.out.println("Carga parcial: solo se cargaron " + limite + " vehículos porque hay " + cantidadUsuarios + " usuarios.");
        }
    }

    @Override
    public String toString() {
        return toReporte();
    }
}
