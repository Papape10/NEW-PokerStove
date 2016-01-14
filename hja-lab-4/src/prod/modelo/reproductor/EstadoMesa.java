package prod.modelo.reproductor;

import prod.baraja.Carta;

import java.util.List;

public class EstadoMesa {

    private List<EstadoJugador> jugadores;
    private double pot;
    private List<Carta> mesa;
    private int button;

    public EstadoMesa(List<EstadoJugador> jugadores, double pot, List<Carta> mesa, int button) {
        this.jugadores = jugadores;
        this.pot = pot;
        this.mesa = mesa;
        this.button = button;
    }

    public List<EstadoJugador> getJugadores() {
        return jugadores;
    }

    public double getPot() {
        return pot;
    }

    public List<Carta> getMesa() {
        return mesa;
    }

    public int getButton() {
        return button;
    }
}
