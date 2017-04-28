package cl.nexo.manager.access.asignacionPersonal;

import java.util.ArrayList;




import cl.nexo.manager.obj.login.ObjLoginUserHoras;
import cl.nexo.manager.obj.tools.ObjComboSelectValueString;


public interface AccessAsignacionPersonal {
	public ArrayList<ObjLoginUserHoras> getListUserHorasByFechas(String desde,String hasta,int div,int sub_d);
	public ArrayList<ObjComboSelectValueString> getListRangoFechas(String desde,String hasta);
	public int buscaHorasDipsUserDia(String dia,int user);
	public int buscaHorasDipsUserDiaEstudio(String dia,int user,int estudio);
	public void asignaHorasUserEstudio(int horas, String dia, int estudio,int user,int accion) ;
	public ArrayList<ObjLoginUserHoras> getAsignadosEstudio(int estudio) ;
	public ArrayList<ObjLoginUserHoras> getAsignadosEstudioDias(int estudio,int user) ;
	
	

}
