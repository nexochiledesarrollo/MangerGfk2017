package cl.nexo.manager.access.reunion;

import cl.nexo.manager.obj.reunion.ObjReunionKickOff;

public interface AccessReunion {

    public int aceptarReunion(ObjReunionKickOff reu);
    public boolean existeReunionByidOperacion(int operacion);

}
