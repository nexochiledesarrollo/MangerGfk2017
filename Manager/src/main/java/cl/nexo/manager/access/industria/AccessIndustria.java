package cl.nexo.manager.access.industria;

import java.util.ArrayList;

import cl.nexo.manager.obj.tools.ObjComboSelect2GroupValueInt;

public interface AccessIndustria {

	public ArrayList<ObjComboSelect2GroupValueInt> getIndustriaSub(int ind, String lang);
	
	public ArrayList<ObjComboSelect2GroupValueInt> getIndustriaCategoria(int sub,String lang);

	public ArrayList<ObjComboSelect2GroupValueInt> getIndustria(String lang, String Place);

	public int getExistIndustriaSub(int id, String lang);

	public int getExistIndustriaCategoria(int id, String lang);

	public String getNombreIndustriaById(int id, String lang);

	public String getNombreSubIndustriaById(int id, String lang);

	public String getNombreCategoriaIndustriaById(int id, String lang);

	

	

}
