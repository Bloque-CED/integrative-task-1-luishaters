package model;

import java.util.Stack;

public class MazoDescarte {

    private static final Stack<Carta> mazoDescarte = new Stack<>();

    public static void agregarCarta(Carta cartaSeleccionada) {
        mazoDescarte.push(cartaSeleccionada);
    }

    public static Carta verCartaSuperior() {
        if (mazoDescarte.isEmpty()) {
            return null;
        } else {
            return mazoDescarte.peek();
        }
    }
}
