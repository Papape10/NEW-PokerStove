package prod.modelo.reproductor;

import prod.modelo.excepciones.EReproductor;
import prod.modelo.reproductor.acciones.I_AccionReproductor;
import prod.modelo.util.LecturaEscritura;

import java.util.List;
import java.util.Stack;

public class Reproductor {

    private List<I_AccionReproductor> acciones;
    private int indiceAccion;
    private List<ManoReproductor> manos;
    private int indiceMano;
    private String archivo;

    /**
     * Constructor vacío, por defecto.
     */
    public Reproductor() {
        iniciaIndices();
        acciones = new Stack<>();
        manos = new Stack<>();
        archivo = null;
    }

    /**
     * Contructor que permite cargar un archivo directamente.
     * @param rutaAlArchivo Path completo al archivo que se va a cargar.
     */
    public Reproductor(String rutaAlArchivo) {
        acciones = new Stack<>();
        iniciaIndices();
        cargarArchivo(rutaAlArchivo);
    }

    /**
     * Cargar un archivo, para que el reproductor tenga
     * @param rutaAlArchivo Path completo al archivo que se va a cargar.
     */
    public void cargarArchivo(String rutaAlArchivo) {
        archivo = rutaAlArchivo;
        manos = LecturaEscritura.leeArchivoPokerStars(archivo);
    }

    public void avanzaAccion() {
        if (puedeAvanzarAccion()) {
            indiceAccion++;
        } else {
            throw new EReproductor("No hay mas acciones en la mano");
        }
    }

    public void retrocedeAccion() {
        if (puedeRetrocederAccion()) {
            indiceAccion--;
        } else {
            throw new EReproductor("No se puede retroceder mas acciones.");
        }
    }

    public boolean puedeAvanzarAccion() {
        return (indiceAccion < (acciones.size()-1));
    }

    public boolean puedeRetrocederAccion() {
        return (indiceAccion > 0);
    }

    public void avanzaMano() {
        if (puedeAvanzarMano()) {
            indiceMano++;
        } else {
            throw new EReproductor("No hay mas manos en el archivo");
        }
    }

    public void retrocedeMano() {
        if (puedeRetrocederMano()) {
            indiceMano--;
        } else {
            throw new EReproductor("No se puede retroceder mas manos");
        }
    }

    public boolean puedeAvanzarMano() {
        return (indiceMano < (manos.size()-1));
    }

    public boolean puedeRetrocederMano() {
        return (indiceMano > 0);
    }

    public EstadoMesa getEstadoActual() {
        return acciones.get(this.indiceAccion).getEstado();
    }

    public String getNombreAccionActual() {
        return acciones.get(this.indiceAccion).getNombreAccion();
    }

    private void iniciaIndices() {
        indiceAccion = 0;
        indiceMano = 0;
    }


    public void resetAcciones() {
        indiceAccion = 0;
    }

    public void resetMano() {
        indiceMano = 0;
    }
}
