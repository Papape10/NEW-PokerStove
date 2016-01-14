package prod.modelo.reproductor;

import prod.modelo.reproductor.acciones.I_AccionReproductor;

import java.util.List;

public class ManoReproductor {

    private List<I_AccionReproductor> acciones;

    public ManoReproductor(List<I_AccionReproductor> acciones) {
        this.acciones = acciones;
    }

    public List<I_AccionReproductor> getAcciones() {
        return acciones;
    }
}
