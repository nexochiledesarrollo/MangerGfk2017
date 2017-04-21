package cl.nexo.manager.access.combo.box;

import java.util.ArrayList;

import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueString;

public interface AccessComboBox {

	public ArrayList<ObjComboSelect2ValueString> getCombo(int id,String lang, int depende,String place, int comboDepende);

	public ObjComboSelect2ValueInt getValueComboDetalle(String id, int combo, String lang);

}
