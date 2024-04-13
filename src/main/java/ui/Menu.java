package ui;

import model.JuegoUno;
import java.util.Scanner;

import static model.JuegoUno.comenzarNuevaPartida;


public class Menu {
    private Scanner scanner;
    private JuegoUno juego;

    public Menu() {
        this.juego = new JuegoUno();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ui.Menu menu = new ui.Menu();
        menu.mostrarMenu();
    }
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("Bienvenido al juego de Uno");
            System.out.println("1. Comenzar nueva partida");
            System.out.println("2. Cargar partida guardada");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Lógica para comenzar una nueva partida
                    System.out.println("Comenzando nueva partida...");
                    Object listaJugadores;
                    comenzarNuevaPartida(); // Llamar al método en la instancia
                    break;
                case 2:
                    // Lógica para cargar una partida guardada
                    System.out.println("Cargando partida guardada...");
                    break;
                case 3:
                    System.out.println("Gracias por jugar. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 3);
    }

}


