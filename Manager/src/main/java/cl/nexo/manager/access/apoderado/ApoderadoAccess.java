package cl.nexo.manager.access.apoderado;

import java.util.ArrayList;

import cl.nexo.manager.obj.apoderado.ObjListApoderado;
import cl.nexo.manager.obj.login.ObjLoginUser;


public interface ApoderadoAccess {


	public ArrayList<ObjListApoderado> getListApoderados();
	public ObjListApoderado getApoderadoById(int id);
	public int updateApoderado(ObjListApoderado apd);
	public int deleteApoderado(String cedula);
	public int crearApoderado(ObjListApoderado apd);
	

	

	

	

	

	

	

}
