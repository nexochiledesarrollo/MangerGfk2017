package cl.nexo.manager.access.general.tools;

import cl.nexo.manager.obj.conf.ObjConfGeneral;

public interface AccessGeneralTools {

	public ObjConfGeneral getConfigGeneralById(int idAtributo);

	public int splitNumberExcel(String number);

	public String getValorConfigById(int atributo);

	public String getNewCodigo(int longitud);

	public String getNewUuId();

	public int existCharOfString(String cadena, String caracter);

	

}
