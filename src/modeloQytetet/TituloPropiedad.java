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
public class TituloPropiedad {
    
    private String nombre;
    private boolean hipotecada;
    private int precioCompra;
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private int precioEdificar;
    private int numCasas;
    private int numHoteles;
    private Jugador propietario;
    
    TituloPropiedad (String nombre, int precioCompra, int alquilerBase, float factorRevalorizacion, int hipotecaBase, int precioEdificar){
        this.nombre = nombre;
        this.hipotecada = false;
        this.precioCompra = precioCompra;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
        this.numCasas = 0;
        this.numHoteles = 0;
        this.propietario = null;
    }
    
    int calcularCosteCancelar() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int calcularCosteHipotecar() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int calcularImporteAlquiler() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int calcularPrecioVenta() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    void cancelarHipoteca() {
        
    }
    
    void cobrarAlquiler(int coste){
        
    }
    
    void edificarCasa() {
        
    }
    
    void edificarHotel() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getHipotecada() {
        return hipotecada;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public int getAlquilerBase() {
        return alquilerBase;
    }

    public float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    public int getHipotecaBase() {
        return hipotecaBase;
    }

    public int getPrecioEdificar() {
        return precioEdificar;
    }

    public int getNumCasas() {
        return numCasas;
    }

    public int getNumHoteles() {
        return numHoteles;
    }
    
    Jugador getPropietario() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int hipotecar() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    int pagarAlquiler() {
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    boolean propietarioEncarcelado() {
        
        boolean aux = false;
        
        if (propietario.getEncarcelado())
            aux = true;
        
        return aux;
        
    }

    void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }
    
    void setPropietario(Jugador jugador) {
        this.propietario = jugador;
    }
    
    boolean tengoPropietario() {
       
        boolean aux = false;
        
        if (propietario != null)
            aux = true;
        
        return aux;
        
    }
    
    @Override
    public String toString(){
        
        String aux = new String();
        
        aux = "Nombre: " + nombre + ", hipotecada: " + hipotecada + ", precio de compra: " + precioCompra + ", Alquiler base: " + alquilerBase + ", Factor de revalorizacion: " + factorRevalorizacion + ", Hipoteca base:" + hipotecaBase + ", Precio edificar: " + precioEdificar + ", Numero de casas: " + numCasas + ", Numero de hoteles: " + numHoteles;
        
        if (propietario != null)
            aux = aux + ", propietario: " + propietario.getNombre();
        
        aux = aux + "}";
        
        return aux;
    }
    
}
