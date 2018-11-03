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
public class Qytetet {
    
    private static final Qytetet instance = new Qytetet();
    ArrayList<Sorpresa> mazo = new ArrayList();
    private Tablero tablero = new Tablero();
    public static final int MAX_JUGADORES = 4;
    static final int NUM_SORPRESAS = 10;
    public static final int NUM_CASILLAS = 20;
    static final int PRECIO_LIBERTAD = 200;
    static final int SALDO_SALIDA = 1000;
    private Sorpresa cartaActual;
    private Dado dado = Dado.getInstance();
    private Jugador jugadorActual;
    private ArrayList<Jugador> jugadores = new ArrayList();
    private EstadoJuego estadoJuego = null;
    
    private Qytetet(){}
    
    public static Qytetet getInstance() {
        return instance;
    }

    public Tablero getTablero() {
        return tablero;
    }
    
    void actuarSiEnCasillaEdificable(){
        
        boolean deboPagar = jugadorActual.deboPagarAlquiler();
        
        if (deboPagar)
            jugadorActual.pagarAlquiler();
                if (jugadorActual.getSaldo() <= 0)
                    estadoJuego = EstadoJuego.ALGUNJUGADORENBANCARROTA;
                
        Casilla casilla = this.obtenerCasillaJugadorActual();
        
        boolean tengoPropietario = casilla.tengoPropietario();
        
        
        
    }
    
    void actuarSiEnCasillaNoEdificable(){
        
    }
    
    public void aplicarSorpresa(){
        
    }
    
    public boolean cancelarHipoteca(int numeroCasilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean comprarTituloPropiedad(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean edificarCasa(int numeroCasilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public boolean edificarHotel(int numeroCasilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    private void encarcelarJugador(){
        
    }
    
    public Sorpresa getCartaActual() {
        return cartaActual;
    }

    Dado getDado() {
        return dado;
    }

    Jugador getJugador_Actual() {
        return jugadorActual;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    ArrayList<Sorpresa> getMazo(){
        return mazo;
    }
    
    public int getValorDado(){
        
        return this.dado.getValor();
        
    }
    
    public void hipotecarPropiedad(int numeroCasilla){
        
    }
    
    private void inicializarCartasSorpresa(){
        mazo.add(new Sorpresa ("Te hemos pillado con chanclas y calcetines, lo sentimos, ¡debes ir a la carcel!", tablero.getCarcel().getNumeroCasilla(), TipoSorpresa.IRACASILLA));

        //2 cartas de IRACASILLA, cuyo valor es el número de la casilla a dónde vas.
        mazo.add(new Sorpresa ("Avance hasta la casilla X.", 8, TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Avance hasta la casilla Y", 18, TipoSorpresa.IRACASILLA));
        
        //2 cartas de PAGARCOBRAR, cuyo valor es la cantidad de dinero a pagar si es negativo o a cobrar si es positivo.
        
        mazo.add(new Sorpresa ("Le ha tocado la lotería, reciba 300.", 300, TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Multa por exceso de velocidad, pague 175.", -175, TipoSorpresa.PAGARCOBRAR));
        
        //2 cartas PORCASAHOTEL, cuyo valor es la cantidad de dinero a pagar o recibir por cada casa y hotel.
        
        mazo.add(new Sorpresa ("Cobre 100 por cada casa y por cada hotel.", 100, TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Pague 100 por cada casa y por cada hotel.", -100, TipoSorpresa.PORCASAHOTEL));
        
        //2 cartas PORJUGADOR, cuyo valor es la cantidad que se debe pagar o recibir del resto de jugadores.
        
        mazo.add(new Sorpresa ("Cobre 100 de cada jugador", 100, TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Pague 100 a cada jugador", -100, TipoSorpresa.PORJUGADOR));
        
        //1 carta de SalirCarcel, con la que el jugador podrá salir de la cárcel y cuyo valor no es aplicable (0 por defecto).
        
        mazo.add(new Sorpresa ("Salga de la carcel cuando quiera con esta carta.", 0, TipoSorpresa.SALIRCARCEL));
    }
    
    public void inicializarJuego(ArrayList<String> nombres){
        Qytetet juego = Qytetet.getInstance();
        juego.inicializarJugadores(nombres);
        juego.inicializarTablero();
        juego.inicializarCartasSorpresa();
    }
    
    private void inicializarJugadores(ArrayList<String> nombres){
        
        Jugador jugador;
        
        for (String i: nombres){
            jugador = new Jugador(i);
            jugadores.add(jugador);
        }
    }
    
    private void inicializarTablero(){
        this.tablero = new Tablero();
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public void jugar(){
        
        int tirada = this.tirarDado();
        
        Casilla aux = this.tablero.obtenerCasillaFinal(jugadorActual.getCasillaActual(), tirada);
        
        this.mover(aux.getNumeroCasilla());
        
    }
    
    private void mover(int numeroCasillaDestino){
        
    }
    
    public Casilla obtenerCasillaJugadorActual(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public ArrayList<Casilla> obtenerCasillasTablero(){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugador(){
        
        ArrayList<Integer> casillas = null;
        
        ArrayList<TituloPropiedad> aux = jugadorActual.getPropiedades();
        
        for (Casilla i:tablero.getCasillas())
            if (aux.contains(i.getTitulo()))
                casillas.add(i.getNumeroCasilla());
        
        return casillas;
        
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugadorSegunEstadoHipoteca(boolean estadoHipoteca){
       
        ArrayList<Integer> casillas = null;
        
        ArrayList<TituloPropiedad> aux = jugadorActual.obtenerPropiedades(estadoHipoteca);
        
        for (Casilla i:tablero.getCasillas())
            if (aux.contains(i.getTitulo()))
                casillas.add(i.getNumeroCasilla());
        
        return casillas;
        
    }
    
    public void obtenerRanking(){
        
        jugadores.sort();
    }
    
    public int obtenerSaldoJugadorActual(){
        
        int saldo = this.jugadorActual.getSaldo();
        
        return saldo;
        
    }
    
    private void salidaJugadores(){
        
        for (Jugador i:jugadores)
            i.setCasillaActual(tablero.obtenerCasillaNumero(0));
        
        estadoJuego = EstadoJuego.JA_PREPARADO;
        
    }
    
    private void setCartaActual(Sorpresa cartaActual){
        
    }
    
    public void siguienteJugador(){
        
        int pos = jugadores.indexOf(jugadorActual);
        
        jugadorActual = jugadores.get((pos + 1) % MAX_JUGADORES);
        
        if (jugadorActual.getEncarcelado())
            estadoJuego = EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD;
        else
            estadoJuego = EstadoJuego.JA_PREPARADO;
        
    }
    
    private int tirarDado(){
        
        int tirada;
        
        tirada = this.dado.tirar();
        
        return tirada;
        
    }
    
    public boolean venderPropiedad(int numeroCasilla){
        throw new UnsupportedOperationException("Sin implementar");
    }
    
    
}
