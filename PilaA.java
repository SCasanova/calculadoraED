/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pilas;

/**
 *
 * @author personal
 */
public class PilaA <T> implements Pila <T>{
    private T[] datosPila;
    private int tope;
    private final int MAXIMO = 20;

    public PilaA() {
        datosPila = (T[]) new Object[MAXIMO];
        tope= -1; //pila vacia
    }

    @Override
    public void push(T datos) {
        if(tope == this.datosPila.length -1)
            expand();
        tope++;
        datosPila[tope] = datos;
    }

    private void expand(){
        T[] masGrande = (T[]) new Object[this.datosPila.length *2];
        
        for (int i = 0; i <= tope; i++)
            masGrande[i] = datosPila[i];
        datosPila = masGrande;
    }
    
    @Override
    public T pop() {
        if (this.isEmpty())
            throw new ColeccionVaciaException("La pila está vacía");   
        T resultado;
        resultado  = this.datosPila[tope];
        this.datosPila[tope] = null;
        tope--;
        return resultado;
    }

    
    @Override
    public boolean isEmpty() {
        return tope == -1;
    }

    @Override
    public T peek() {
        if (this.isEmpty())
            throw new ColeccionVaciaException("La pila está vacía");
        return this.datosPila[tope];
    }
    
    public String toString(){
        StringBuilder sB = new StringBuilder();
        
        for(int i = tope; i >= 0; i--)
            sB.append(datosPila[i]).append("\n");
        return sB.toString();
    }
    
    
    
    
}
