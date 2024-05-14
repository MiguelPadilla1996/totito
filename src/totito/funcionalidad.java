/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package totito;

import java.util.Scanner;

/**
 *
 * @author saman
 */
public class funcionalidad {

    private String [][] tablero;
    private String jugadorActual;

    public funcionalidad() {
        tablero = new String[3][3];
        jugadorActual = "|X|";
    }

    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = "|-|";
            }
        }
    }

    public void juega() {
        Scanner scanner = new Scanner(System.in);
        boolean juegoActivo = true;

        do {
            juegoActivo = true; 
            inicializarTablero();
            while (juegoActivo) {
                mostrarTablero();
                System.out.println("Turno del jugador " + jugadorActual);
                System.out.print("Ingrese fila y columna (por ejemplo, 1 para la primera fila y 1 la primera columna, Dejando un espacio en cada numero): ");
                int fila = scanner.nextInt();
                int columna = scanner.nextInt();

                if (fila >= 1 && fila <= 3 && columna >= 1 && columna <= 3 && tablero[fila - 1][columna - 1] == "|-|") {
                    tablero[fila - 1][columna - 1] = jugadorActual;

                    if (haGanado()) {
                        mostrarTablero();
                        System.out.println("¡El jugador " + jugadorActual + " ha ganado!");
                        juegoActivo = false;

                    } else if (tableroLleno()) {
                        mostrarTablero();
                        System.out.println("¡Empate!");
                        juegoActivo = false;

                    } else {
                        cambiarTurno();
                    }
                } else {
                    System.out.println("Movimiento inválido. Inténtalo de nuevo.");
                }

            }
            System.out.print("¿Quieres jugar otra vez? (Si/No): ");
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("Si")) {
                juegoActivo = false;
            } else {
                juegoActivo = true;
            }
        } while (juegoActivo);
        scanner.close();
        System.out.println("¡Gracias por jugar!");
    }

    private void mostrarTablero() {
        System.out.println("Tablero:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void cambiarTurno() {
        if (jugadorActual == "|X|") {
            jugadorActual = "|O|";
        } else {
            jugadorActual = "|X|";
        }
    }

    private boolean haGanado() {

        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != "|-|" && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] != "|-|" && tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
                return true;
            }
        }

        if (tablero[0][0] != "|-|" && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true;
        }
        if (tablero[0][2] != "|-|" && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true;
        }
        return false;
    }

    private boolean tableroLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == "|-|") {
                    return false;
                }
            }
        }
        return true;
    }

}
