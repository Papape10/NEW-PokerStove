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
        cartashero.add(new Carta("7c"));

        ArrayList<Carta> cartasmesa = new ArrayList<>();

        // Estado inicial
        List<EstadoJugador> jugadores = new ArrayList<>();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.44, 0, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.03, null), "Dealt to MN-UCM [2h 7c]"));

        // kastaracing: calls 0.02
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.42, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.81, 0, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, false, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.05, null), "kastaracing: calls â‚¬0.02"));

        // rosaenitsha: calls 0.02
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.42, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.44, 0.0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, false, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.07, null), "rosaenithsa: calls â‚¬0.02"));

        // edgarbermejo: raises 0.09 to 0.11
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.42, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, false, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.18, null), "edgarbermejo: raises â‚¬0.09 to â‚¬0.11"));

        // MN-UCM: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.98, 0.02, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.42, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        //Primera accion leida correctamente
        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.18, null), "MN-UCM: folds "));

        // eljudas: calls 0.09
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.42, 0.02, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.27, null), "eljudas888: calls â‚¬0.09"));

        // kastaracing: calls 0.09
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, false, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.36, null), "kastaracing: calls â‚¬0.09"));

        // rosaenithsa: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.36, null), "rosaenithsa: folds "));

        // *** FLOP *** [4c 7s Ks]
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        cartasmesa.add(new Carta("4c"));
        cartasmesa.add(new Carta("7s"));
        cartasmesa.add(new Carta("Ks"));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.36, cartasmesa), "*** FLOP *** [4c 7s Ks]"));

        // eljufas888: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.36, cartasmesa), "eljudas888: checks "));

        // kastaracing: checks
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.3299999999999998, 0.11, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.36, cartasmesa), "kastaracing: checks "));

        // edgarbermejo: bets 0.18
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, false, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.15, 0.29, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.54, cartasmesa), "edgarbermejo: bets â‚¬0.18"));

        // eljudas888: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, false, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.15, 0.29, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.54, cartasmesa), "eljudas888: folds "));

        // kastaracing: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0.11, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0.11, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0.02, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.15, 0.29, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0.01, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0.54, cartasmesa), "kastaracing: folds "));

        // kastaracing: folds
        jugadores.clear();
        jugadores.add(new EstadoJugador("eljudas888", null, 1.89, 0, true, false));
        jugadores.add(new EstadoJugador("kastaracing", null, 1.3299999999999998, 0, true, false));
        jugadores.add(new EstadoJugador("rosaenithsa", null, 0.79, 0, true, false));
        jugadores.add(new EstadoJugador("edgarbermejo", null, 1.6799999999999997, 0, false, true));
        jugadores.add(new EstadoJugador("MN-UCM", new ArrayList<Carta>(cartashero), 0.77, 0, true, false));

        accionesEsperadas.add(new AccionReproductor(new EstadoMesa(new ArrayList<EstadoJugador>(jugadores), 0, cartasmesa), "edgarbermejo collected â‚¬0.35 from pot"));

        for (int i = 0; i < accionesEsperadas.size(); i++) {
            assertEquals("No se ha parseado la accion correctamente", accionesEsperadas.get(i), resultado.get(0).getAcciones().get(i));
        }
    }

    @Test
    public void testLeeArchivoPokerStarsUnaManoFlopTurnRiver() {



    }
}
