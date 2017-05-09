package cl.nexo.manager.access.reunion;

import java.util.ArrayList;







import cl.nexo.manager.obj.reunion.ObjAprob;
import cl.nexo.manager.obj.reunion.ObjBitacoraAprob;
import cl.nexo.manager.obj.reunion.ObjReunionKickOff;

public interface AccessReunion {

    public int aceptarReunion(ObjReunionKickOff reu);
    public boolean existeReunionByidOperacion(int operacion);
	ArrayList<ObjBitacoraAprob> getListBitacoraAprobByid(int id_operacion);
	void updateCampo(int oper, String campo);
	void updateScripting(int oper, String campo);
	void updateCalidad(int oper, String campo);
	void updateTabulacion(int oper, String campo);
	
	ObjAprob getAprobByid(int id_operacion);
	void createBitacora(int oper, String login, String observacion);
	public boolean getExistAprobaciones(int operacion);
	void createAprobacion(int oper);
	ObjAprob statusAprobaciones(int operacion);

}
