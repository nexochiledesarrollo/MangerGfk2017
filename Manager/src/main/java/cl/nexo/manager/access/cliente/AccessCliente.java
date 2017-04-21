package cl.nexo.manager.access.cliente;

import java.util.ArrayList;

import cl.nexo.manager.obj.cliente.ObjCliente;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;

public interface AccessCliente {

	public ArrayList<ObjComboSelect2ValueInt> getCombo(int id, String place);

	public ObjCliente getClienteById(int id);

}
