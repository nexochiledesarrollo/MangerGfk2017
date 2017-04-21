package cl.nexo.manager.access.menu;

import java.util.ArrayList;
import cl.nexo.manager.obj.menu.ObjMenuManager;
import cl.nexo.manager.obj.menu.ObjModuloMenuManager;
import cl.nexo.manager.obj.menu.ObjUnidadMenuManager;

public interface AccessMenu {

	public ObjMenuManager getMenuSistema(int tipo, int cliente, int perfil);

	public ArrayList<ObjUnidadMenuManager> getUnidades(int menu, int perfil);

	public ArrayList<ObjModuloMenuManager> getModulos(int unidad, int perfil);

}
