package cl.nexo.manager.access.producto;

import java.util.ArrayList;

import cl.nexo.manager.obj.proyecto.ObjEstudioProducto;
import cl.nexo.manager.obj.tools.ObjComboSelect2GroupValueInt;

public interface AccessProducto {

	public ArrayList<ObjComboSelect2GroupValueInt> getProductoCategoriaSub(int id,	String lang);

	public int getExistProductoCategoriaSub(int id, String lang);

	public ArrayList<ObjComboSelect2GroupValueInt> getProductoCategoria(int id,String lang);

	public int getExistProductoCategoria(int id, String lang);

	public ArrayList<ObjComboSelect2GroupValueInt> getProductoSub(int id, String lang);

	public int getExistProductoSub(int id, String lang);

	public ArrayList<ObjComboSelect2GroupValueInt> getProducto(String lang);

	public ArrayList<ObjEstudioProducto> getArrayProducto(String value, String uiid,int idOp);

	public int setProductoOperacion(ArrayList<ObjEstudioProducto> prods, int id);

	public int setProductoOperacion2(ObjEstudioProducto prod, int id);

	public ArrayList<ObjEstudioProducto> getProductoEstudioById(int id);

}
