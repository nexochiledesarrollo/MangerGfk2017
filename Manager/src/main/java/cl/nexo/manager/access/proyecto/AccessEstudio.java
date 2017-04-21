package cl.nexo.manager.access.proyecto;

import java.util.ArrayList;

import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudioAnalisis;
import cl.nexo.manager.obj.proyecto.ObjEstudioBussnessCase;
import cl.nexo.manager.obj.proyecto.ObjEstudioCodificacion;
import cl.nexo.manager.obj.proyecto.ObjEstudioCuestionario;
import cl.nexo.manager.obj.proyecto.ObjEstudioDetalle;
import cl.nexo.manager.obj.proyecto.ObjEstudioDigitacion;
import cl.nexo.manager.obj.proyecto.ObjEstudioFiltros;
import cl.nexo.manager.obj.proyecto.ObjEstudioRecoleccion;
import cl.nexo.manager.obj.proyecto.ObjEstudioTabulacion;
import cl.nexo.manager.obj.proyecto.ObjResultCreaCotOp;

public interface AccessEstudio {

	public String getKeyEstudio();

	public ObjEstudio getEstudioById(int id);
	
	public ObjResultCreaCotOp setOperacion(ObjEstudio estudio);

	public int getIdOperacionByUid(String uuid);

	public int getCodEstudioByUid(int id);

	public ObjResultCreaCotOp setDetalleOperacion(ObjEstudio estudio);

	public int getIdOperacionDetalleByUid(int idDetalle);

	public int updateDetalleOperacion(ObjEstudioDetalle det);

	public ObjEstudioCodificacion getCodificacionOperacionByUid(int id);

	public ObjResultCreaCotOp setCodificacionOperacion(ObjEstudioCodificacion cod);

	public ObjResultCreaCotOp updateCodificacionOperacion(ObjEstudioCodificacion cod);

	public int deleteCodificacionOperacion(int id);

	
	public ObjEstudioDigitacion getDigitacionOperacionByUid(int id);

	public ObjResultCreaCotOp setDigitacionOperacion(ObjEstudioDigitacion dig);

	public ObjResultCreaCotOp updateDigitacionOperacion(ObjEstudioDigitacion dig);

	public int deleteDigitacionOperacion(int id);

	
	public ObjEstudioTabulacion getTabulacionOperacionByUid(int id);

	public ObjResultCreaCotOp setTabulacionOperacion(ObjEstudioTabulacion tab);

	public ObjResultCreaCotOp updateTabulacionOperacion(ObjEstudioTabulacion tab);

	public int deleteTabulacionOperacion(int id);

	
	public ObjEstudioAnalisis getAnalisisOperacionByUid(int id);

	public ObjResultCreaCotOp setAnalisisOperacion(ObjEstudioAnalisis ana);

	public ObjResultCreaCotOp updateAnalisisOperacion(ObjEstudioAnalisis ana);

	public int deleteAnalisisOperacion(int id);

	
	public ObjEstudioRecoleccion getRecoleccionOperacionByUid(int id);
	
	public ArrayList<ObjEstudioRecoleccion> getListRecoleccionOperacionByUid(int id);

	public ObjResultCreaCotOp setRecoleccionOperacion(ObjEstudioRecoleccion rec);

	public int deleteRecoleccionOperacion(int id);

	public ObjResultCreaCotOp updateRecoleccionOperacion(ObjEstudioRecoleccion rec);

	
	
	public ObjEstudioBussnessCase getBussnessCaseOperacionByUid(int id);

	public ObjResultCreaCotOp setBussnessCaseOperacion(ObjEstudioBussnessCase bc);

	public ObjResultCreaCotOp updateBussnessCaseOperacion(ObjEstudioBussnessCase bc);

	public int deleteBussnessCaseOperacion(int id);

	public int updateLastUpdateOperacion(ObjEstudio est);

	public int updatePriorityOperacion(ObjEstudio est);

	public int updateColaOperacion(ObjEstudio est);

	public int updateColaEstadoOperacion(ObjEstudio est);

	public int deleteOperacion(ObjEstudio est);

	public ObjEstudioDetalle getDetalleOperacionByUid(int id);

	public int updateActivateOperacion(ObjEstudio est);

	public ArrayList<ObjEstudio> getListEstudioByFilter(ObjEstudioFiltros fitros);

	public ObjEstudio getFullEstudioByUid(int id);

	public int updateDateCompromisoOperacion(ObjEstudio est);

	public int getCountEstudios();

	public ArrayList<ObjEstudio> getListEstudioByUserResAsig(int idUser, int User,	String lang);

	public ArrayList<ObjEstudio> getListEstudioByUserAsig(int user, String lang, int flujo, int resp);

	public ArrayList<ObjEstudio> getListResponsableByIdEstudio(int id, String lang);

	public ArrayList<ObjEstudio> getListEstudioByUser(int id, String lang);

	public int updateEndOperacion(ObjEstudio est);

	public ArrayList<ObjEstudio> getListEstudioWorkFlow15(int id, String lang);

	public int updateOperacion(ObjEstudio det);

	public int updateOperacionFechasEtapa(ObjEstudio det);

	public String getValueValorCadenaWorkflow(int idOp, int idAct);

	public int setValueValorCadenaWorkflow(int idOp, int idAct, String val);

	public int updateValueValorCadenaWorkflow(int idOp, int idAct, String value);

	public int deleteValueValorCadenaWorkflow(int idOp, int idAct);

	public int createActivityPuestaMarcha(int idOp);
	
	public ObjEstudio getFullEstudioReunionByUid(int id);

	public void updateColaEstudio(int nuevo_estado,int id_operacion);
	
	
	
	
	
	
	

}
