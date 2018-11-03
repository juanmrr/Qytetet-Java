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
        
        int total = 0;
        
        for (TituloPropiedad i:propiedades)
            total = total + i.getNumCasas() + i.getNumHoteles();
            
        return total;
        
    }
    
    boolean deboPagarAlquiler() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    Sorpresa devolverCartaLibertad() {
        
        Sorpresa aux = cartaLibertad;
        
        cartaLibertad = null;
            
        return aux;
        
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
        
        boolean aux = false;
        
        for (TituloPropiedad i:propiedades)
            if (i.equals(titulo))
                aux = true;
        
        return aux;
        
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
        
        int aux = 0;
        
        aux = this.getSaldo();
        
        aux = aux + cantidad;
        
        this.saldo = aux;
        
        return aux;
        
    }
    
    int obtenerCapital() {
        
        int total = 0;
        
        for (TituloPropiedad i:propiedades){
            total = total + (i.getNumCasas() + i.getNumHoteles()) * i.getPrecioEdificar();
            if (i.getHipotecada())
                total = total - i.getHipotecaBase();
        }
        
        total = total + this.getSaldo();
        
        return total;
        
    }
    
    ArrayList<TituloPropiedad> obtenerPropiedades(boolean hipotecada) {
        
        ArrayList<TituloPropiedad> aux = null;
        
        for (TituloPropiedad i:propiedades)
            if (i.getHipotecada() == hipotecada)
                aux.add(i);
        
        return aux;
        
    }
    
    void pagarAlquiler() {
        
    }
    
    void pagarImpuesto() {
        this.modificarSaldo(-(casillaActual.getCoste()));
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
        
        boolean aux = false;
        
        if (cartaLibertad != null)
            aux = true;
        
        return aux;
        
    }
    
    private boolean tengoSaldo(int cantidad) {
        
        boolean aux = false;
        
        if (this.getSaldo() > cantidad)
            aux = true;
        
        return aux;
        
    }
    
    boolean venderPropiedad(Casilla casilla) {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    @Override
    
    public String toString(){
        
        String aux = new String();
        
        aux = "Jugador: " + nombre + ", encarcelado: " + encarcelado + ", saldo: " + saldo + ", capital: " + this.obtenerCapital();
        
        if (cartaLibertad != null)
            aux = aux + ", carta libertad" +  cartaLibertad;
        
        if (propiedades.isEmpty())
            aux = aux + ", no tiene propiedades";
        else
            for (TituloPropiedad i: propiedades)
                aux = aux + i.getNombre() + ", ";
        if (casillaActual != null)
            
            aux = aux + ", casilla actual: " +  casillaActual;
        
        else
            
            aux = aux + ", no se ha asignado una casilla aún";
        
        aux = aux + "}";
            
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
