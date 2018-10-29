/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloQytetet;

import java.util.ArrayList;

/**
 *
 * @author juanma
 */
public class Jugador implements Comparable{
    
    private boolean encarcelado = false;
    private String nombre;
    private int saldo = 7500;
    private Sorpresa cartaLibertad = null;
    private ArrayList<TituloPropiedad> propiedades = new ArrayList();
    private Casilla casillaActual = null;
    
    Jugador(String nombre) {
        this.nombre = nombre;
    }
    
    boolean cancelarHipoteca(TituloPropiedad titulo) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean comprarTituloPropiedad() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int cuantasCasasHotelesTengo() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean deboPagarAlquiler() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    Sorpresa devolverCartaLibertad() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean edificarCasa(TituloPropiedad titulo) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean edificarHotel(TituloPropiedad titulo) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private void eliminarDeMisPropiedades(TituloPropiedad titulo) {
        
    }
    
    private boolean esDeMiPropiedad(TituloPropiedad titulo) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean estoyEnCalleLibre() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    boolean getEncarcelado() {
        return encarcelado;
    }

    String getNombre() {
        return nombre;
    }

    public int getSaldo() {
        return saldo;
    }

    Sorpresa getCartaLibertad() {
        return cartaLibertad;
    }

    ArrayList<TituloPropiedad> getPropiedades() {
        return propiedades;
    }

    Casilla getCasillaActual() {
        return casillaActual;
    }
    
    boolean hipotecarPropiedad(TituloPropiedad titulo) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void irACarcel(Casilla casilla) {
        
    }
    
    int modificarSaldo(int cantidad) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int obtenerCapital() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedades(boolean hipotecada) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void pagarAlquiler() {
        
    }
    
    void pagarImpuesto() {
        
    }
    
    void pagarLibertad(int cantidad) {
        
    }

    void setEncarcelado(boolean encarcelado) {
        this.encarcelado = encarcelado;
    }

    void setCartaLibertad(Sorpresa cartaLibertad) {
        this.cartaLibertad = cartaLibertad;
    }

    void setCasillaActual(Casilla casillaActual) {
        this.casillaActual = casillaActual;
    }
    
    boolean tengoCartaLibertad() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private boolean tengoSaldo(int cantidad) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean venderPropiedad(Casilla casilla) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    @Override
    
    public String toString(){
        
        String aux = new String();
        
        aux = "Jugador: " + nombre + ", encarcelado: " + encarcelado + ", saldo: " + saldo;
        
        if (cartaLibertad != null)
            aux = aux + ", carta libertad" +  cartaLibertad;
        
        if (propiedades.isEmpty())
            aux = aux + ", no tiene propiedades";
        else
            for (TituloPropiedad i: propiedades)
                aux = aux + i.getNombre() + ", ";
        if (casillaActual != null)
            
            aux = aux + ", casilla actual: " +  casillaActual + "}";
        
        else
            
            aux = aux + ", no se ha asignado una casilla aún";
            
        return aux;
    }

    @Override
    public int compareTo(Object object) {
        
        Jugador jugador = (Jugador) object;
        
        if (this.saldo > jugador.saldo)
            return 1;
        else if (this.saldo == jugador.saldo)
            return 0;
        else
            return -1;
    
    }
  
}
