package cl.nexo.manager.access.ubicacion;

import java.util.ArrayList;

import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;

public interface AccessUbicacion {

	public ArrayList<ObjComboSelectValueInt> getPais();

	public ArrayList<ObjComboSelectValueInt> getRegion(int pais);

	public ArrayList<ObjComboSelectValueInt> getCiudad(int region);

	public ArrayList<ObjComboSelectValueInt> getComuna(int ciudad);

}
