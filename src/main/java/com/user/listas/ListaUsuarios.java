package com.user.listas;

import com.user.model.Usuario;

public class ListaUsuarios {

    private Usuario listaUsuarios[];
    private int tamMaximo;
    private int aUsuario;

    public ListaUsuarios() {
        tamMaximo = 30;
        listaUsuarios = new Usuario[tamMaximo];
        aUsuario = 0;
    }

    public ListaUsuarios(int tam) {
        tamMaximo = tam;
        listaUsuarios = new Usuario[tamMaximo];
        aUsuario = 0;
    }

    public void agregarUsuario(Usuario nuevo) {
        if (nuevo == null) {
            System.out.println("Error: No se puede agregar un usuario nulo.");
            return;
        }
        if (aUsuario < tamMaximo) {
            listaUsuarios[aUsuario++] = nuevo;
        } else {
            System.out.println("Error: Lista de usuarios llena.");
        }
    }

    public void modificarUsuario(int pos, Usuario nuevo) {
        if (nuevo == null) {
            System.out.println("Error: No se puede modificar con un usuario nulo.");
            return;
        }
        if (pos >= 0 && pos < aUsuario) {
            listaUsuarios[pos] = nuevo;
        } else {
            System.out.println("Error: Posición inválida.");
        }
    }

    public void reemplazarUsuario(String identificacion, Usuario nuevo) {
        if (nuevo == null) {
            System.out.println("Error: No se puede reemplazar con un usuario nulo.");
            return;
        }
        int pos = consultarUsuarioXIdentificacion(identificacion);
        if (pos >= 0) {
            listaUsuarios[pos] = nuevo;
        } else {
            System.out.println("Error: Usuario con identificación " + identificacion + " no encontrado.");
        }
    }

    public int consultarUsuarioXIdentificacion(String identificacion) {
        if (identificacion == null) {
            return -1;
        }
        for (int i = 0; i < aUsuario; i++) {
            if (listaUsuarios[i] != null && listaUsuarios[i].getIdentificacion().equals(identificacion)) {
                return i;
            }
        }
        return -1;
    }

    public Usuario getUsuario(int pos) {
        if (pos >= 0 && pos < aUsuario) {
            return listaUsuarios[pos];
        }
        return null;
    }

    public int getTamMaximo() {
        return tamMaximo;
    }

    public int getAUsuario() {
        return aUsuario;
    }

    public ListaUsuarios filtroXActivos() {
        ListaUsuarios temporal = new ListaUsuarios(tamMaximo);
        for (int i = 0; i < aUsuario; i++) {
            if (listaUsuarios[i] != null && listaUsuarios[i].isActivo()) {
                temporal.agregarUsuario(listaUsuarios[i]);
            }
        }
        return temporal;
    }

    public ListaUsuarios filtroXInactivos() {
        ListaUsuarios temporal = new ListaUsuarios(tamMaximo);
        for (int i = 0; i < aUsuario; i++) {
            if (listaUsuarios[i] != null && !listaUsuarios[i].isActivo()) {
                temporal.agregarUsuario(listaUsuarios[i]);
            }
        }
        return temporal;
    }

    public ListaUsuarios unirLista(ListaUsuarios listaA, ListaUsuarios listaB) {
        int tamañoUnion = listaA.getAUsuario() + listaB.getAUsuario();
        ListaUsuarios temporal = new ListaUsuarios(tamañoUnion);
        for (int i = 0; i < listaA.getAUsuario(); i++) {
            temporal.agregarUsuario(listaA.getUsuario(i));
        }
        for (int i = 0; i < listaB.getAUsuario(); i++) {
            temporal.agregarUsuario(listaB.getUsuario(i));
        }
        return temporal;
    }

    public String toReporte() {
        StringBuilder sal = new StringBuilder("LISTA DE USUARIOS\n");
        for (int i = 0; i < aUsuario; i++) {
            if (listaUsuarios[i] != null) {
                sal.append(listaUsuarios[i].toString()).append("\n");
            }
        }
        return sal.toString();
    }

    public void cargarListaUsuarios() {
        agregarUsuario(new Usuario("Juan Perez","101","juan@gmail.com","88880001","San Jose",true));
        agregarUsuario(new Usuario("Maria Rodriguez","102","maria@gmail.com","88880002","Alajuela",true));
        agregarUsuario(new Usuario("Carlos Gonzalez","103","carlos@gmail.com","88880003","Cartago",true));
        agregarUsuario(new Usuario("Sofia Jimenez","104","sofia@gmail.com","88880004","Heredia",true));
        agregarUsuario(new Usuario("Andres Vargas","105","andres@gmail.com","88880005","Limon",true));
        agregarUsuario(new Usuario("Valeria Mora","106","valeria@gmail.com","88880006","Puntarenas",true));
        agregarUsuario(new Usuario("Jose Brenes","107","jose@gmail.com","88880007","Guanacaste",true));
        agregarUsuario(new Usuario("Daniela Chaves","108","daniela@gmail.com","88880008","San Jose",true));
        agregarUsuario(new Usuario("Kevin Solano","109","kevin@gmail.com","88880009","Cartago",true));
        agregarUsuario(new Usuario("Fernanda Rojas","110","fernanda@gmail.com","88880010","Heredia",true));
        agregarUsuario(new Usuario("Steven Hernandez","111","steven@gmail.com","88880011","Limon",true));
        agregarUsuario(new Usuario("Natalia Castro","112","natalia@gmail.com","88880012","Alajuela",true));
        agregarUsuario(new Usuario("Diego Araya","113","diego@gmail.com","88880013","Puntarenas",true));
        agregarUsuario(new Usuario("Laura Sanchez","114","laura@gmail.com","88880014","San Jose",true));
        agregarUsuario(new Usuario("Gabriel Quesada","115","gabriel@gmail.com","88880015","Cartago",true));
        agregarUsuario(new Usuario("Camila Mena","116","camila@gmail.com","88880016","Heredia",true));
        agregarUsuario(new Usuario("Esteban Leon","117","esteban@gmail.com","88880017","Guanacaste",true));
        agregarUsuario(new Usuario("Paola Solis","118","paola@gmail.com","88880018","Limon",true));
        agregarUsuario(new Usuario("Ricardo Campos","119","ricardo@gmail.com","88880019","Alajuela",true));
        agregarUsuario(new Usuario("Karla Valverde","120","karla@gmail.com","88880020","San Jose",true));
    }

    @Override
    public String toString() {
        return toReporte();
    }
}