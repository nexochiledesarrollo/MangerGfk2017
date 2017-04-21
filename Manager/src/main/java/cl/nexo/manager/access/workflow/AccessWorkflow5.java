package cl.nexo.manager.access.workflow;

import java.util.ArrayList;

import cl.nexo.manager.obj.proyecto.ObjEstudio;

public interface AccessWorkflow5 {

	public ArrayList<ObjEstudio> getListEstudioByUser(int id, String lang);

	public ArrayList<ObjEstudio> getListEstudioByUserResAsig(int idUser, int User,	String lang);

}
