/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package totito;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author saman
 */


public class interfaz extends JFrame {
    private funcionalidad juego;
    private JButton[][] botones;

    public interfaz() {
        juego = new funcionalidad();
        botones = new JButton[3][3];
        iniciar();
    }

    private void iniciar() {
        setTitle("Totito");
        setLayout(new GridLayout(3, 3));
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j] = new JButton("-");
                botones[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                final int fila = i;
                final int columna = j;
                botones[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (juego.hacerMovimiento(fila, columna)) {
                            botones[fila][columna].setText(juego.getJugadorActual());
                            if (juego.haGanado()) {
                                JOptionPane.showMessageDialog(null, "¡El jugador " + juego.getJugadorActual() + " ha ganado!");
                                reiniciarJuego();
                            } else if (juego.tableroLleno()) {
                                JOptionPane.showMessageDialog(null, "¡Empate!");
                                reiniciarJuego();
                            } else {
                                juego.cambiarTurno();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Movimiento inválido. Inténtalo de nuevo.");
                        }
                    }
                });
                add(botones[i][j]);
            }
        }
    }

    private void reiniciarJuego() {
        juego.inicializarTablero();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setText("-");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new interfaz().setVisible(true);
            }
        });
    }
}
