package cl.nexo.manager.access.proyecto;

import java.util.ArrayList;

import cl.nexo.manager.obj.asignacion.usuario.ObjAsignUser;
import cl.nexo.manager.obj.asignacion.usuario.ObjAsignUserDetalle;
import cl.nexo.manager.obj.asignacion.usuario.ObjFiltroAsignUser;
import cl.nexo.manager.obj.proyecto.ObjEstudio;

public interface AccessAsignUsers {

	public ArrayList<ObjAsignUserDetalle> getDetalleAsignUser(ObjFiltroAsignUser fil);

	public ArrayList<ObjAsignUser> getListAsignUser(ObjFiltroAsignUser fil);

}
