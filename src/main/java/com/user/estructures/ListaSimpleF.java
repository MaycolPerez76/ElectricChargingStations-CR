/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listasSimples;

import pilas.Pila;

/**
 *
 * @author mayco
 */
public class ListaSimpleF {
  // +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
  // LISTA SIMPLE FINITAS 
  // Listas encadenadas , 
  // que su ultimo apuntador apunta a 0 o null 
  // +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
   private int TOP; // Maximo 
   private int Apunt[ ]; 
   private int INFO [ ]; 
   private int PTR;
   private Pila DISP;
   
   public ListaSimpleF(){
     TOP = 20;
     INFO = new int[TOP];
     Apunt = new int[TOP];
     PTR = 0;
     DISP = new Pila(TOP-1);
     inicializarPila();
   }
   
   public ListaSimpleF(int max){
     TOP = max+1;
     INFO = new int[TOP];
     Apunt = new int[TOP];
     PTR = 0;
     DISP = new Pila(TOP-1);
     inicializarPila();
   }
   
   public void inicializarPila(){
     //DISP.getTop();
     for(int i=1; i<TOP; i++){
       DISP.agregarElemento(i);
     }
   }
   
   public String desplegarPila(){
     return DISP.toString();
   }
   
   public void agregarElemento(int elemento){
     //saca un elemento de la pila y disminuye el apunt en 1
     int temp = DISP.eliminarElemento();
     INFO[temp] = elemento;
     Apunt[temp] = PTR;
     PTR = temp;
   }
   
   public int eliminarElemento(){
     int temp = PTR; 
     int elemento = INFO[temp];
     INFO[temp] = 0;
     PTR = Apunt[temp];
     Apunt[temp] = 0;
     //devuelve un elemento a la pila y aumenta el apunt en 1
     DISP.agregarElemento(temp);
     return elemento;
   }
   
   public int localizarElemento(int elemento){
     int R = PTR;
     int Enodo = 0;
     for(int i=R; i<TOP; i++){
       if(INFO[i]==elemento){
         Enodo=i;
         R=Apunt[i];
       }
     }
     return Enodo;
   }
   
   public String toString(){
     String salida = "";
     for(int i=1; i<TOP; i++){
       salida+=i+" ";
       salida+="["+INFO[i]+"]";
       salida+="["+Apunt[i]+"]\n";
     }
     salida+="Estado de la pila\n";
     salida+=DISP.toString();
     return salida;
   }
   
   public String toStringI(){
     String salida = "";
     for(int i=TOP-1; i>0; i--){//invertido
       salida+=i+" ";
       salida+="["+INFO[i]+"]";
       salida+="["+Apunt[i]+"]\n";
     }
     salida+="Estado de la pila\n";
     salida+=DISP.toString();
     return salida;
   }
}

