package cl.nexo.manager.access.asignacionPersonal;

import java.util.ArrayList;

import cl.nexo.manager.obj.apoderado.ObjListApoderado;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjLoginUserHoras;


public interface AccessAsignacionPersonal {
	public ArrayList<ObjLoginUserHoras> getListUserHorasByFechas(String desde,String hasta,int div,int sub_d);

}
