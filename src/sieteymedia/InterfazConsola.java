package sieteymedia;

import java.util.Scanner;

public class InterfazConsola {
    private final SieteYMedia sm = new SieteYMedia();
    private final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        InterfazConsola ic = new InterfazConsola();
        ic.presentarJuego();
        ic.primeraMano();
        System.out.println("Adiós");
    }

    public void presentarJuego() {
        System.out.println("- El usuario es el jugador y el ordenador la  banca.");
        System.out.println("- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.");
        System.out.println("- las figuras (10-sota, 11-caballo y 12-rey) valen medio punto y, el resto, su valor.");
        System.out.println("- Hay dos turnos de juego: el turno del jugador y el turno de la banca. Se comienza por el turno del jugador.");
        System.out.println("- El jugador va pidiendo cartas a la banca de una en una.");
        System.out.println("- El jugador puede plantarse en cualquier momento.");
        System.out.print("- Si la suma de los valores de las cartas sacadas es superior ");
        System.out.println("a 7 y medio, el jugador 'se pasa de siete y medio' y  pierde.");
        System.out.println("- Si el jugador no se pasa, comienza a sacar cartas la banca y ésta  está obligada a sacar cartas hasta empatar o superar al jugador.");
        System.out.println("- Si la banca consigue empatar o superar la puntuación del jugador 'sin pasarse de siete y medio', gana la banca.");
        System.out.println("- La banca no se puede plantar y tiene que empatar o superar la puntuación del  jugador sin pasarse.");
        System.out.println("- En este proceso puede ocurrir que la banca 'se pase' y entonces pierde la banca y gana el jugador.");
        System.out.println("\nEmpecemos!!!\n");
    }

    public void primeraMano() {
        char opc = 'C';
        System.out.println("Como mínimo recibes una carta, luego puedes decidir si seguir o plantarte");
        while (sm.valorCartas(sm.cartasJugador) < 7.5 && opc == 'C') {
            sm.turnoJugador();
            mostrarCartasJugador();
            if (sm.valorCartas(sm.cartasJugador) > 7.5) {
                System.out.println("Jugador, te has pasado en tu jugada anterior, gana la banca");
                return;
            } else if (sm.valorCartas(sm.cartasJugador) < 7.5) {
                System.out.println("\n¿Pides [C]arta o te [P]lantas?");
                opc = tomarDecision();
                if (opc == 'P') {
                    sm.turnoBanca();
                    mostrarCartasBanca();
                    finalJuego();
                }
            } else {
                sm.turnoBanca();
                mostrarCartasBanca();
                finalJuego();
            }
        }
    }

    public char tomarDecision() {
        return sc.next().trim().toUpperCase().charAt(0);
    }

    public void mostrarCartasJugador() {
        System.out.println("Éstas son tus cartas jugador:");
        int i = 0;
        while (sm.mostrarCartas(sm.cartasJugador)[i] != null) {
            System.out.print("\t" + sm.cartasJugador[i]);
            i++;
        }
        double valor = sm.valorCartas(sm.cartasJugador);
        System.out.println("\n\tValor de cartas: " + valor);
    }

    public void mostrarCartasBanca() {
        System.out.println("\n\nTurno de banca ...");
        System.out.println("Éstas son mis cartas:");
        int i = 0;
        while (sm.mostrarCartas(sm.cartasBanca)[i] != null) {
            System.out.print("\t" + sm.cartasBanca[i]);
            i++;
        }
        System.out.println("\nValor de  mis cartas(banca): " + sm.valorCartas(sm.cartasBanca));
    }

    public void finalJuego() {
        if (sm.valorCartas(sm.cartasBanca) > 7.5) {
            System.out.println("me pasé, ganas tú,jugador");
        } else {
            System.out.println("Gana la banca");
        }
    }
}

