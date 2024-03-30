package model;
import model.Carta;

import java.util.Queue;
public class Cola {
    private Queue<Carta> cartas;

    public Cola(){
        this.cartas = new Queue<>();
    }

    public void agregarCarta(Carta carta){
        cartas.add(carta);
    }

    public Carta sacarCarta(){
        return cartas.poll();
    }

    public Carta verPrimerCarta(){
        return cartas.peek();
    }

    public boolean estaVacia(){
        return cartas.isEmpty();
    }

}
