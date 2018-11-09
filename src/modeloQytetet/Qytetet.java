/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloQytetet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
                    this.setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                
        Casilla casilla = this.obtenerCasillaJugadorActual();
        
        boolean tengoPropietario = casilla.tengoPropietario();
        
        if (estadoJuego != EstadoJuego.ALGUNJUGADORENBANCARROTA)
            if (tengoPropietario)
                this.setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
            else
                this.setEstadoJuego(EstadoJuego.JA_PUEDECOMPRAROGESTIONAR);
    
    }
    
    void actuarSiEnCasillaNoEdificable(){
        
        this.setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        Casilla casillaActual = jugadorActual.getCasillaActual();
        
        if (casillaActual.getTipo() == TipoCasilla.IMPUESTO)
            jugadorActual.pagarImpuesto();
        else{
            if (casillaActual.getTipo() == TipoCasilla.JUEZ)
                this.encarcelarJugador();
            else if (casillaActual.getTipo() == TipoCasilla.SORPRESA){
                this.cartaActual = mazo.remove(0);
                this.setEstadoJuego(EstadoJuego.JA_CONSORPRESA);
            }
        }
        
    }
    
    public void aplicarSorpresa(){
        
        this.setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);

        switch (this.cartaActual.getTipo()){
            case SALIRCARCEL:
                this.jugadorActual.setCartaLibertad(cartaActual);
                break;
            case PAGARCOBRAR:
                this.jugadorActual.modificarSaldo(this.cartaActual.getValor());
                if (this.jugadorActual.getSaldo() < 0)
                    this.setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                break;
            case IRACASILLA:
                int valor = this.cartaActual.getValor();
                boolean casillaCarcel = this.tablero.esCasillCarcel(valor);
                if (casillaCarcel)
                    this.encarcelarJugador();
                else
                    this.mover(valor);
                break;
            case PORCASAHOTEL:
                int cantidad = this.cartaActual.getValor();
                int numeroTotal = this.jugadorActual.cuantasCasasHotelesTengo();
                this.jugadorActual.modificarSaldo(cantidad*numeroTotal);
                if (this.jugadorActual.getSaldo() < 0)
                    this.setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                break;
            case PORJUGADOR:
                Jugador jugador = null;
                for (int i = 0; i < MAX_JUGADORES; i++)
                    jugador = this.jugadores.get((i + 1) % MAX_JUGADORES);
                    if (jugador != this.jugadorActual)
                        jugador.modificarSaldo(cartaActual.getValor());
                    if (jugador.getSaldo() < 0)
                        this.setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                    this.jugadorActual.modificarSaldo(-this.cartaActual.getValor());
                    if (this.jugadorActual.getSaldo() < 0)
                        this.setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCARROTA);
                break;
        }
                    
    }
    
    public boolean cancelarHipoteca(int numeroCasilla){
        
        boolean cancelar;
        
        TituloPropiedad titulo = this.tablero.getCasillas().get(numeroCasilla).getTitulo();
        
        cancelar = this.jugadorActual.cancelarHipoteca(titulo);
        
        this.estadoJuego = EstadoJuego.JA_PUEDEGESTIONAR;
        
        return cancelar;
         
    }
    
    public boolean comprarTituloPropiedad(){
        
        boolean comprado = false;
        
        int costeCompra = this.jugadorActual.getCasillaActual().getCoste();
        
        TituloPropiedad titulo = null;
                
        if (costeCompra < this.jugadorActual.getSaldo()){
            titulo = this.jugadorActual.getCasillaActual().asignarPropietario(jugadorActual);
            comprado = true;
            this.jugadorActual.getPropiedades().add(titulo);
            this.jugadorActual.modificarSaldo(-costeCompra);
        }
        
        if (comprado)
            this.setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        return comprado;
        
    }
    
    public boolean edificarCasa(int numeroCasilla){
        
        boolean edificada = false;
        
        Casilla casilla = this.tablero.obtenerCasillaNumero(numeroCasilla);
        
        TituloPropiedad titulo = casilla.getTitulo();
        
        edificada = this.jugadorActual.edificarCasa(titulo);
        
        if (edificada)
            this.setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        return edificada;
        
    }
    
    public boolean edificarHotel(int numeroCasilla){
        
        boolean edificado = false;
        
        Casilla casilla = this.tablero.obtenerCasillaNumero(numeroCasilla);
        
        TituloPropiedad titulo = casilla.getTitulo();
        
        edificado = this.jugadorActual.edificarHotel(titulo);
        
        if (edificado)
            this.setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        return edificado;        
        
    }
    
    private void encarcelarJugador(){
        
        Casilla casillaCarcel = null;
        
        Sorpresa carta = null;
        
        if (!this.jugadorActual.tengoCartaLibertad()){
            casillaCarcel = this.tablero.getCarcel();
            this.jugadorActual.irACarcel(casillaCarcel);
            this.jugadorActual.setCasillaActual(casillaCarcel);
            this.jugadorActual.setEncarcelado(true);
            this.setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
        }
        else{
            carta = this.jugadorActual.devolverCartaLibertad();
            this.mazo.add(carta);
            this.setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
        
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
        
        Casilla casilla = this.tablero.obtenerCasillaNumero(numeroCasilla);
        
        TituloPropiedad titulo = casilla.getTitulo();
        
        this.jugadorActual.hipotecarPropiedad(titulo);
        
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
        juego.salidaJugadores();
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
        
        int resultado;
        
        if (metodo == MetodoSalirCarcel.TIRANDODADO){
            resultado = this.tirarDado();
            if (resultado >= 5)
                this.jugadorActual.setEncarcelado(false);
        }
        else if (metodo == MetodoSalirCarcel.PAGANDOLIBERTAD){
            this.jugadorActual.pagarLibertad(PRECIO_LIBERTAD);
        }
        
        boolean libre = this.jugadorActual.getEncarcelado();
        
        if (libre)
            this.setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
        else
            this.setEstadoJuego(EstadoJuego.JA_PREPARADO);
        
        return libre;
        
    }
    
    public void jugar(){
        
        int tirada = this.tirarDado();
        
        Casilla aux = this.tablero.obtenerCasillaFinal(jugadorActual.getCasillaActual(), tirada);
        
        this.mover(aux.getNumeroCasilla());
        
    }
    
    void mover(int numCasillaDestino){
        
        Casilla casillaInicial = this.jugadorActual.getCasillaActual();
        
        Casilla casillaFinal = this.tablero.obtenerCasillaFinal(casillaInicial, numCasillaDestino);
        
        this.jugadorActual.setCasillaActual(casillaFinal);
        
        if (numCasillaDestino < casillaInicial.getNumeroCasilla())
            this.jugadorActual.modificarSaldo(SALDO_SALIDA);
        
        if (casillaFinal.soyEdificable())
            this.actuarSiEnCasillaEdificable();
        else
            this.actuarSiEnCasillaNoEdificable();
        
    }
    
    public Casilla obtenerCasillaJugadorActual(){
        
        return this.jugadorActual.getCasillaActual();
        
    }
    
    public ArrayList<Casilla> obtenerCasillasTablero(){
        
        return this.tablero.getCasillas();
        
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
        
        ArrayList<Jugador> aux = this.jugadores;
        
        Collections.sort(aux);
        
        System.out.println(aux);
    }
    
    public int obtenerSaldoJugadorActual(){
        
        int saldo = this.jugadorActual.getSaldo();
        
        return saldo;
        
    }
    
    private void salidaJugadores(){
        
        for (Jugador i:jugadores)
            i.setCasillaActual(tablero.obtenerCasillaNumero(0));
        
        estadoJuego = EstadoJuego.JA_PREPARADO;
        
        Random numero_aleatorio = new Random();
        
        int jugador = (int) (numero_aleatorio.nextDouble() * this.jugadores.size() + 1);
        
        this.jugadorActual = this.jugadores.get(jugador);
        
    }
    
    private void setCartaActual(Sorpresa cartaActual){
        
        this.cartaActual = cartaActual;
        
    }
    
    public void setEstadoJuego(EstadoJuego estadoJuego){
        this.estadoJuego = estadoJuego;
    }
    
    public void siguienteJugador(){
        
        int pos = jugadores.indexOf(jugadorActual);
        
        jugadorActual = jugadores.get((pos + 1) % jugadores.size());
        
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
    
    public void venderPropiedad(int numeroCasilla){
        
        Casilla casilla = this.tablero.obtenerCasillaNumero(numeroCasilla);
        
        this.jugadorActual.venderPropiedad(casilla);
        
        TituloPropiedad titulo = casilla.getTitulo();
        
        this.jugadorActual.eliminarDeMisPropiedades(titulo);
        
        this.setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
    }
    
    
}
