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
        
        boolean cancelar = false;
        
        int cantidad = titulo.calcularCosteCancelar();
        
        if (this.saldo > cantidad){
            this.modificarSaldo(-cantidad);
            titulo.cancelarHipoteca();
            cancelar = true;
        }
            
        return cancelar;
    }
    
    boolean comprarTituloPropiedad() {
        
        boolean comprado = false;
        
        int costeCompra = this.casillaActual.getCoste();
        
        TituloPropiedad titulo = null;
        
        if (costeCompra < this.saldo){
            titulo = this.casillaActual.asignarPropietario(this);
            comprado = true;
            this.propiedades.add(titulo);
            this.modificarSaldo(-costeCompra);
        }
        
        return comprado;
        
    }
    
    int cuantasCasasHotelesTengo() {
        
        int total = 0;
        
        for (TituloPropiedad i:propiedades)
            total = total + i.getNumCasas() + i.getNumHoteles();
            
        return total;
        
    }
    
    boolean deboPagarAlquiler() {
        
        boolean deboPagar = false;
        
        TituloPropiedad titulo = this.casillaActual.getTitulo();
        
        deboPagar = !this.esDeMiPropiedad(titulo) && titulo.tengoPropietario() && !titulo.propietarioEncarcelado() && !titulo.getHipotecada();
        
        return deboPagar;
        
    }
    
    Sorpresa devolverCartaLibertad() {
        
        Sorpresa aux = cartaLibertad;
        
        cartaLibertad = null;
            
        return aux;
        
    }
    
    boolean edificarCasa(TituloPropiedad titulo) {
        
        boolean edificada = false;
        
        int numCasas = titulo.getNumCasas();
        
        int costeEdificarCasa = 0;
        
        boolean tengoSaldo = false;
        
        if (numCasas < 4){
            costeEdificarCasa = titulo.getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(costeEdificarCasa);
            if (tengoSaldo){
                titulo.edificarCasa();
                this.modificarSaldo(-costeEdificarCasa);
                edificada = true;
            }
        }
        
        return edificada;
        
    }
    
    boolean edificarHotel(TituloPropiedad titulo) {
        
        boolean edificado = false;
        
        int numHoteles = titulo.getNumHoteles();
     
        int costeEdificarHotel = 0;
        
        boolean tengoSaldo = false;
        
        if (numHoteles < 4){
            costeEdificarHotel = titulo.getPrecioEdificar();
            tengoSaldo = this.tengoSaldo(costeEdificarHotel);
            if (tengoSaldo){
                titulo.edificarHotel();
                this.modificarSaldo(-costeEdificarHotel);
                edificado = true;
            }
        }
        
        return edificado;
        
    }
    
    void eliminarDeMisPropiedades(TituloPropiedad titulo) {
        
        this.propiedades.remove(titulo);
        
        titulo.setPropietario(null);
        
        int precioVenta = titulo.calcularPrecioVenta();
        
        this.modificarSaldo(precioVenta);
        
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
    
    void hipotecarPropiedad(TituloPropiedad titulo) {
        
        int costeHipoteca = titulo.hipotecar();
        
        this.modificarSaldo(costeHipoteca);
        
    }
    
    void irACarcel(Casilla casilla) {
        
        setCasillaActual(casilla);
        
        setEncarcelado(true);
        
    }
    
    void modificarSaldo(int cantidad) {
        
        int aux = this.getSaldo();
        
        aux = aux + cantidad;
        
        this.saldo = aux;
        
    }
    
    int obtenerCapital() {
        
        int total = saldo;
        
        for (TituloPropiedad i:propiedades){
            total = total + (i.getNumCasas() + i.getNumHoteles()) * i.getPrecioEdificar();
            if (i.getHipotecada())
                total = total - i.getHipotecaBase();
        }

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
        
        int costeAlquiler = this.casillaActual.getTitulo().pagarAlquiler();
        
        this.modificarSaldo(-costeAlquiler);
        
    }
    
    void pagarImpuesto() {
        this.modificarSaldo(casillaActual.getCoste());
    }
    
    void pagarLibertad(int cantidad) {
        
        boolean tengoSaldo = false;
        
        tengoSaldo = this.tengoSaldo(cantidad);
        if (tengoSaldo){
            this.setEncarcelado(false);
            this.modificarSaldo(-cantidad);
        }
        
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
    
    void venderPropiedad(Casilla casilla) {
        
        TituloPropiedad titulo = casilla.getTitulo();
        
        this.eliminarDeMisPropiedades(titulo);
        
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
                aux = aux + ", " + i.getNombre();
        
        if (casillaActual != null)
            
            aux = aux + ", casilla actual: " +  casillaActual;
        
        else
            
            aux = aux + ", no se ha asignado una casilla aún";
        
        aux = aux + "}\n";
            
        return aux;
    }

    @Override
    public int compareTo(Object otroJugador) {
        
        int otroCapital = ((Jugador) otroJugador).obtenerCapital();
        
        return otroCapital - this.obtenerCapital();
    }
  
}
