/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pilas;

/**
 *
 * @author mayco
 */
public class Pila {
   private int pila[];
   private int apunt;
   private int TOP;
   
   public Pila() {
    pila = new int[21];
    TOP = 20;
    apunt = 0;
}
   
   public Pila(int tamaño) {
       pila = new int[tamaño +1];
       TOP = tamaño;
       apunt = 0;
   }
   
   
   public boolean pilaLlena() {
       return apunt == TOP;
   }
   public boolean pilaVacia() {
       return apunt == 0;
   }
   public void agregarElemento(int y) {
       pila[++apunt] = y;
   }
   public int eliminarElemento() {
       return pila[apunt--];
   }
   public String ckIngresarElemento() {
       String sal;
       if (!pilaLlena()) {
           return "Pila normal, elemento sera ingresado";
       } else {
           return "Pila llena, elemento no ingresara";
       }
   }
    public String ckIngresarElemento(int y) {
       String sal;
       if (!pilaLlena()) {
           return "Pila normal, elemento sera ingresado" + y;
       } else {
           return "Pila llena, elemento no ingresara" + y;
       }
   }
    
    public String toString()
  {
    String salida="\n";
    for(int i=pila.length-1;i>0;i--){
      salida+=i+"["+pila[i]+"]";
      if(i==pila.length-1){
        salida+="<==TOP";
      }
      if(i==apunt){
        salida+="<==Apunt";
      }
        salida+="\n";
    }
    salida+="Apunt="+apunt+"\n";
    return salida;
  }//fin del metodo toString
    
    
    
}
