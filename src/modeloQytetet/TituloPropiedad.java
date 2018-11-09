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
        
        int costeCancelar;
        
        costeCancelar = (int) (calcularCosteHipotecar() * 1.1);
        
        return costeCancelar;
        
    }
    
    int calcularCosteHipotecar() {
        
        int costeHipoteca = (int) ((this.getNumCasas() * 0.5) + (this.getNumHoteles() * 2) + 1);
        
        return costeHipoteca;
        
    }
    
    int calcularImporteAlquiler() {
        
        int costeAlquiler = 0;
        
        costeAlquiler = (int) (this.alquilerBase * ((this.numCasas * 0.5) + (this.numHoteles * 2) + 1)); 
        
        return costeAlquiler;
        
    }
    
    int calcularPrecioVenta() {
        
        int precioVenta = (int) (this.precioCompra + (this.numCasas + this.numHoteles) * this.precioEdificar * this.factorRevalorizacion);
        
        return precioVenta;
        
    }
    
    void cancelarHipoteca() {
        
        this.hipotecada = false;
        
    }
    
    void cobrarAlquiler(int coste){
        
        
        
    }
    
    void edificarCasa() {
        
        this.numCasas = this.numCasas + 1;
        
    }
    
    void edificarHotel() {
        
        this.numHoteles = this.numHoteles + 1;
        
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
        return propietario;
    }
    
    int hipotecar() {
        
        int costeHipoteca = this.calcularCosteHipotecar();
        
        this.setHipotecada(true);
        
        return costeHipoteca;
        
    }
    
    int pagarAlquiler() {
       
        int costeAlquiler = this.calcularImporteAlquiler();
        
        this.propietario.modificarSaldo(costeAlquiler);
        
        return costeAlquiler;
        
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
