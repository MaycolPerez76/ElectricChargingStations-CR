/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user.algoritmos;

import com.user.estructures.Grafos;
import com.user.listas.ListaCargas;

/**
 *
 * @author mayco
 */
public class CargasGrafo {
    private Grafos grafo;
    private ListaCargas cargas;
    
        public CargasGrafo() {
        cargas = new ListaCargas();
        cargas.cargar(idCarga);
        totalEstaciones = estaciones.getAEstacion();
        grafo = new Grafos(totalEstaciones);
        construirGrafo();
    }
    
    
}
