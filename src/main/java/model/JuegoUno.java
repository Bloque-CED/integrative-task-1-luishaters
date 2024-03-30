package model;
import model.Jugador;
import model.Mazo;
import java.util.List;

public class JuegoUno {
    private Mazo mazo;
    public void comenzarNuevaPartida(List<Jugador> jugadores) {
        // Inicialización del juego
        Mazo mazo = new Mazo();
        mazo.mezclar();

        // Configuración del mazo de descarte
        Pila mazoDescarte = new Pila();
        Carta cartaInicial = mazo.robarCarta();
        mazoDescarte.agregarCarta(cartaInicial);

        // Distribución de cartas a los jugadores
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 7; i++) { // Cada jugador recibe 7 cartas al inicio
                Carta carta = mazo.robarCarta();
                jugador.agregarCarta(carta);
            }
        }

        // Determinar el primer jugador que comenzará la partida (podría ser aleatorio)
        int indicePrimerJugador = 0; // Por ejemplo, el primer jugador en la lista

        // Inicio del juego
        System.out.println("Comienza la partida de Uno!");
        System.out.println("La carta inicial en el mazo de descarte es: " + cartaInicial.getColor() + " " + cartaInicial.getValor());
        System.out.println("El primer jugador en jugar será: " + jugadores.get(indicePrimerJugador).getNombre());

        // Lógica para el turno de los jugadores
        int indiceJugadorActual = indicePrimerJugador;
        boolean juegoTerminado = false;

        while (!juegoTerminado) {
            Jugador jugadorActual = jugadores.get(indiceJugadorActual);

            // Mostrar la carta actual en la parte superior del mazo de descarte
            Carta cartaActual = mazoDescarte.verCartaSuperior();
            System.out.println("Carta actual en el mazo de descarte: " + cartaActual.getColor() + " " + cartaActual.getValor());

            // Lógica para el turno del jugador actual (debes implementarla)
            turnoJugadorActual();

            // Actualizar el índice del jugador actual para el próximo turno
            indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
        }

        // Fin del juego (debes implementar la lógica para determinar el ganador, etc.)
        // ...
    }
     public void turnoJugadorActual(){
         System.out.println("Turno de " + jugadorActual.getNombre() + ":");
         System.out.println("Cartas en la mano:");
         for (Carta carta : jugadorActual.getMano()) {
             System.out.println("- " + carta.getColor() + " " + carta.getNumero());
         }

// Permitir al jugador seleccionar una carta para jugar
         Carta cartaSeleccionada = seleccionarCartaParaJugar(jugadorActual);

// Verificar si la carta seleccionada es válida para jugar
         if (esCartaValida(cartaSeleccionada, mazoDescarte.verCartaSuperior())) {
             // Aplicar los efectos de la carta jugada, si es una carta especial
             if (cartaSeleccionada.getTipoEspecial() != null) {
                 aplicarEfectoCartaEspecial(cartaSeleccionada, jugadores, mazo, mazoDescarte);
             } else {
                 // Agregar la carta al mazo de descarte
                 mazoDescarte.agregarCarta(cartaSeleccionada);
                 // Quitar la carta de la mano del jugador
                 jugadorActual.quitarCarta(cartaSeleccionada);
             }
         } else {
             // El jugador no puede jugar esta carta, debe robar una carta del mazo
             Carta cartaRobada = mazo.robarCarta();
             System.out.println(jugadorActual.getNombre() + " no puede jugar esa carta. Roba una carta del mazo.");
             if (esCartaValida(cartaRobada, mazoDescarte.verCartaSuperior())) {
                 // Si la carta robada se puede jugar, el jugador la juega
                 System.out.println(jugadorActual.getNombre() + " juega la carta robada: " + cartaRobada.getColor() + " " + cartaRobada.getValor());
                 mazoDescarte.agregarCarta(cartaRobada);
                 jugadorActual.quitarCarta(cartaRobada);
             } else {
                 // Si la carta robada no se puede jugar, se pasa al siguiente jugador
                 System.out.println("La carta robada tampoco se puede jugar. " + jugadorActual.getNombre() + " pasa su turno.");
             }
         }
     }

}
