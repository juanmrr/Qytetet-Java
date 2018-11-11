/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloQytetet;

/**
 *
 * @author juanma
 */
public class Casilla {
   
    private int numeroCasilla;
    private int coste;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;
    
    Casilla (int numeroCasilla, int coste, TipoCasilla tipo){
        
        this.numeroCasilla = numeroCasilla;
        this.coste = coste; //Coste 0??
        this.tipo = tipo;
        this.titulo = null;
        
    }
    
    Casilla (int numeroCasilla, TituloPropiedad titulo){
        
        this.numeroCasilla = numeroCasilla;
        this.tipo = TipoCasilla.CALLE;
        this.coste = titulo.getPrecioCompra();
        this.setTitulo(titulo);
        
    }
    
    TituloPropiedad asignarPropietario(Jugador jugador) {
        this.titulo.setPropietario(jugador);
        
        return titulo;
    }

    public int getCoste() {
        return coste;
    }
    
    public int getNumeroCasilla() {
        return numeroCasilla;
    }
    
    public TipoCasilla getTipo() {
        return tipo;
    }

    public TituloPropiedad getTitulo() {
        return titulo;
    }
    
    int pagarAlquiler() {
       
        int costeAlquiler = this.titulo.pagarAlquiler();
        
        return costeAlquiler;
        
    }
    
    boolean propietarioEncarcelado() {
        
        return titulo.propietarioEncarcelado();
        
    }

    private void setTitulo(TituloPropiedad titulo) {
        this.titulo = titulo;
    }
    
    boolean soyEdificable() {
       
        boolean aux = false;
        
        if (tipo == TipoCasilla.CALLE)
            aux = true;
        
        return aux;
        
    }
    
    boolean tengoPropietario() {
        
        return titulo.tengoPropietario();
        
    }
    
    @Override
    public String toString(){
        
        String aux = new String();
        
        aux = "\nCasilla {" + "Numero de casilla: " + numeroCasilla + ", Tipo de casilla: " + tipo + ", Coste: " + coste;
        
        if (titulo != null)
            aux = aux + ", Titulo de la propiedad: " + titulo;
        
        aux = aux + "}";
        
        return aux;
    }
    
    
}
