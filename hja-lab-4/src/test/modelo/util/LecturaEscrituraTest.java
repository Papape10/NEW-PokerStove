package test.modelo.util;


import org.junit.Test;
import prod.baraja.Carta;
import prod.modelo.reproductor.EstadoJugador;
import prod.modelo.reproductor.EstadoMesa;
import prod.modelo.reproductor.ManoReproductor;
import prod.modelo.reproductor.acciones.AccionReproductor;
import prod.modelo.reproductor.acciones.I_AccionReproductor;
import prod.modelo.util.LecturaEscritura;
import prod.modelo.util.Utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class LecturaEscrituraTest {

    @Test
    public void testLeeArchivoPokerStarsUnaManoPreflop() {

        List<ManoReproductor> resultado = LecturaEscritura.leeArchivoPokerStars(new File("src/archivos/manos/manoTestUnaManoPreflop.txt"));

        List<I_AccionReproductor> accionesEsperadas = new ArrayList<>();

        // Estado inicial
        List<EstadoJugador> jugadores = new ArrayList<>();
        ArrayList<Carta> cartashero = new ArrayList<>();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.97, 0, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, false, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.03, null), "Dealt to MN-UCM [9h Ks]"));

        // eljudas888 raises 0.04 to 0.06
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.91, 0.06, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, false, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "eljudas888: raises â‚¬0.04 to â‚¬0.06"));

        // kastaracing: folds
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.91, 0.06, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, false, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "kastaracing: folds "));

        // rosaenisthsa: folds
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.91, 0.06, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, true, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, false, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "rosaenithsa: folds "));

        // edgarbermejo: folds
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.91, 0.06, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, true, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, true, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "edgarbermejo: folds "));

        // MN-UCM: folds
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.91, 0.06, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, true, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.01, true, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0.02, true, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.09, null), "MN-UCM: folds "));

        // eljudas888 collected 0.05 from pot
        jugadores.clear();
        cartashero.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 2, 0.0, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, true, true));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0, true, false));
        cartashero.add(new Carta("9h"));
        cartashero.add(new Carta("Ks"));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.78, 0, true, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0, null), "eljudas888 collected â‚¬0.05 from pot"));

        for (int i = 0; i < accionesEsperadas.size(); i++) {
            assertEquals("No se ha parseado la accion correctamente", accionesEsperadas.get(i), resultado.get(0).getAcciones().get(i));
        }

    }

    @Test
    public void testLeeArchivoPokerStarsUnaManoPostFlop() {
        List<ManoReproductor> resultado = LecturaEscritura.leeArchivoPokerStars(new File("src/archivos/manos/manoTestUnaManoPostFlop.txt"));

        List<I_AccionReproductor> accionesEsperadas = new ArrayList<>();

        ArrayList<Carta> cartashero = new ArrayList<>();
        cartashero.add(new Carta("2h"));
        cartashero.add(new Carta("Ks"));

        // Estado inicial
        List<EstadoJugador> jugadores = new ArrayList<>();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.03, null), "Dealt to MN-UCM [2h 7c]"));

    }

    @Test
    public void testLeeArchivoPokerStarsUnaManoFlopTurnRiver() {

    }
}
