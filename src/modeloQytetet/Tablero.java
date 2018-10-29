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
        throw new UnsupportedOperationException("Sin implementar");
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
        TituloPropiedad titulocalle1 = new TituloPropiedad("Calle Buena Vista",500,50,10,150,250);
        casillas.add(new Casilla (1,titulocalle1));
        TituloPropiedad titulocalle2 = new TituloPropiedad("Calle Doctor Victor Escribano",600,60,12,150,250);
        casillas.add(new Casilla (2,titulocalle2));
        TituloPropiedad titulocalle3 = new TituloPropiedad("Calle Arabial",700,70,14,150,250);
        casillas.add(new Casilla (3,titulocalle3));
        casillas.add(new Casilla (4, -100, TipoCasilla.SORPRESA));
        carcel = new Casilla (5, -300, TipoCasilla.CARCEL);
        casillas.add(carcel);
        TituloPropiedad titulocalle4 = new TituloPropiedad("Calle Periodista Jose Maria Carulla",800,80,16,150,250);
        casillas.add(new Casilla (6,titulocalle4));
        casillas.add(new Casilla (7, 100, TipoCasilla.SORPRESA));
        TituloPropiedad titulocalle5 = new TituloPropiedad("Avenida de Madrid",900,90,18,150,250);
        casillas.add(new Casilla (8,titulocalle5));
        TituloPropiedad titulocalle6 = new TituloPropiedad("Camino de Ronda",1000,100,20,150,250);
        casillas.add(new Casilla (9,titulocalle6));
        TituloPropiedad titulocalle7 = new TituloPropiedad("Calle Pedro Antonio de Alarcon",830,80,16,150,250);
        casillas.add(new Casilla (10, -100, TipoCasilla.PARKING));
        casillas.add(new Casilla (11,titulocalle7));
        TituloPropiedad titulocalle8 = new TituloPropiedad("Calle Recogidas",780,80,16,150,250);
        casillas.add(new Casilla (12,titulocalle8));
        TituloPropiedad titulocalle9 = new TituloPropiedad("Avenida de Andalucia",620,60,12,150,250);
        casillas.add(new Casilla (13,titulocalle9));
        casillas.add(new Casilla (14, 200, TipoCasilla.SORPRESA));
        TituloPropiedad titulocalle10 = new TituloPropiedad("Avenida Juan Pablo II",550,50,11,150,250);
        casillas.add(new Casilla (15, 0, TipoCasilla.JUEZ));
        casillas.add(new Casilla (16,titulocalle10));
        TituloPropiedad titulocalle11 = new TituloPropiedad("Calle Curro Cuchares",950,95,19,150,250);
        casillas.add(new Casilla (17,titulocalle11));
        casillas.add(new Casilla (18, -300, TipoCasilla.IMPUESTO));
        TituloPropiedad titulocalle12 = new TituloPropiedad("Calle Elvira",870,90,19,150,250);
        casillas.add(new Casilla (19,titulocalle12));
        
    }

    Casilla obtenerCasillaFinal(Casilla casilla, int desplazamiento){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    Casilla obtenerCasillaNumero(int numCasilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    @Override
    public String toString(){
        return casillas.toString();
    }
    
}
