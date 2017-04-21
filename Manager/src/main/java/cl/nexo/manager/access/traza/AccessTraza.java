package cl.nexo.manager.access.traza;

import java.sql.Connection;
import java.util.ArrayList;

import cl.nexo.manager.obj.traza.ObjTrazaManager;
import cl.nexo.manager.obj.traza.ObjTrazaManagerEdit;

public interface AccessTraza {

	public int setTraza(ObjTrazaManager traza);

	public ArrayList<ObjTrazaManagerEdit> getListTrazaByIdOp(int id);
	
	
	

}
