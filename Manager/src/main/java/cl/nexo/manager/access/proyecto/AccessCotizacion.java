package cl.nexo.manager.access.proyecto;

import java.util.ArrayList;

import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjResultCreaCotOp;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;

public interface AccessCotizacion {

	public ArrayList<ObjComboSelect2ValueInt> getCombo(int id, String place);

	public int setNewCotizacion(String uuid, String nombre);

	public ObjResultCreaCotOp newCotizacion(ObjEstudio estudio);

	public int getIdCotizacionByUid(String uuid);

	public int getCodCotizacionByUid(int id);

	public int getCountCotizacion();
	
	

}
