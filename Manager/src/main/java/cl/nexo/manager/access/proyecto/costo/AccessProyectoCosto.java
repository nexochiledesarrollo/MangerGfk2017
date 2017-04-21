package cl.nexo.manager.access.proyecto.costo;

import java.util.ArrayList;

import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;

public interface AccessProyectoCosto {

	public ArrayList<ObjComboSelect2ValueInt> getCombo(int tipo, int id);

}
