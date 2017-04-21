package cl.nexo.manager.access.tarea;

import java.util.ArrayList;

import cl.nexo.manager.obj.nota.ObjNota;
import cl.nexo.manager.obj.tarea.ObjTarea;
import cl.nexo.manager.obj.tarea.ObjTareaPropia;

public interface AccessTarea {

	public ArrayList<ObjNota> getListTareasPropias(int user);

	public ObjNota getTareasPropias(int id);

	public int setTareaPropia(ObjNota nota);

	int updateTareaPropia(ObjNota nota);

	public int deleteTareaPropia(int id);

	public ObjTarea getTarea(int id);

	public int getCountTareas(int ttarea, int user, int grupo);

	public ArrayList<ObjTarea> getListTareas(int ttarea, int user, int grupo, String lang);

	public ArrayList<ObjTarea> getListTareasDashboard(int user , String lang);

	public int setTarea(ObjTarea tarea);

	public int updateTarea(ObjTarea tarea);

	public int deleteTarea(int id);

	

	

	
	
	

}
