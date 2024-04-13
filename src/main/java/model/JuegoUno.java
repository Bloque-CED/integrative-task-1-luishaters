package model;
import java.util.List;

public class JuegoUno {
    private Mazo mazo;
    public void comenzarNuevaPartida(List<Jugador> jugadores) {
        this.mazo = new Mazo();
        mazo.mezclar();

        // Configuración del mazo de descarte
        PilaDinamica<Carta> mazoDescarte = new PilaDinamica<>();
        Carta cartaInicial = mazo.robarCarta();
        mazoDescarte.push(cartaInicial);


        // Distribución de cartas a los jugadores
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 7; i++) { // Cada jugador recibe 7 cartas al inicio
                Carta carta = mazo.robarCarta();
                jugador.agregarCarta(carta);
            }
        }

        // Determinar el primer jugador que comenzará la partida (podría ser aleatorio)
        int indicePrimerJugador = (int) (Math.random() * jugadores.size());
        // Por ejemplo, el primer jugador en la lista

        // Inicio del juego
        System.out.println("Comienza la partida de Uno!");
        System.out.println("La carta inicial en el mazo de descarte es: \n" + cartaInicial.getColor() + " " + cartaInicial.getNumero());
        System.out.println("El primer jugador en jugar será: " + jugadores.get(indicePrimerJugador).getNombre());

        // Lógica para el turno de los jugadores
        int indiceJugadorActual = indicePrimerJugador;


        while (!juegoTerminado(jugadores)) {
            turnoJugadorActual(jugadores.get(indiceJugadorActual), mazo, mazoDescarte);
            indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
        }

        // Fin del juego (debes implementar la lógica para determinar el ganador, etc.)
        // ...
    }

    private void turnoJugadorActual(Jugador jugadorActual, Mazo mazo, PilaDinamica<Carta> mazoDescarte) {
        System.out.println("Turno de " + jugadorActual.getNombre() + ":");
        System.out.println("Cartas en la mano:");
        for (Carta carta : jugadorActual.getMano()) {
            System.out.println("- " + carta.getColor() + " " + carta.getNumero());
        }

        // Permitir al jugador seleccionar una carta para jugar
        Carta cartaSeleccionada = seleccionarCartaParaJugar(jugadorActual);

        // Verificar si la carta seleccionada es válida para jugar
        if (cartaSeleccionada != null && cartaSeleccionada.equals(mazoDescarte.verCartaSuperior())) {
            // Aplicar los efectos de la carta jugada, si es una carta especial
            if (cartaSeleccionada.getEfecto() != null) {
                // Implementar lógica para aplicar efectos especiales
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
            if (cartaRobada.equals(mazoDescarte.verCartaSuperior())) {
                // Si la carta robada se puede jugar, el jugador la juega
                System.out.println(jugadorActual.getNombre() + " juega la carta robada: " + cartaRobada.getColor() + " " + cartaRobada.getNumero());
                mazoDescarte.agregarCarta(cartaRobada);
                jugadorActual.quitarCarta(cartaRobada);
            } else {
                // Si la carta robada no se puede jugar, se pasa al siguiente jugador
                System.out.println("La carta robada tampoco se puede jugar. " + jugadorActual.getNombre() + " pasa su turno.");
            }
        }
    }

    private boolean juegoTerminado(List<Jugador> jugadores) {
    return false;
    }
    private Carta seleccionarCartaParaJugar(Jugador jugadorActual) {
    return null;
    }

}
