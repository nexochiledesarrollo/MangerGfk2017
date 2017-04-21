package cl.nexo.manager.access.setup;

import java.util.ArrayList;

import cl.nexo.manager.obj.setup.ObjComboDetalle;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;

public interface AccessSetUp {

	public ArrayList<ObjComboSelect2ValueInt> getListBase(String pace);

	public ArrayList<ObjComboDetalle> getListDetalle(int combo, String lang);

	public int deleteDetalleCombo(int id);

}
