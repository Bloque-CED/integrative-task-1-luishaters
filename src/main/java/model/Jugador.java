package model;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Jugador {
    private String nombre;
    private HashMap<Integer, Carta> mazo;
    private boolean turno;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mazo = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public HashMap<Integer, Carta> getMano() {
        return mazo;
    }

    public void agregarCarta(Carta carta, int posicion) {
        mazo.put(posicion, carta);
    }

    public void quitarCarta(int posicion) {
        mazo.remove(posicion);
    }

    public HashMap<Integer, Carta> getMazo() {
        return mazo;
    }

    public boolean isTurno() {
        boolean turno = false;
        return turno;
    }

    public void setTurno(boolean b) {
        this.turno = b;
    }
}


