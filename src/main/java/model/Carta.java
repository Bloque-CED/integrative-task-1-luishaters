package model;

import java.util.HashMap;
import java.util.Map;

import model.EfectoEspecial;
import model.ColoresCartas;
public class Carta {

    private static int contadorCartas = 0;
    private int id;
    private ColoresCartas color;
    private int numero;
    private EfectoEspecial efecto;
    //el atributo es null cuando no lo contiene, sea el caso de ser comun
    private static Map<Integer, Carta> mazoJuego = new HashMap<>();
    private static Map<Integer, Carta> mazoJugador = new HashMap<>();


    public Carta(int numero, ColoresCartas color) {
        this.numero = numero;
        this.color = color;
        this.id = contadorCartas++;
        mazoJuego.put(id, this); // Añadir la carta al mazo de juego
    }

    public Carta(ColoresCartas colo, EfectoEspecial efecto) {
        // Carta(-1, color);
        this.efecto = efecto;
        this.id = contadorCartas++;
        mazoJuego.put(id, this);
    }

    public ColoresCartas getColor() {
        return color;
    }

    public int getNumero() {
        return numero;
    }

    public EfectoEspecial getEfecto() {
        return efecto;
    }

    public boolean isEspecial() {
        return this.efecto != null;
    }

    public static Carta obtenerCartaDesdeMazoJuego(int id) {
        return mazoJuego.get(id);
    }

    public static Carta obtenerCartaDesdeMazoJugador(int id) {
        return mazoJugador.get(id);
    }
    @Override
    public String toString() {

        String estado = "";

        if (this.isEspecial()) {
            switch (this.efecto) {
                case MAS_DOS:
                    estado = "+2 " + "| " + color;
                    break;
                case SALTAR_TURNO:
                    estado = "Saltar Turno " + "| " + color;
                    break;
                case INVERTIR_SENTIDO:
                    estado = "Invertir Sentido " + "| " + color;
                    break;
                case MAS_CUATRO:
                    estado = "+4 " + "| " + color;
                    break;
                case CAMBIAR_COLOR:
                    estado = "Cambiar Color " + "| " + color;
                    break;

            }

        } else {
            estado = numero + " | " + color;
        }

        return estado;
    }


    public void setColor(ColoresCartas nuevoColor) {
        this.color = color;
    }

    public boolean esCompatible(Carta otraCarta) {
        if (this.esEspecial() || otraCarta.esEspecial() ||
                this.color == otraCarta.color || this.numero == otraCarta.numero) {
            return true;
        } else {
            return false;
        }
    }

    private boolean esEspecial() {
        return this.efecto != null;

    }

}


