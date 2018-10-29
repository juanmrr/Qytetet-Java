/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloQytetet;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author juanma
 */
public class PruebaQytetet {
    
    static Qytetet juego = Qytetet.getInstance();
    
    private static final Scanner in = new Scanner (System.in); 
    
    private static ArrayList<Sorpresa> mayorQueCero(){
        
        ArrayList<Sorpresa> aux = new ArrayList();

        for (Sorpresa i: juego.getMazo()){
            if (i.getValor() > 0){
                aux.add(i);
            }
        }
           
        return aux;
        
    }
    
    private static ArrayList<Sorpresa> irACasilla(){
        
        ArrayList<Sorpresa> aux = new ArrayList();

        for (Sorpresa i: juego.getMazo()){
            if (i.getTipo() == TipoSorpresa.IRACASILLA){
                aux.add(i);
            }
        }
           
        return aux;
        
    }
    
    private static ArrayList<Sorpresa> tipos(TipoSorpresa tipo){
        
        ArrayList<Sorpresa> aux = new ArrayList<Sorpresa>();

        for (Sorpresa i: juego.getMazo()){
            if (i.getTipo() == tipo){
                aux.add(i);
            }
        }
           
        return aux;
        
    }
    
    static ArrayList<String> getNombreJugadores() {
        
        int num_jugadores = 0;
        
        String s = null;
        
        ArrayList<String> nombres = new ArrayList();
        
        System.out.println("Introduzca el número de jugadores:");
        
        while (num_jugadores < 2 || num_jugadores > 4 ){
            s = in.nextLine();
            num_jugadores = Integer.parseInt(s);
        }
            
        for (int i = 0; i < num_jugadores; i++){
            System.out.println("Introduzca el nombre del jugador " + (i + 1) + ":");
            s = in.nextLine();
            nombres.add(s);
        }
        
        return nombres;
        
    }
    
    public static void main(String[] argv){
        
        ArrayList<String> nombres = getNombreJugadores();
        
        juego.inicializarJuego(nombres);
        
        System.out.println(juego.getJugadores().toString());
        
        System.out.println(juego.getMazo().toString());
        
        System.out.println(mayorQueCero());
        
        System.out.println(irACasilla());
        
        for (TipoSorpresa i: TipoSorpresa.values()){
            ArrayList aux = new ArrayList();
            aux = tipos(i);
            System.out.println(aux);
        }
        
        System.out.println(juego.getTablero().toString());
    }
    
}
