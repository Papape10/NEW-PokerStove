package prod.modelo.reproductor;

import prod.baraja.Carta;

import java.util.List;

public class EstadoJugador {

    private List<Carta> cartas;
    private double stack;
    private double bet;
    private boolean hasFolded;

    public EstadoJugador(List<Carta> cartas, double stack, double bet, boolean hasFolded) {
        this.cartas = cartas;
        this.stack = stack;
        this.bet = bet;
        this.hasFolded = hasFolded;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public double getStack() {
        return stack;
    }

    public double getBet() {
        return bet;
    }

    public boolean hasFolded() {
        return hasFolded;
    }
}
