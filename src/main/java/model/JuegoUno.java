package model;
import java.util.*;


public class JuegoUno {
    private static Mazo mazo;

    public static void comenzarNuevaPartida() {
        Scanner scanner = new Scanner(System.in);

        // Pedir el número de jugadores
        System.out.print("Ingrese el número de jugadores: ");
        int numJugadores = scanner.nextInt();
        scanner.nextLine();

        // Crear la lista de jugadores y pedir sus nombres
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < numJugadores; i++) {
            System.out.print("Nombre del jugador " + (i + 1) + ": ");
            String nombreJugador = scanner.nextLine();
            jugadores.add(new Jugador(nombreJugador));
        }
        iniciarPartida(jugadores);
    }
    public static void iniciarPartida(List<Jugador> jugadores) {
        mazo = new Mazo();
        mazo.mezclar();

        // Configuración del mazo de descarte
        PilaDinamica<Carta> mazoDescarte = new PilaDinamica<>();
        Carta cartaInicial = mazo.robarCarta();
        mazoDescarte.push(cartaInicial);


        // Distribución de cartas a los jugadores
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 7; i++) { // Cada jugador recibe 7 cartas al inicio
                Carta carta = mazo.robarCarta();
                jugador.agregarCarta(carta, i);
            }
        }
        // Determinar el primer jugador que comenzará la partida (podría ser aleatorio)
        int indicePrimerJugador = (int) (Math.random() * jugadores.size());
        // Por ejemplo, el primer jugador en la lista

        // Inicio del juego
        System.out.println("Comienza la partida de Uno!");
        System.out.println("La carta inicial del juego es: \n" + cartaInicial.getColor() + " " + cartaInicial.getNumero());
        System.out.println("El primer jugador en jugar será: " + jugadores.get(indicePrimerJugador).getNombre());

        // Lógica para el turno de los jugadores
        int indiceJugadorActual = indicePrimerJugador;

        while (!juegoTerminado(jugadores)) {
            turnoJugadorActual(jugadores.get(indiceJugadorActual), mazo, mazoDescarte, jugadores);
            indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
        }
        System.out.println("¡La partida ha terminado!");
    }

    private static boolean juegoTerminado(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            if (jugador.getMano().isEmpty()) {
                System.out.println("¡El jugador " + jugador.getNombre() + " ha ganado la partida!");
                return true;
            }
        }
        return false;
    }
    private static void turnoJugadorActual(Jugador jugadorActual, Mazo mazo, PilaDinamica<Carta> mazoDescarte, List<Jugador> jugadores) {
        Carta cartaSuperior = mazoDescarte.verCartaSuperior();
        // Obtener el mazo del jugador actual
        HashMap<Integer, Carta> mazoJugador = jugadorActual.getMazo();

        // Mostrar las cartas en la mano del jugador actual
        System.out.println("Cartas en el mazo de " + jugadorActual.getNombre() + ":");
        for (Map.Entry<Integer, Carta> entry : mazoJugador.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().toString());
        }
        
        boolean puedeJugar = false;
        while (!puedeJugar) {
            // Pedir al jugador que elija una carta para jugar
            Scanner scanner = new Scanner(System.in);
            int opcion;
            do {
                System.out.print("Seleccione el número de la carta que desea jugar (0 para robar una carta): ");
                opcion = scanner.nextInt();
                if (opcion < 0 || opcion > mazoJugador.size()) {
                    System.out.println("Opción no válida. Por favor, seleccione un número válido.");
                }
            } while (opcion < 0 || opcion > mazoJugador.size());

            if (opcion == 0) {
                // El jugador decide robar una carta
                Carta cartaRobada = mazo.robarCarta();
                System.out.println(jugadorActual.getNombre() + " roba una carta del mazo.");
                if (cartaRobada.equals(cartaSuperior)) {
                    // Si la carta robada se puede jugar, el jugador la juega
                    System.out.println(jugadorActual.getNombre() + " juega la carta robada: " + cartaRobada.toString());
                    mazoDescarte.agregarCarta(cartaRobada);
                    jugadorActual.quitarCarta(cartaRobada.getNumero());
                    puedeJugar = true;
                } else {
                    // Si la carta robada no se puede jugar, se mantiene en la mano del jugador
                    System.out.println("La carta robada tampoco se puede jugar. Se mantiene en la mano de " + jugadorActual.getNombre() + ".");
                    mazoJugador = null;
                    jugadorActual.agregarCarta(cartaRobada, mazoJugador.size() + 1);
                }
            } else {
                // El jugador elige jugar una carta
                Carta cartaSeleccionada = mazoJugador.get(opcion - 1);
                System.out.println("El jugador decide jugar la carta: " + cartaSeleccionada.toString());

                // Verificar si la carta seleccionada es válida para jugar
                if (cartaSeleccionada.equals(cartaSuperior)) {
                    if (cartaSeleccionada.isEspecial()) {
                        aplicarEfectoEspecial(cartaSeleccionada, jugadores, mazo, mazoDescarte, jugadorActual);
                    } else {
                        // Agregar la carta al mazo de descarte
                        mazoDescarte.agregarCarta(cartaSeleccionada);
                        // Quitar la carta de la mano del jugador
                        jugadorActual.quitarCarta(opcion);
                        puedeJugar = true;
                    }
                } else {
                    // El jugador no puede jugar esta carta, debe robar una carta del mazo
                    System.out.println(jugadorActual.getNombre() + " no puede jugar esa carta. Intenta de nuevo.");

                }
            }
        }

    }
    private static Jugador obtenerSiguienteJugador(List<Jugador> jugadores, Jugador jugadorActual) {
        int indiceJugadorActual = jugadores.indexOf(jugadorActual);
        int indiceSiguienteJugador = (indiceJugadorActual + 1) % jugadores.size();
        return jugadores.get(indiceSiguienteJugador);
    }

    private static void avanzarTurno(List<Jugador> jugadores) {
        // Obtener el índice del jugador actual
        int indiceJugadorActual = 0;
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).isTurno()) {
                indiceJugadorActual = i;
                break;
            }
        }

        // Desactivar el turno del jugador actual
        jugadores.get(indiceJugadorActual).setTurno(false);

        // Avanzar al siguiente jugador
        int indiceSiguienteJugador = (indiceJugadorActual + 1) % jugadores.size();

        // Activar el turno del siguiente jugador
        jugadores.get(indiceSiguienteJugador).setTurno(true);
    }

    public static void aplicarEfectoEspecial(Carta carta, List<Jugador> jugadores, Mazo mazo, PilaDinamica<Carta> mazoDescarte, Jugador jugadorActual) {
        switch (carta.getEfecto()) {
            case CAMBIAR_COLOR:
                // Permitir al jugador elegir el nuevo color
                Scanner scanner = null;
                ColoresCartas nuevoColor = elegirNuevoColor(scanner);
                System.out.println("El jugador decide cambiar el color a " + nuevoColor);
                // Actualizar el color del mazo de descarte
                mazoDescarte.verCartaSuperior().setColor(nuevoColor);
                break;
            case MAS_DOS:
                // El siguiente jugador debe robar 2 cartas y perder su turno
                Jugador siguienteJugador = obtenerSiguienteJugador(jugadores, jugadorActual);
                System.out.println("El siguiente jugador, " + siguienteJugador.getNombre() + ", debe robar 2 cartas y perder su turno.");
                for (int i = 0; i < 2; i++) {
                    Carta cartaRobada = mazo.robarCarta();
                    System.out.println(siguienteJugador.getNombre() + " roba una carta del mazo.");
                    siguienteJugador.agregarCarta(cartaRobada, cartaRobada.getNumero());
                }
                // El siguiente jugador pierde su turno
                avanzarTurno(jugadores);
                break;
            case INVERTIR_SENTIDO:
                // Invertir el orden de juego
                Collections.reverse(jugadores);
                System.out.println("El sentido del juego ha sido invertido.");
                break;
            case SALTAR_TURNO:
                // Hace que el siguiente jugador pierda su turno
                Jugador siguiente = obtenerSiguienteJugador(jugadores, null);
                System.out.println("El siguiente jugador, " + siguiente.getNombre() + ", pierde su turno.");
                avanzarTurno(jugadores);
                break;
            case MAS_CUATRO:
                // El siguiente jugador debe robar 4 cartas y perder su turno
                Jugador proximoJugador = obtenerSiguienteJugador(jugadores, null);
                System.out.println("El siguiente jugador, " + proximoJugador.getNombre() + ", debe robar 4 cartas y perder su turno.");
                for (int i = 0; i < 4; i++) {
                    Carta cartaRobada = mazo.robarCarta();
                    System.out.println(proximoJugador.getNombre() + " roba una carta del mazo.");
                    proximoJugador.agregarCarta(cartaRobada, carta.getNumero());
                }
                // El siguiente jugador pierde su turno
                avanzarTurno(jugadores);
                break;
        }
    }
    private static ColoresCartas elegirNuevoColor(Scanner scanner) {
        ColoresCartas nuevoColor = null;
        do {
            System.out.println("Seleccione el nuevo color (1. Rojo, 2. Verde, 3. Amarillo, 4. Azul): ");
            int opcionColor = scanner.nextInt();
            switch (opcionColor) {
                case 1:
                    nuevoColor = ColoresCartas.ROJO;
                    break;
                case 2:
                    nuevoColor = ColoresCartas.VERDE;
                    break;
                case 3:
                    nuevoColor = ColoresCartas.AMARILLO;
                    break;
                case 4:
                    nuevoColor = ColoresCartas.AZUL;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione un número válido.");
                    break;
            }
        } while (nuevoColor == null);
        return nuevoColor;
    }

}
