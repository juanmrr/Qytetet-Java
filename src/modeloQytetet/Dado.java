/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloQytetet;

import java.util.Random;

/**
 *
 * @author juanma
 */
public class Dado {
    
    private static final Dado instance = new Dado();
    
    private int valor;
    
    private Dado(){}
    
    int tirar() {
        
        Random numero_aleatorio = new Random();
        
        valor = (int) (numero_aleatorio.nextDouble() * 6 + 1);
        
        return valor;
        
    }

    public int getValor() {
        return valor;
    }
    
    public static Dado getInstance() {
        return instance;
    }
    
}
