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
public class Tablero {
   
    private ArrayList<Casilla> casillas;
    private Casilla carcel = null;
    
    Tablero (){
        
        this.inicializar();
        
    }
    
    boolean esCasillCarcel(int numeroCasilla){
        
        boolean esCarcel = false;
        
        if (numeroCasilla == carcel.getNumeroCasilla())
            esCarcel = true;
        
        return esCarcel;
        
    }
    
    public Casilla getCarcel() {
        return carcel;
    }
    
    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }
    
    private void inicializar(){
        
        this.casillas = new ArrayList<>();
        
        casillas.add(new Casilla (0,0,TipoCasilla.SALIDA));
        TituloPropiedad titulocalle1 = new TituloPropiedad("Calle Buena Vista",500,50, (float) 1.0,150,250);
        casillas.add(new Casilla (1,titulocalle1));
        TituloPropiedad titulocalle2 = new TituloPropiedad("Calle Doctor Victor Escribano",600,60, (float) 1.2,150,250);
        casillas.add(new Casilla (2,titulocalle2));
        TituloPropiedad titulocalle3 = new TituloPropiedad("Calle Arabial",700,70, (float) 1.4,150,250);
        casillas.add(new Casilla (3,titulocalle3));
        casillas.add(new Casilla (4, 0, TipoCasilla.SORPRESA));
        carcel = new Casilla (5, 0, TipoCasilla.CARCEL);
        casillas.add(carcel);
        TituloPropiedad titulocalle4 = new TituloPropiedad("Calle Periodista Jose Maria Carulla",800,80, (float) 1.6,150,250);
        casillas.add(new Casilla (6,titulocalle4));
        casillas.add(new Casilla (7, 0, TipoCasilla.SORPRESA));
        TituloPropiedad titulocalle5 = new TituloPropiedad("Avenida de Madrid",900,90, (float) 1.8,150,250);
        casillas.add(new Casilla (8,titulocalle5));
        TituloPropiedad titulocalle6 = new TituloPropiedad("Camino de Ronda",1000,100, (float) 2.0,150,250);
        casillas.add(new Casilla (9,titulocalle6));
        TituloPropiedad titulocalle7 = new TituloPropiedad("Calle Pedro Antonio de Alarcon",830,80, (float) 1.6,150,250);
        casillas.add(new Casilla (10, -100, TipoCasilla.PARKING));
        casillas.add(new Casilla (11,titulocalle7));
        TituloPropiedad titulocalle8 = new TituloPropiedad("Calle Recogidas",780,80, (float) 1.6,150,250);
        casillas.add(new Casilla (12,titulocalle8));
        TituloPropiedad titulocalle9 = new TituloPropiedad("Avenida de Andalucia",620,60, (float) 1.2,150,250);
        casillas.add(new Casilla (13,titulocalle9));
        casillas.add(new Casilla (14, 0, TipoCasilla.SORPRESA));
        TituloPropiedad titulocalle10 = new TituloPropiedad("Avenida Juan Pablo II",550,50, (float) 1.1,150,250);
        casillas.add(new Casilla (15, 0, TipoCasilla.JUEZ));
        casillas.add(new Casilla (16,titulocalle10));
        TituloPropiedad titulocalle11 = new TituloPropiedad("Calle Curro Cuchares",950,95, (float) 1.9,150,250);
        casillas.add(new Casilla (17,titulocalle11));
        casillas.add(new Casilla (18, -300, TipoCasilla.IMPUESTO));
        TituloPropiedad titulocalle12 = new TituloPropiedad("Calle Elvira",870,90, (float) 1.9,150,250);
        casillas.add(new Casilla (19,titulocalle12));
        
    }

    Casilla obtenerCasillaFinal(Casilla casilla, int desplazamiento){
        
        int posicion = this.casillas.indexOf(casilla);
        
        Casilla aux = this.casillas.get((posicion + desplazamiento) % casillas.size());
        
        return aux;
        
    }
    
    Casilla obtenerCasillaNumero(int numCasilla){
        
        Casilla casilla = this.casillas.get(numCasilla);
        
        return casilla;
        
    }
    
    @Override
    public String toString(){
        return casillas.toString();
    }
    
}
