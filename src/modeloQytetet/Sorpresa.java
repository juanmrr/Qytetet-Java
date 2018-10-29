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
public class Sorpresa {
    private String texto;
    private TipoSorpresa tipo;
    private int valor;
        
    public Sorpresa(String texto, int valor, TipoSorpresa tipo){
        this.texto = texto;
        this.valor = valor;
        this.tipo = tipo;
    }
    
    String getTexto(){
        return texto;
    }
    
    int getValor(){
        return valor;
    }
    
    TipoSorpresa getTipo(){
        return tipo;
    }
    
    @Override
    public String toString(){
        return "Sorpresa{" + "texto:" + texto + ", valor:" + Integer.toString(valor) + ", tipo:" + tipo + "}";
    }
    
}
