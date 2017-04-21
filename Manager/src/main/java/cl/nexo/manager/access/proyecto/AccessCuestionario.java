package cl.nexo.manager.access.proyecto;

import cl.nexo.manager.obj.proyecto.ObjEstudioCuestionario;
import cl.nexo.manager.obj.proyecto.ObjResultCreaCotOp;

public interface AccessCuestionario {

	public ObjEstudioCuestionario getCuestionarioByIdOperacion(int id);

	public ObjResultCreaCotOp setCuestionario(ObjEstudioCuestionario cu);

	public int getIdCuestionarioByIdOperacion(int id);

	public ObjResultCreaCotOp updateCuestionario(ObjEstudioCuestionario cu);

	public int deleteCuestionario(int id);

}
