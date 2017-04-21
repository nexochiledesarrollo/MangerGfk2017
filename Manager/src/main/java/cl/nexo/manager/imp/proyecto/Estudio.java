package cl.nexo.manager.imp.proyecto;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.industria.AccessIndustria;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.producto.AccessProducto;
import cl.nexo.manager.access.proyecto.AccessCuestionario;
import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.access.reunion.AccessReunion;
import cl.nexo.manager.imp.reunion.Reunion;
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
import cl.nexo.manager.obj.proyecto.ObjProyectoEstandar;
import cl.nexo.manager.obj.proyecto.ObjResultCreaCotOp;

public class Estudio implements AccessEstudio {
	
	private static final Logger logger = Logger.getLogger(Estudio.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	private SecureRandom random  = new SecureRandom();
	
	@Override
	public String getKeyEstudio(){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		int longKey = Integer.valueOf(tools.getValorConfigById(29));
		
		String key = new BigInteger(130, random).toString(longKey);
		
		return key;
	}
	@Override
	public ObjEstudio getFullEstudioByUid(int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessProducto prods = (AccessProducto) context.getBean("AccessProducto");
		AccessCuestionario cuests = (AccessCuestionario) context.getBean("AccessCuestionario");
		
		
		ObjEstudio es = new ObjEstudio();
		Connection conn = null;
		
		String query = " SELECT m.id_operacion "
					   +"   ,m.id_cotizacion "
					   +"   ,m.id_proyectom "
					   +"   ,m.canal_venta "
					   +"   ,m.cod_sam "
					   +"   ,m.tipo_sam "
					   +"   ,m.id_sap "
					   +"   ,m.cod_operacion "
					   +"   ,m.cod_manager "
					   +"   ,m.cod_cotizacion "
					   +"   ,m.cod_crm "
					   +"   ,m.uid_operacion "
					   +"   ,m.id_cliente "
					   +"   ,m.area_medicion "
					   +"   ,m.sector_medicion "
					   +"   ,m.industria_medicion "
					   +"   ,m.id_tipo_entrevista "
					   +"   ,m.nombre_operacion "
					   +"   ,m.estado_medicion "
					   +"   ,m.orden_medicion "
					   +"   ,m.cola_operacion "
					   +"   ,m.priori_operacion "
					   +"   ,m.activa_operacion "
					   +"   ,m.fcrea_medicion "
					   +"   ,m.screa_medicion "
					   +"   ,m.factivacion_medicion "
					   +"   ,m.sactivacion_medicion "
					   +"   ,m.fmod_medicion "
					   +"   ,m.smod_medicion "
					   +"   ,m.felimina_medicion "
					   +"   ,m.selimina_medicion "
					   +"   ,m.elimina_medicion "
					   +"   ,m.fingreso_puesta_marcha "
					   +"   ,m.fsalida_puesta_marcha "
					   +"   ,m.fingreso_implementacion "
					   +"   ,m.fsalida_implementacion "
					   +"   ,m.fingreso_recoleccion "
					   +"   ,m.fsalida_recoleccion "
					   +"   ,m.fingreso_codificacion "
					   +"   ,m.fsalida_codificacion "
					   +"   ,m.fingreso_digitacion "
					   +"   ,m.fsalida_digitacion "
					   +"   ,m.fingreso_depuracion "
					   +"   ,m.fsalida_depuracion "
					   +"   ,m.fingreso_tabulacion "
					   +"   ,m.fsalida_tabulacion "
					   +"   ,m.fingreso_entrega "
					   +"   ,m.fsalida_entrega "
					   +"   ,c1.nombre_cliente "
					   +"   ,e.nombre_estado "
					   +"   ,f.nombre_flujo "
					   +"   ,m.end_operacion "
					   +"   ,m.fcom_ini_campo "
					   +"   ,m.fcom_fin_campo "
					   +"   ,m.fcom_ini_bbdd "
					   +"   ,m.fcom_fin_bbdd "
					   +"   ,m.fcom_entrega "
					   +" FROM man_proyecto_manager_medicion m "
					   +" LEFT JOIN man_cliente c1 ON c1.id_cliente = m.id_cliente "
					   +" LEFT JOIN man_estados e ON e.cod_estado = m.estado_medicion "
					   +" LEFT JOIN man_flujo f ON f.cod_flujo = m.cola_operacion "
					   +" WHERE m.id_operacion =" + id ;
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug("query_estudio: "+query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				 es.setId_operacion(rs.getInt("id_operacion")); 
				 es.setId_cotizacion(rs.getInt("id_cotizacion"));
				 es.setId_proyectom(rs.getInt("id_proyectom"));
				 es.setCanal_venta(rs.getInt("canal_venta"));
				 es.setCod_sam(rs.getString("cod_sam"));
				 es.setTipo_sam(rs.getString("tipo_sam"));
				 es.setId_sap(rs.getString("id_sap"));
				 es.setCod_operacion(rs.getInt("cod_operacion"));
				 es.setCod_manager(rs.getString("cod_manager"));
				 es.setCodigo_cotizacion(rs.getString("cod_cotizacion"));
				 es.setId_crm(rs.getString("cod_crm"));
				 es.setUid_operacion(rs.getString("uid_operacion"));
				 es.setId_cliente(rs.getInt("id_cliente"));
				 es.setStr_cliente(rs.getString("nombre_cliente"));
				 es.setArea_medicion(rs.getInt("area_medicion"));
				 es.setSector_medicion(rs.getInt("sector_medicion"));
				 es.setIndustria_medicion(rs.getInt("industria_medicion"));
				 es.setId_tipo_entrevista(rs.getString("id_tipo_entrevista"));
				 es.setNombre_operacion(rs.getString("nombre_operacion"));
				 es.setEstado_medicion(rs.getInt("estado_medicion"));
				 es.setStr_estado_medicion(rs.getString("nombre_estado"));
				 es.setOrden_medicion(rs.getInt("orden_medicion"));
				 es.setCola_operacion(rs.getInt("cola_operacion"));
				 es.setStr_cola_operacion(rs.getString("nombre_flujo"));
				 es.setPriori_operacion(rs.getInt("priori_operacion"));
				 es.setActiva_operacion(rs.getInt("activa_operacion"));
				 es.setEnd_operacion(rs.getInt("end_operacion"));
				 
				 if(rs.getDate("fcrea_medicion")!= null){
					 es.setFcreacion_proyectom(format3.format(rs.getTimestamp("fcrea_medicion")));
					 es.setScreacion_proyectom(rs.getInt("screa_medicion"));
					 es.setStr_screacion(logins.getStrLoginById(rs.getInt("screa_medicion")));
				  }
				  if(rs.getDate("factivacion_medicion")!= null){
					  es.setFactivacion_medicion(format3.format(rs.getTimestamp("factivacion_medicion")));
					  es.setSactivacion_medicion(rs.getInt("sactivacion_medicion"));
					  es.setStr_sactivacion_medicion(logins.getStrLoginById(rs.getInt("sactivacion_medicion")));
				  }
				  if(rs.getDate("fmod_medicion")!= null){
					  es.setFmod_proyectom(format3.format(rs.getTimestamp("fmod_medicion")));
					  es.setSmod_proyectom(rs.getInt("smod_medicion"));
					  es.setStr_smodp(logins.getStrLoginById(rs.getInt("smod_medicion")));
				  }
				  if(rs.getDate("felimina_medicion")!= null){
					  es.setFelimina_proyectom(format3.format(rs.getTimestamp("felimina_medicion")));
					  es.setSelimina_proyectom(rs.getInt("selimina_medicion"));
					  es.setStr_seliminap(logins.getStrLoginById(rs.getInt("selimina_medicion")));
				  }
				  
				  es.setElimina_proyectom(rs.getInt("elimina_medicion"));
				  
				  if(rs.getDate("fingreso_puesta_marcha")!= null){
					  es.setFingreso_puesta_marcha_operacion(format3.format(rs.getTimestamp("fingreso_puesta_marcha")));
				  }
				  if(rs.getDate("fsalida_puesta_marcha")!= null){
					  es.setFsalida_puesta_marcha_operacion(format3.format(rs.getTimestamp("fsalida_puesta_marcha")));
				  }
				  
				  if(rs.getDate("fingreso_implementacion")!= null){
					  es.setFingreso_implementacion_operacion(format3.format(rs.getTimestamp("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!= null){
					  es.setFsalida_implementacion_operacion(format3.format(rs.getTimestamp("fsalida_implementacion")));
				  }
				  
				  if(rs.getDate("fingreso_recoleccion")!= null){
					  es.setFingreso_recoleccion_operacion(format3.format(rs.getTimestamp("fingreso_recoleccion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!= null){
					  es.setFsalida_recoleccion_operacion(format3.format(rs.getTimestamp("fsalida_implementacion")));
				  }
				  
				  if(rs.getDate("fingreso_codificacion")!= null){
					  es.setFingreso_codificacion_operacion(format3.format(rs.getTimestamp("fingreso_codificacion")));
				  }
				  if(rs.getDate("fsalida_codificacion")!= null){
					  es.setFsalida_codificacion_operacion(format3.format(rs.getTimestamp("fsalida_codificacion")));
				  }
				  
				  if(rs.getDate("fingreso_digitacion")!= null){
					  es.setFingreso_digitacion_operacion(format3.format(rs.getTimestamp("fingreso_digitacion")));
				  }
				  if(rs.getDate("fsalida_digitacion")!= null){
					  es.setFsalida_digitacion_operacion(format3.format(rs.getTimestamp("fsalida_digitacion")));
				  }
				  
				  if(rs.getDate("fingreso_depuracion")!= null){
					  es.setFingreso_depuracion_operacion(format3.format(rs.getTimestamp("fingreso_depuracion")));
				  }
				  if(rs.getDate("fsalida_depuracion")!= null){
					  es.setFsalida_depuracion_operacion(format3.format(rs.getTimestamp("fsalida_depuracion")));
				  }
				  if(rs.getDate("fingreso_tabulacion")!= null){
					  es.setFingreso_tabulacion_operacion(format3.format(rs.getTimestamp("fingreso_tabulacion")));
				  }
				  if(rs.getDate("fsalida_tabulacion")!= null){
					  es.setFsalida_tabulacion_operacion(format3.format(rs.getTimestamp("fsalida_tabulacion")));
				  }
				  
				  if(rs.getDate("fingreso_entrega")!= null){
					  es.setFingreso_entrega_operacion(format3.format(rs.getTimestamp("fingreso_entrega")));
				  }
				  if(rs.getDate("fsalida_entrega")!= null){
					  es.setFsalida_entrega_operacion(format3.format(rs.getTimestamp("fsalida_entrega")));
				  }
				  
				  if(rs.getDate("fcom_ini_campo")!= null){
					  es.setFcom_ini_campo(format4.format(rs.getTimestamp("fcom_ini_campo")));
				  }
				  
				  if(rs.getDate("fcom_fin_campo")!= null){
					  es.setFcom_fin_campo(format4.format(rs.getTimestamp("fcom_fin_campo")));
				  }
				  
				  
				  if(rs.getDate("fcom_ini_bbdd")!= null){
					  es.setFcom_ini_bbdd(format4.format(rs.getTimestamp("fcom_ini_bbdd")));
				  }
				  
				  if(rs.getDate("fcom_fin_bbdd")!= null){
					  es.setFcom_fin_bbdd(format4.format(rs.getTimestamp("fcom_fin_bbdd")));
				  }
				  
				  
				  if(rs.getDate("fcom_entrega")!= null){
					  es.setFcom_entrega(format4.format(rs.getTimestamp("fcom_entrega")));
				  }

				  
			  }

			  es.setDetalle(this.getDetalleOperacionByUid(id));
			  es.setRecoleccion(this.getListRecoleccionOperacionByUid(id));
			  es.setBbcc(this.getBussnessCaseOperacionByUid(id));
			  es.setProductos(prods.getProductoEstudioById(id));
			  es.setCuestionario(cuests.getCuestionarioByIdOperacion(id));
			  es.setCodificacion(this.getCodificacionOperacionByUid(id));
			  es.setDigitacion(this.getDigitacionOperacionByUid(id));
			  es.setTabulacion(this.getTabulacionOperacionByUid(id));
			  es.setAnalisis(this.getAnalisisOperacionByUid(id));
			  
			  
			  return es;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	
	@Override
	public ObjEstudio getFullEstudioReunionByUid(int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessProducto prods = (AccessProducto) context.getBean("AccessProducto");
		AccessCuestionario cuests = (AccessCuestionario) context.getBean("AccessCuestionario");
		AccessReunion reunion = (AccessReunion) context.getBean("AccessReunion");
		
		ObjEstudio es = new ObjEstudio();
		Connection conn = null;
		
		String query = " SELECT m.id_operacion "
					   +"   ,m.id_cotizacion "
					   +"   ,m.id_proyectom "
					   +"   ,m.canal_venta "
					   +"   ,m.cod_sam "
					   +"   ,m.tipo_sam "
					   +"   ,m.id_sap "
					   +"   ,m.cod_operacion "
					   +"   ,m.cod_manager "
					   +"   ,m.cod_cotizacion "
					   +"   ,m.cod_crm "
					   +"   ,m.uid_operacion "
					   +"   ,m.id_cliente "
					   +"   ,m.area_medicion "
					   +"   ,m.sector_medicion "
					   +"   ,m.industria_medicion "
					   +"   ,m.id_tipo_entrevista "
					   +"   ,m.nombre_operacion "
					   +"   ,m.estado_medicion "
					   +"   ,m.orden_medicion "
					   +"   ,m.cola_operacion "
					   +"   ,m.priori_operacion "
					   +"   ,m.activa_operacion "
					   +"   ,m.fcrea_medicion "
					   +"   ,m.screa_medicion "
					   +"   ,m.factivacion_medicion "
					   +"   ,m.sactivacion_medicion "
					   +"   ,m.fmod_medicion "
					   +"   ,m.smod_medicion "
					   +"   ,m.felimina_medicion "
					   +"   ,m.selimina_medicion "
					   +"   ,m.elimina_medicion "
					   +"   ,m.fingreso_puesta_marcha "
					   +"   ,m.fsalida_puesta_marcha "
					   +"   ,m.fingreso_implementacion "
					   +"   ,m.fsalida_implementacion "
					   +"   ,m.fingreso_recoleccion "
					   +"   ,m.fsalida_recoleccion "
					   +"   ,m.fingreso_codificacion "
					   +"   ,m.fsalida_codificacion "
					   +"   ,m.fingreso_digitacion "
					   +"   ,m.fsalida_digitacion "
					   +"   ,m.fingreso_depuracion "
					   +"   ,m.fsalida_depuracion "
					   +"   ,m.fingreso_tabulacion "
					   +"   ,m.fsalida_tabulacion "
					   +"   ,m.fingreso_entrega "
					   +"   ,m.fsalida_entrega "
					   +"   ,c1.nombre_cliente "
					   +"   ,e.nombre_estado "
					   +"   ,f.nombre_flujo "
					   +"   ,m.end_operacion "
					   +"   ,m.fcom_ini_campo "
					   +"   ,m.fcom_fin_campo "
					   +"   ,m.fcom_ini_bbdd "
					   +"   ,m.fcom_fin_bbdd "
					   +"   ,m.fcom_entrega "
					   +" FROM man_proyecto_manager_medicion m "
					   +" LEFT JOIN man_cliente c1 ON c1.id_cliente = m.id_cliente "
					   +" LEFT JOIN man_estados e ON e.cod_estado = m.estado_medicion "
					   +" LEFT JOIN man_flujo f ON f.cod_flujo = m.cola_operacion "
					   +" WHERE m.id_operacion =" + id ;
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug("query_estudio: "+query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				 es.setId_operacion(rs.getInt("id_operacion")); 
				 es.setId_cotizacion(rs.getInt("id_cotizacion"));
				 es.setId_proyectom(rs.getInt("id_proyectom"));
				 es.setCanal_venta(rs.getInt("canal_venta"));
				 es.setCod_sam(rs.getString("cod_sam"));
				 es.setTipo_sam(rs.getString("tipo_sam"));
				 es.setId_sap(rs.getString("id_sap"));
				 es.setCod_operacion(rs.getInt("cod_operacion"));
				 es.setCod_manager(rs.getString("cod_manager"));
				 es.setCodigo_cotizacion(rs.getString("cod_cotizacion"));
				 es.setId_crm(rs.getString("cod_crm"));
				 es.setUid_operacion(rs.getString("uid_operacion"));
				 es.setId_cliente(rs.getInt("id_cliente"));
				 es.setStr_cliente(rs.getString("nombre_cliente"));
				 es.setArea_medicion(rs.getInt("area_medicion"));
				 es.setSector_medicion(rs.getInt("sector_medicion"));
				 es.setIndustria_medicion(rs.getInt("industria_medicion"));
				 es.setId_tipo_entrevista(rs.getString("id_tipo_entrevista"));
				 es.setNombre_operacion(rs.getString("nombre_operacion"));
				 es.setEstado_medicion(rs.getInt("estado_medicion"));
				 es.setStr_estado_medicion(rs.getString("nombre_estado"));
				 es.setOrden_medicion(rs.getInt("orden_medicion"));
				 es.setCola_operacion(rs.getInt("cola_operacion"));
				 es.setStr_cola_operacion(rs.getString("nombre_flujo"));
				 es.setPriori_operacion(rs.getInt("priori_operacion"));
				 es.setActiva_operacion(rs.getInt("activa_operacion"));
				 es.setEnd_operacion(rs.getInt("end_operacion"));
				 
				 if(rs.getDate("fcrea_medicion")!= null){
					 es.setFcreacion_proyectom(format3.format(rs.getTimestamp("fcrea_medicion")));
					 es.setScreacion_proyectom(rs.getInt("screa_medicion"));
					 es.setStr_screacion(logins.getStrLoginById(rs.getInt("screa_medicion")));
				  }
				  if(rs.getDate("factivacion_medicion")!= null){
					  es.setFactivacion_medicion(format3.format(rs.getTimestamp("factivacion_medicion")));
					  es.setSactivacion_medicion(rs.getInt("sactivacion_medicion"));
					  es.setStr_sactivacion_medicion(logins.getStrLoginById(rs.getInt("sactivacion_medicion")));
				  }
				  if(rs.getDate("fmod_medicion")!= null){
					  es.setFmod_proyectom(format3.format(rs.getTimestamp("fmod_medicion")));
					  es.setSmod_proyectom(rs.getInt("smod_medicion"));
					  es.setStr_smodp(logins.getStrLoginById(rs.getInt("smod_medicion")));
				  }
				  if(rs.getDate("felimina_medicion")!= null){
					  es.setFelimina_proyectom(format3.format(rs.getTimestamp("felimina_medicion")));
					  es.setSelimina_proyectom(rs.getInt("selimina_medicion"));
					  es.setStr_seliminap(logins.getStrLoginById(rs.getInt("selimina_medicion")));
				  }
				  
				  es.setElimina_proyectom(rs.getInt("elimina_medicion"));
				  
				  if(rs.getDate("fingreso_puesta_marcha")!= null){
					  es.setFingreso_puesta_marcha_operacion(format3.format(rs.getTimestamp("fingreso_puesta_marcha")));
				  }
				  if(rs.getDate("fsalida_puesta_marcha")!= null){
					  es.setFsalida_puesta_marcha_operacion(format3.format(rs.getTimestamp("fsalida_puesta_marcha")));
				  }
				  
				  if(rs.getDate("fingreso_implementacion")!= null){
					  es.setFingreso_implementacion_operacion(format3.format(rs.getTimestamp("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!= null){
					  es.setFsalida_implementacion_operacion(format3.format(rs.getTimestamp("fsalida_implementacion")));
				  }
				  
				  if(rs.getDate("fingreso_recoleccion")!= null){
					  es.setFingreso_recoleccion_operacion(format3.format(rs.getTimestamp("fingreso_recoleccion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!= null){
					  es.setFsalida_recoleccion_operacion(format3.format(rs.getTimestamp("fsalida_implementacion")));
				  }
				  
				  if(rs.getDate("fingreso_codificacion")!= null){
					  es.setFingreso_codificacion_operacion(format3.format(rs.getTimestamp("fingreso_codificacion")));
				  }
				  if(rs.getDate("fsalida_codificacion")!= null){
					  es.setFsalida_codificacion_operacion(format3.format(rs.getTimestamp("fsalida_codificacion")));
				  }
				  
				  if(rs.getDate("fingreso_digitacion")!= null){
					  es.setFingreso_digitacion_operacion(format3.format(rs.getTimestamp("fingreso_digitacion")));
				  }
				  if(rs.getDate("fsalida_digitacion")!= null){
					  es.setFsalida_digitacion_operacion(format3.format(rs.getTimestamp("fsalida_digitacion")));
				  }
				  
				  if(rs.getDate("fingreso_depuracion")!= null){
					  es.setFingreso_depuracion_operacion(format3.format(rs.getTimestamp("fingreso_depuracion")));
				  }
				  if(rs.getDate("fsalida_depuracion")!= null){
					  es.setFsalida_depuracion_operacion(format3.format(rs.getTimestamp("fsalida_depuracion")));
				  }
				  if(rs.getDate("fingreso_tabulacion")!= null){
					  es.setFingreso_tabulacion_operacion(format3.format(rs.getTimestamp("fingreso_tabulacion")));
				  }
				  if(rs.getDate("fsalida_tabulacion")!= null){
					  es.setFsalida_tabulacion_operacion(format3.format(rs.getTimestamp("fsalida_tabulacion")));
				  }
				  
				  if(rs.getDate("fingreso_entrega")!= null){
					  es.setFingreso_entrega_operacion(format3.format(rs.getTimestamp("fingreso_entrega")));
				  }
				  if(rs.getDate("fsalida_entrega")!= null){
					  es.setFsalida_entrega_operacion(format3.format(rs.getTimestamp("fsalida_entrega")));
				  }
				  
				  if(rs.getDate("fcom_ini_campo")!= null){
					  es.setFcom_ini_campo(format4.format(rs.getTimestamp("fcom_ini_campo")));
				  }
				  
				  if(rs.getDate("fcom_fin_campo")!= null){
					  es.setFcom_fin_campo(format4.format(rs.getTimestamp("fcom_fin_campo")));
				  }
				  
				  
				  if(rs.getDate("fcom_ini_bbdd")!= null){
					  es.setFcom_ini_bbdd(format4.format(rs.getTimestamp("fcom_ini_bbdd")));
				  }
				  
				  if(rs.getDate("fcom_fin_bbdd")!= null){
					  es.setFcom_fin_bbdd(format4.format(rs.getTimestamp("fcom_fin_bbdd")));
				  }
				  
				  
				  if(rs.getDate("fcom_entrega")!= null){
					  es.setFcom_entrega(format4.format(rs.getTimestamp("fcom_entrega")));
				  }
				  
				  
				  

				  
			  }

			  es.setDetalle(this.getDetalleOperacionByUid(id));
			  es.setRecoleccion(this.getListRecoleccionOperacionByUid(id));
			  es.setBbcc(this.getBussnessCaseOperacionByUid(id));
			  es.setProductos(prods.getProductoEstudioById(id));
			  es.setCuestionario(cuests.getCuestionarioByIdOperacion(id));
			  es.setCodificacion(this.getCodificacionOperacionByUid(id));
			  es.setDigitacion(this.getDigitacionOperacionByUid(id));
			  es.setTabulacion(this.getTabulacionOperacionByUid(id));
			  es.setAnalisis(this.getAnalisisOperacionByUid(id));
			  
			  es.setExistReunionEstudio(reunion.existeReunionByidOperacion(id));
			  
			  
			  return es;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	
	
	
	@Override
	public ObjEstudio getEstudioById(int id){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		
		ObjEstudio es = new ObjEstudio();
		
		Connection conn = null;
		
		String query = " SELECT m.id_operacion "
				   +"   ,m.id_cotizacion "
				   +"   ,m.id_proyectom "
				   +"   ,m.canal_venta "
				   +"   ,m.cod_sam "
				   +"   ,m.tipo_sam "
				   +"   ,m.id_sap "
				   +"   ,m.cod_operacion "
				   +"   ,m.cod_manager "
				   +"   ,m.cod_cotizacion "
				   +"   ,m.cod_crm "
				   +"   ,m.uid_operacion "
				   +"   ,m.id_cliente "
				   +"   ,m.area_medicion "
				   +"   ,m.sector_medicion "
				   +"   ,m.industria_medicion "
				   +"   ,m.id_tipo_entrevista "
				   +"   ,m.nombre_operacion "
				   +"   ,m.estado_medicion "
				   +"   ,m.orden_medicion "
				   +"   ,m.cola_operacion "
				   +"   ,m.priori_operacion "
				   +"   ,m.activa_operacion "
				   +"   ,m.fcrea_medicion "
				   +"   ,m.screa_medicion "
				   +"   ,m.factivacion_medicion "
				   +"   ,m.sactivacion_medicion "
				   +"   ,m.fmod_medicion "
				   +"   ,m.smod_medicion "
				   +"   ,m.felimina_medicion "
				   +"   ,m.selimina_medicion "
				   +"   ,m.elimina_medicion "
				   +"   ,m.fingreso_puesta_marcha "
				   +"   ,m.fsalida_puesta_marcha "
				   +"   ,m.fingreso_implementacion "
				   +"   ,m.fsalida_implementacion "
				   +"   ,m.fingreso_recoleccion "
				   +"   ,m.fsalida_recoleccion "
				   +"   ,m.fingreso_codificacion "
				   +"   ,m.fsalida_codificacion "
				   +"   ,m.fingreso_digitacion "
				   +"   ,m.fsalida_digitacion "
				   +"   ,m.fingreso_depuracion "
				   +"   ,m.fsalida_depuracion "
				   +"   ,m.fingreso_tabulacion "
				   +"   ,m.fsalida_tabulacion "
				   +"   ,m.fingreso_entrega "
				   +"   ,m.fsalida_entrega "
				   +"   ,c1.nombre_cliente "
				   +"   ,e.nombre_estado "
				   +"   ,f.nombre_flujo "
				   +"   ,m.end_operacion "
				   +" FROM man_proyecto_manager_medicion m "
				   +" LEFT JOIN man_cliente c1 ON c1.id_cliente = m.id_cliente "
				   +" LEFT JOIN man_estados e ON e.cod_estado = m.estado_medicion "
				   +" LEFT JOIN man_flujo f ON f.cod_flujo = m.cola_operacion "
				   +" WHERE m.id_operacion =" + id ;
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
				  	 es.setId_operacion(rs.getInt("id_operacion")); 
					 es.setId_cotizacion(rs.getInt("id_cotizacion"));
					 es.setId_proyectom(rs.getInt("id_proyectom"));
					 es.setCanal_venta(rs.getInt("canal_venta"));
					 es.setCod_sam(rs.getString("cod_sam"));
					 es.setTipo_sam(rs.getString("tipo_sam"));
					 es.setId_sap(rs.getString("id_sap"));
					 es.setCod_operacion(rs.getInt("cod_operacion"));
					 es.setCod_manager(rs.getString("cod_manager"));
					 es.setCodigo_cotizacion(rs.getString("cod_cotizacion"));
					 es.setId_crm(rs.getString("cod_crm"));
					 es.setUid_operacion(rs.getString("uid_operacion"));
					 es.setId_cliente(rs.getInt("id_cliente"));
					 es.setStr_cliente(rs.getString("nombre_cliente"));
					 es.setArea_medicion(rs.getInt("area_medicion"));
					 es.setSector_medicion(rs.getInt("sector_medicion"));
					 es.setIndustria_medicion(rs.getInt("industria_medicion"));
					 es.setId_tipo_entrevista(rs.getString("id_tipo_entrevista"));
					 es.setNombre_operacion(rs.getString("nombre_operacion"));
					 es.setEstado_medicion(rs.getInt("estado_medicion"));
					 es.setStr_estado_medicion(rs.getString("nombre_estado"));
					 es.setOrden_medicion(rs.getInt("orden_medicion"));
					 es.setCola_operacion(rs.getInt("cola_operacion"));
					 es.setStr_cola_operacion(rs.getString("nombre_flujo"));
					 es.setPriori_operacion(rs.getInt("priori_operacion"));
					 es.setActiva_operacion(rs.getInt("activa_operacion"));
					 es.setEnd_operacion(rs.getInt("end_operacion"));
					 
					 if(rs.getDate("fcrea_medicion")!= null){
						 es.setFcreacion_proyectom(format3.format(rs.getTimestamp("fcrea_medicion")));
						 es.setScreacion_proyectom(rs.getInt("screa_medicion"));
						 es.setStr_screacion(logins.getStrLoginById(rs.getInt("screa_medicion")));
					  }
					  if(rs.getDate("factivacion_medicion")!= null){
						  es.setFactivacion_medicion(format3.format(rs.getTimestamp("factivacion_medicion")));
						  es.setSactivacion_medicion(rs.getInt("sactivacion_medicion"));
						  es.setStr_sactivacion_medicion(logins.getStrLoginById(rs.getInt("sactivacion_medicion")));
					  }
					  if(rs.getDate("fmod_medicion")!= null){
						  es.setFmod_proyectom(format3.format(rs.getTimestamp("fmod_medicion")));
						  es.setSmod_proyectom(rs.getInt("smod_medicion"));
						  es.setStr_smodp(logins.getStrLoginById(rs.getInt("smod_medicion")));
					  }
					  if(rs.getDate("felimina_medicion")!= null){
						  es.setFelimina_proyectom(format3.format(rs.getTimestamp("felimina_medicion")));
						  es.setSelimina_proyectom(rs.getInt("selimina_medicion"));
						  es.setStr_seliminap(logins.getStrLoginById(rs.getInt("selimina_medicion")));
					  }
					  
					  es.setElimina_proyectom(rs.getInt("elimina_medicion"));
					  
					  if(rs.getDate("fingreso_puesta_marcha")!= null){
						  es.setFingreso_puesta_marcha_operacion(format3.format(rs.getTimestamp("fingreso_puesta_marcha")));
					  }
					  if(rs.getDate("fsalida_puesta_marcha")!= null){
						  es.setFsalida_puesta_marcha_operacion(format3.format(rs.getTimestamp("fsalida_puesta_marcha")));
					  }
					  
					  if(rs.getDate("fingreso_implementacion")!= null){
						  es.setFingreso_implementacion_operacion(format3.format(rs.getTimestamp("fingreso_implementacion")));
					  }
					  if(rs.getDate("fsalida_implementacion")!= null){
						  es.setFsalida_implementacion_operacion(format3.format(rs.getTimestamp("fsalida_implementacion")));
					  }
					  
					  if(rs.getDate("fingreso_recoleccion")!= null){
						  es.setFingreso_recoleccion_operacion(format3.format(rs.getTimestamp("fingreso_recoleccion")));
					  }
					  if(rs.getDate("fsalida_implementacion")!= null){
						  es.setFsalida_recoleccion_operacion(format3.format(rs.getTimestamp("fsalida_implementacion")));
					  }
					  
					  if(rs.getDate("fingreso_codificacion")!= null){
						  es.setFingreso_codificacion_operacion(format3.format(rs.getTimestamp("fingreso_codificacion")));
					  }
					  if(rs.getDate("fsalida_codificacion")!= null){
						  es.setFsalida_codificacion_operacion(format3.format(rs.getTimestamp("fsalida_codificacion")));
					  }
					  
					  if(rs.getDate("fingreso_digitacion")!= null){
						  es.setFingreso_digitacion_operacion(format3.format(rs.getTimestamp("fingreso_digitacion")));
					  }
					  if(rs.getDate("fsalida_digitacion")!= null){
						  es.setFsalida_digitacion_operacion(format3.format(rs.getTimestamp("fsalida_digitacion")));
					  }
					  
					  if(rs.getDate("fingreso_depuracion")!= null){
						  es.setFingreso_depuracion_operacion(format3.format(rs.getTimestamp("fingreso_depuracion")));
					  }
					  if(rs.getDate("fsalida_depuracion")!= null){
						  es.setFsalida_depuracion_operacion(format3.format(rs.getTimestamp("fsalida_depuracion")));
					  }
					  if(rs.getDate("fingreso_tabulacion")!= null){
						  es.setFingreso_tabulacion_operacion(format3.format(rs.getTimestamp("fingreso_tabulacion")));
					  }
					  if(rs.getDate("fsalida_tabulacion")!= null){
						  es.setFsalida_tabulacion_operacion(format3.format(rs.getTimestamp("fsalida_tabulacion")));
					  }
					  
					  if(rs.getDate("fingreso_entrega")!= null){
						  es.setFingreso_entrega_operacion(format3.format(rs.getTimestamp("fingreso_entrega")));
					  }
					  if(rs.getDate("fsalida_entrega")!= null){
						  es.setFsalida_entrega_operacion(format3.format(rs.getTimestamp("fsalida_entrega")));
					  }
				  
			  }else{
				  es.setId_operacion(0);
			  }
			  
			  return es;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	
		
	}
	
	
	
	
	@Override
	public ArrayList<ObjEstudio> getListEstudioByFilter(ObjEstudioFiltros fitros){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessIndustria inds = (AccessIndustria) context.getBean("AccessIndustria");
		
		ArrayList<ObjEstudio> re = new ArrayList<ObjEstudio>();
		
		Connection conn = null;
		
		String query="SELECT "
					 +" p.id_proyectom "
					 +" ,p.codigo_proyectom "
					 +" ,p.estado_proyectom "
					 +" ,c.id_cotizacion "
					 +" ,c.codigo_cotizacion "
					 +" ,o.id_operacion "
				     +" ,o.id_cotizacion "
				     +" ,o.canal_venta "
				     +" ,o.cod_sam "
				     +" ,o.tipo_sam "
				     +" ,o.id_sap "
				     +" ,o.cod_operacion "
				     +" ,o.cod_manager "
				     +" ,o.cod_cotizacion "
				     +" ,o.cod_crm "
				     +" ,o.id_cliente "
				     +" ,cli.nombre_cliente "
				     +" ,cli.rut_cliente "
				     +" ,o.area_medicion "
				     +" ,o.sector_medicion "
				     +" ,s.nombre_sector "
				     +" ,o.industria_medicion "
				     +" ,i.des_ind "
				     +" ,o.id_tipo_entrevista "
				     +" ,o.nombre_operacion "
				     +" ,o.estado_medicion "
				     +" ,f.nombre_flujo "
				     +" ,e.id_etapa "
				     +" ,e.nombre_etapa "
				     +" ,o.orden_medicion "
				     +" ,o.cola_operacion "
				     +" ,o.priori_operacion "
				     +" ,o.activa_operacion "
				     +" ,o.fcrea_medicion "
				     +" ,o.screa_medicion "
				     +" ,o.factivacion_medicion "
				     +" ,o.sactivacion_medicion "
				     +" ,o.fmod_medicion "
				     +" ,o.smod_medicion "
				     +" ,o.felimina_medicion "
				     +" ,o.selimina_medicion "
				     +" ,o.elimina_medicion "
				     +" ,o.fingreso_puesta_marcha "
				     +" ,o.fsalida_puesta_marcha "
				     +" ,o.fingreso_implementacion "
				     +" ,o.fsalida_implementacion "
				     +" ,o.fingreso_recoleccion "
				     +" ,o.fsalida_recoleccion "
				     +" ,o.fingreso_codificacion "
				     +" ,o.fsalida_codificacion "
				     +" ,o.fingreso_digitacion "
				     +" ,o.fsalida_digitacion "
				     +" ,o.fingreso_depuracion "
				     +" ,o.fsalida_depuracion "
				     +" ,o.fingreso_tabulacion "
				     +" ,o.fsalida_tabulacion "
				     +" ,o.fingreso_entrega "
				     +" ,o.fsalida_entrega "
					 +" ,m.res_us1_op "
				     +" ,m.num_entrevistas_op"
					 +" ,(us1.nombre_user)as nom1 "
					 +" ,(us1.app_user)as app1 "
					 +" ,m.res_us2_op "
					 +" ,(us2.nombre_user)as nom2 "
					 +" ,(us2.app_user)as app2 "
					 +" ,m.res_us3_op "
					 +" ,(us3.nombre_user)as nom3 "
					 +" ,(us3.app_user)as app3 "
					 +" ,m.num_entrevistas_op "
					 +" ,es.nombre_estado "
					 +" ,(te.des_detalle)as nombre_tipo_entrevista "
					 +" ,o.end_operacion "
				  +" FROM man_proyecto_manager_medicion o "
				  +" INNER JOIN man_proyecto_manager_principal p on o.id_proyectom = p.id_proyectom "
				  +" INNER JOIN man_proyecto_manager_cotizacion c on o.id_cotizacion = c.id_cotizacion "
				  +" INNER JOIN man_proyecto_manager m on m.id_operacion = o.id_operacion "
				  +" INNER JOIN man_cliente cli on o.id_cliente = cli.id_cliente "
				  +" LEFT JOIN man_sector s on s.id_sector = o.sector_medicion "
				  +" LEFT JOIN man_industria i on o.industria_medicion = i.id_ind "
				  +" LEFT JOIN man_flujo f on o.cola_operacion = f.id_flujo "
				  +" LEFT JOIN man_estados es on o.estado_medicion = es.cod_estado "
				  +" LEFT JOIN man_etapas e on e.id_etapa = f.id_etapa "
				  +" LEFT JOIN man_combo_box_detalle te on te.valor_detalle = o.id_tipo_entrevista AND te.id_combo = 5 "
				  +" LEFT JOIN man_login_user us1 on us1.id_user = m.res_us1_op "
				  +" LEFT JOIN man_login_user us2 on us2.id_user = m.res_us2_op "
				  +" LEFT JOIN man_login_user us3 on us3.id_user = m.res_us3_op ";
				  
				  if(fitros.getActiva_operacion() == 1){query = query+"  WHERE o.activa_operacion= "+fitros.getActiva_operacion() ;}
				  
				  query = query + "ORDER BY o.id_operacion DESC ";
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  ObjEstudio res = new ObjEstudio();
				  res.setId_proyectom(rs.getInt("id_proyectom"));
				  res.setCodigo_proyectom(rs.getString("codigo_proyectom"));
				  res.setEstado_proyectom(rs.getInt("estado_proyectom"));
				  res.setNum_entrevistas_op(rs.getInt("num_entrevistas_op"));
				  res.setId_cotizacion(rs.getInt("id_cotizacion"));
				  res.setCodigo_cotizacion(rs.getString("codigo_cotizacion"));
				  res.setId_operacion(rs.getInt("id_operacion"));
				  res.setCanal_venta(rs.getInt("canal_venta"));
				  res.setCod_sam(rs.getString("cod_sam"));
				  res.setTipo_sam(rs.getString("tipo_sam"));
				  res.setId_sap(rs.getString("id_sap"));
				  res.setId_crm(rs.getString("cod_crm"));
				  res.setCod_operacion(rs.getInt("cod_operacion"));
				  res.setCod_manager(rs.getString("cod_manager"));
				  res.setId_cliente(rs.getInt("id_cliente"));
				  res.setStr_cliente(rs.getString("nombre_cliente"));
				  res.setRut_cliente(rs.getString("rut_cliente"));
				  res.setArea_medicion(rs.getInt("area_medicion"));
				  res.setSector_medicion(rs.getInt("sector_medicion"));
				  res.setStr_sector_medicion(rs.getString("nombre_sector"));
				  res.setIndustria_medicion(rs.getInt("industria_medicion"));
				  res.setStr_industria_medicion(inds.getNombreIndustriaById(rs.getInt("industria_medicion"), fitros.getLang()));
				  res.setId_tipo_entrevista(rs.getString("id_tipo_entrevista"));
				  res.setStr_id_tipo_entrevista(rs.getString("nombre_tipo_entrevista"));
				  res.setNombre_operacion(rs.getString("nombre_operacion"));
				  res.setEstado_medicion(rs.getInt("estado_medicion"));
				  res.setStr_estado_medicion(rs.getString("nombre_estado"));
				  res.setId_etapa(rs.getInt("id_etapa"));
				  res.setNombre_etapa(rs.getString("nombre_etapa"));
				  res.setOrden_medicion(rs.getInt("estado_medicion"));
				  res.setCola_operacion(rs.getInt("cola_operacion"));
				  res.setStr_cola_operacion(rs.getString("nombre_flujo"));
				  res.setPriori_operacion(rs.getInt("priori_operacion"));
				  res.setActiva_operacion(rs.getInt("activa_operacion"));
				  res.setRes_us1_op(rs.getInt("res_us1_op"));
				  res.setStr_res_us1_op(rs.getString("nom1") +" "+rs.getString("app1"));
				  res.setRes_us2_op(rs.getInt("res_us2_op"));
				  res.setStr_res_us2_op(rs.getString("nom2") +" "+rs.getString("app2"));
				  res.setRes_us3_op(rs.getInt("res_us1_op"));
				  res.setStr_res_us3_op(rs.getString("nom3") +" "+rs.getString("app3"));
				  res.setEnd_operacion(rs.getInt("end_operacion"));
				  
				  if(rs.getDate("fcrea_medicion")!= null){
					  res.setFcreacion_proyectom(format3.format(rs.getTimestamp("fcrea_medicion")));
					  res.setScreacion_proyectom(rs.getInt("screa_medicion"));
					  res.setStr_screacion(logins.getStrLoginById(rs.getInt("screa_medicion")));
				  }
				  if(rs.getDate("factivacion_medicion")!= null){
					  res.setFactivacion_medicion(format3.format(rs.getTimestamp("factivacion_medicion")));
					  res.setSactivacion_medicion(rs.getInt("sactivacion_medicion"));
					  res.setStr_sactivacion_medicion(logins.getStrLoginById(rs.getInt("sactivacion_medicion")));
				  }
				  if(rs.getDate("fmod_medicion")!= null){
					  res.setFmod_proyectom(format3.format(rs.getTimestamp("fmod_medicion")));
					  res.setSmod_proyectom(rs.getInt("smod_medicion"));
					  res.setStr_smodp(logins.getStrLoginById(rs.getInt("smod_medicion")));
				  }
				  if(rs.getDate("felimina_medicion")!= null){
					  res.setFelimina_proyectom(format3.format(rs.getTimestamp("felimina_medicion")));
					  res.setSelimina_proyectom(rs.getInt("selimina_medicion"));
					  res.setStr_estado_medicion(logins.getStrLoginById(rs.getInt("selimina_medicion")));
				  }
				  
				  res.setElimina_proyectom(rs.getInt("elimina_medicion"));
				  
				  if(rs.getDate("fingreso_puesta_marcha")!=null){
					  res.setFingreso_puesta_marcha_operacion(format3.format(rs.getTimestamp("fingreso_puesta_marcha")));
				  }
				  if(rs.getDate("fsalida_puesta_marcha")!=null){
					  res.setFsalida_puesta_marcha_operacion(format3.format(rs.getTimestamp("fsalida_puesta_marcha")));
				  }
				  if(rs.getDate("fingreso_implementacion")!=null){
					  res.setFingreso_implementacion_operacion(format3.format(rs.getTimestamp("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!=null){
					  res.setFsalida_implementacion_operacion(format3.format(rs.getTimestamp("fsalida_implementacion")));
				  }
				  if(rs.getDate("fingreso_recoleccion")!=null){
					  res.setFingreso_recoleccion_operacion(format3.format(rs.getTimestamp("fingreso_recoleccion")));
				  }
				  if(rs.getDate("fsalida_recoleccion")!=null){
					  res.setFsalida_recoleccion_operacion(format3.format(rs.getTimestamp("fsalida_recoleccion")));
				  }
				  if(rs.getDate("fingreso_codificacion")!=null){
					  res.setFingreso_codificacion_operacion(format3.format(rs.getTimestamp("fingreso_codificacion")));
				  }
				  if(rs.getDate("fsalida_codificacion")!=null){
					  res.setFsalida_codificacion_operacion(format3.format(rs.getTimestamp("fsalida_codificacion")));
				  }
				  if(rs.getDate("fingreso_digitacion")!=null){
					  res.setFingreso_digitacion_operacion(format3.format(rs.getTimestamp("fingreso_digitacion")));
				  }
				  if(rs.getDate("fsalida_digitacion")!=null){
					  res.setFsalida_digitacion_operacion(format3.format(rs.getTimestamp("fsalida_digitacion")));
				  }
				  if(rs.getDate("fingreso_depuracion")!=null){
					  res.setFingreso_depuracion_operacion(format3.format(rs.getTimestamp("fingreso_depuracion")));
				  }
				  if(rs.getDate("fsalida_depuracion")!=null){
					  res.setFsalida_depuracion_operacion(format3.format(rs.getTimestamp("fsalida_depuracion")));
				  }
				  if(rs.getDate("fingreso_tabulacion")!=null){
					  res.setFingreso_tabulacion_operacion(format3.format(rs.getTimestamp("fingreso_tabulacion")));
				  }
				  if(rs.getDate("fsalida_tabulacion")!=null){
					  res.setFsalida_tabulacion_operacion(format3.format(rs.getTimestamp("fsalida_tabulacion")));
				  }
				  if(rs.getDate("fingreso_entrega")!=null){
					  res.setFingreso_entrega_operacion(format3.format(rs.getTimestamp("fingreso_entrega")));
				  }
				  if(rs.getDate("fsalida_entrega")!=null){
					  res.setFsalida_entrega_operacion(format3.format(rs.getTimestamp("fsalida_entrega")));
				  }
				  
				re.add(res);  
			  }
			  
			  
			  return re;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	
		
	}
	@Override
	public ArrayList<ObjEstudio> getListResponsableByIdEstudio(int id, String lang){
		ArrayList<ObjEstudio> result = new ArrayList<ObjEstudio>();
		
		return result;
	}
	@Override
	public ArrayList<ObjEstudio> getListEstudioWorkFlow15(int id, String lang){
		ArrayList<ObjEstudio> result = new ArrayList<ObjEstudio>();
		
		ArrayList<ObjEstudio> result1 = this.getListEstudioByUserResAsig(1,id,lang);
		ArrayList<ObjEstudio> result2 = this.getListEstudioByUserResAsig(2,id,lang);
		ArrayList<ObjEstudio> result3 = this.getListEstudioByUserResAsig(3,id,lang);
		ArrayList<ObjEstudio> result4 = this.getListEstudioByUserAsig(id, lang, 0, 0);
		
		for(ObjEstudio li: result1 ){
			result.add(li);
		}
		for(ObjEstudio li2: result2 ){
			result.add(li2);
		}
		for(ObjEstudio li3: result3 ){
			result.add(li3);
		}
		for(ObjEstudio li4: result4 ){
			result.add(li4);
		}

		
		return result;
	}
	@Override
	public ArrayList<ObjEstudio> getListEstudioByUser(int id, String lang){
		ArrayList<ObjEstudio> result = new ArrayList<ObjEstudio>();
		
		ArrayList<ObjEstudio> result1 = this.getListEstudioByUserResAsig(1,id,lang);
		ArrayList<ObjEstudio> result2 = this.getListEstudioByUserResAsig(2,id,lang);
		ArrayList<ObjEstudio> result3 = this.getListEstudioByUserResAsig(3,id,lang);
		ArrayList<ObjEstudio> result4 = this.getListEstudioByUserAsig(id, lang, 0, 0);
		
		for(ObjEstudio li: result1 ){
			result.add(li);
		}
		for(ObjEstudio li2: result2 ){
			result.add(li2);
		}
		for(ObjEstudio li3: result3 ){
			result.add(li3);
		}
		for(ObjEstudio li4: result4 ){
			result.add(li4);
		}

		
		return result;
	}
	@Override
	public ArrayList<ObjEstudio> getListEstudioByUserResAsig(int idUser, int User, String lang){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessIndustria inds = (AccessIndustria) context.getBean("AccessIndustria");
		
		ArrayList<ObjEstudio> re = new ArrayList<ObjEstudio>();
		
		Connection conn = null;
		
		String query="SELECT "
					 +" p.id_proyectom "
					 +" ,p.codigo_proyectom "
					 +" ,p.estado_proyectom "
					 +" ,c.id_cotizacion "
					 +" ,c.codigo_cotizacion "
					 +" ,o.id_operacion "
				     +" ,o.id_cotizacion "
				     +" ,o.canal_venta "
				     +" ,o.cod_sam "
				     +" ,o.tipo_sam "
				     +" ,o.id_sap "
				     +" ,o.cod_operacion "
				     +" ,o.cod_manager "
				     +" ,o.cod_cotizacion "
				     +" ,o.cod_crm "
				     +" ,o.id_cliente "
				     +" ,cli.nombre_cliente "
				     +" ,cli.rut_cliente "
				     +" ,o.area_medicion "
				     +" ,o.sector_medicion "
				     +" ,s.nombre_sector "
				     +" ,o.industria_medicion "
				     +" ,i.des_ind "
				     +" ,o.id_tipo_entrevista "
				     +" ,o.nombre_operacion "
				     +" ,o.estado_medicion "
				     +" ,f.nombre_flujo "
				     +" ,e.id_etapa "
				     +" ,e.nombre_etapa "
				     +" ,o.orden_medicion "
				     +" ,o.cola_operacion "
				     +" ,o.priori_operacion "
				     +" ,o.activa_operacion "
				     +" ,o.fcrea_medicion "
				     +" ,o.screa_medicion "
				     +" ,o.factivacion_medicion "
				     +" ,o.sactivacion_medicion "
				     +" ,o.fmod_medicion "
				     +" ,o.smod_medicion "
				     +" ,o.felimina_medicion "
				     +" ,o.selimina_medicion "
				     +" ,o.elimina_medicion "
				     +" ,o.fingreso_puesta_marcha "
				     +" ,o.fsalida_puesta_marcha "
				     +" ,o.fingreso_implementacion "
				     +" ,o.fsalida_implementacion "
				     +" ,o.fingreso_recoleccion "
				     +" ,o.fsalida_recoleccion "
				     +" ,o.fingreso_codificacion "
				     +" ,o.fsalida_codificacion "
				     +" ,o.fingreso_digitacion "
				     +" ,o.fsalida_digitacion "
				     +" ,o.fingreso_depuracion "
				     +" ,o.fsalida_depuracion "
				     +" ,o.fingreso_tabulacion "
				     +" ,o.fsalida_tabulacion "
				     +" ,o.fingreso_entrega "
				     +" ,o.fsalida_entrega "
					 +" ,m.res_us1_op "
				     +" ,m.num_entrevistas_op"
					 +" ,(us1.nombre_user)as nom1 "
					 +" ,(us1.app_user)as app1 "
					 +" ,m.res_us2_op "
					 +" ,(us2.nombre_user)as nom2 "
					 +" ,(us2.app_user)as app2 "
					 +" ,m.res_us3_op "
					 +" ,(us3.nombre_user)as nom3 "
					 +" ,(us3.app_user)as app3 "
					 +" ,m.num_entrevistas_op "
					 +" ,es.nombre_estado "
					 +" ,(te.des_detalle)as nombre_tipo_entrevista "
					 +" ,o.end_operacion "
				  +" FROM man_proyecto_manager_medicion o "
				  +" INNER JOIN man_proyecto_manager_principal p on o.id_proyectom = p.id_proyectom "
				  +" INNER JOIN man_proyecto_manager_cotizacion c on o.id_cotizacion = c.id_cotizacion "
				  +" INNER JOIN man_proyecto_manager m on m.id_operacion = o.id_operacion "
				  +" INNER JOIN man_cliente cli on o.id_cliente = cli.id_cliente "
				  +" LEFT JOIN man_sector s on s.id_sector = o.sector_medicion "
				  +" LEFT JOIN man_industria i on o.industria_medicion = i.id_ind "
				  +" LEFT JOIN man_flujo f on o.cola_operacion = f.id_flujo "
				  +" LEFT JOIN man_estados es on o.estado_medicion = es.cod_estado "
				  +" LEFT JOIN man_etapas e on e.id_etapa = f.id_etapa "
				  +" LEFT JOIN man_combo_box_detalle te on te.valor_detalle = o.id_tipo_entrevista AND te.id_combo = 5 "
				  +" LEFT JOIN man_login_user us1 on us1.id_user = m.res_us1_op "
				  +" LEFT JOIN man_login_user us2 on us2.id_user = m.res_us2_op "
				  +" LEFT JOIN man_login_user us3 on us3.id_user = m.res_us3_op ";
				  
				  if(idUser == 1){query = query+"  WHERE m.res_us1_op= "+User ;}
				  if(idUser == 2){query = query+"  WHERE m.res_us2_op= "+User ;}
				  if(idUser == 3){query = query+"  WHERE m.res_us3_op= "+User ;}
				  
				  query = query + "ORDER BY o.id_operacion DESC ";
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  ObjEstudio res = new ObjEstudio();
				  res.setId_proyectom(rs.getInt("id_proyectom"));
				  res.setCodigo_proyectom(rs.getString("codigo_proyectom"));
				  res.setEstado_proyectom(rs.getInt("estado_proyectom"));
				  res.setNum_entrevistas_op(rs.getInt("num_entrevistas_op"));
				  res.setId_cotizacion(rs.getInt("id_cotizacion"));
				  res.setCodigo_cotizacion(rs.getString("codigo_cotizacion"));
				  res.setId_operacion(rs.getInt("id_operacion"));
				  res.setCanal_venta(rs.getInt("canal_venta"));
				  res.setCod_sam(rs.getString("cod_sam"));
				  res.setTipo_sam(rs.getString("tipo_sam"));
				  res.setId_sap(rs.getString("id_sap"));
				  res.setId_crm(rs.getString("cod_crm"));
				  res.setCod_operacion(rs.getInt("cod_operacion"));
				  res.setCod_manager(rs.getString("cod_manager"));
				  res.setId_cliente(rs.getInt("id_cliente"));
				  res.setStr_cliente(rs.getString("nombre_cliente"));
				  res.setRut_cliente(rs.getString("rut_cliente"));
				  res.setArea_medicion(rs.getInt("area_medicion"));
				  res.setSector_medicion(rs.getInt("sector_medicion"));
				  res.setStr_sector_medicion(rs.getString("nombre_sector"));
				  res.setIndustria_medicion(rs.getInt("industria_medicion"));
				  res.setStr_industria_medicion(inds.getNombreIndustriaById(rs.getInt("industria_medicion"), lang));
				  res.setId_tipo_entrevista(rs.getString("id_tipo_entrevista"));
				  res.setStr_id_tipo_entrevista(rs.getString("nombre_tipo_entrevista"));
				  res.setNombre_operacion(rs.getString("nombre_operacion"));
				  res.setEstado_medicion(rs.getInt("estado_medicion"));
				  res.setStr_estado_medicion(rs.getString("nombre_estado"));
				  res.setId_etapa(rs.getInt("id_etapa"));
				  res.setNombre_etapa(rs.getString("nombre_etapa"));
				  res.setOrden_medicion(rs.getInt("estado_medicion"));
				  res.setCola_operacion(rs.getInt("cola_operacion"));
				  res.setStr_cola_operacion(rs.getString("nombre_flujo"));
				  res.setPriori_operacion(rs.getInt("priori_operacion"));
				  res.setActiva_operacion(rs.getInt("activa_operacion"));
				  res.setRes_us1_op(rs.getInt("res_us1_op"));
				  res.setStr_res_us1_op(rs.getString("nom1") +" "+rs.getString("app1"));
				  res.setRes_us2_op(rs.getInt("res_us2_op"));
				  res.setStr_res_us2_op(rs.getString("nom2") +" "+rs.getString("app2"));
				  res.setRes_us3_op(rs.getInt("res_us1_op"));
				  res.setStr_res_us3_op(rs.getString("nom3") +" "+rs.getString("app3"));
				  res.setEnd_operacion(rs.getInt("end_operacion"));
				  
				  if(rs.getDate("fcrea_medicion")!= null){
					  res.setFcreacion_proyectom(format3.format(rs.getTimestamp("fcrea_medicion")));
					  res.setScreacion_proyectom(rs.getInt("screa_medicion"));
					  res.setStr_screacion(logins.getStrLoginById(rs.getInt("screa_medicion")));
				  }
				  if(rs.getDate("factivacion_medicion")!= null){
					  res.setFactivacion_medicion(format3.format(rs.getTimestamp("factivacion_medicion")));
					  res.setSactivacion_medicion(rs.getInt("sactivacion_medicion"));
					  res.setStr_sactivacion_medicion(logins.getStrLoginById(rs.getInt("sactivacion_medicion")));
				  }
				  if(rs.getDate("fmod_medicion")!= null){
					  res.setFmod_proyectom(format3.format(rs.getTimestamp("fmod_medicion")));
					  res.setSmod_proyectom(rs.getInt("smod_medicion"));
					  res.setStr_smodp(logins.getStrLoginById(rs.getInt("smod_medicion")));
				  }
				  if(rs.getDate("felimina_medicion")!= null){
					  res.setFelimina_proyectom(format3.format(rs.getTimestamp("felimina_medicion")));
					  res.setSelimina_proyectom(rs.getInt("selimina_medicion"));
					  res.setStr_estado_medicion(logins.getStrLoginById(rs.getInt("selimina_medicion")));
				  }
				  
				  res.setElimina_proyectom(rs.getInt("elimina_medicion"));
				  
				  if(rs.getDate("fingreso_puesta_marcha")!=null){
					  res.setFingreso_puesta_marcha_operacion(format3.format(rs.getTimestamp("fingreso_puesta_marcha")));
				  }
				  if(rs.getDate("fsalida_puesta_marcha")!=null){
					  res.setFsalida_puesta_marcha_operacion(format3.format(rs.getTimestamp("fsalida_puesta_marcha")));
				  }
				  if(rs.getDate("fingreso_implementacion")!=null){
					  res.setFingreso_implementacion_operacion(format3.format(rs.getTimestamp("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!=null){
					  res.setFsalida_implementacion_operacion(format3.format(rs.getTimestamp("fsalida_implementacion")));
				  }
				  if(rs.getDate("fingreso_recoleccion")!=null){
					  res.setFingreso_recoleccion_operacion(format3.format(rs.getTimestamp("fingreso_recoleccion")));
				  }
				  if(rs.getDate("fsalida_recoleccion")!=null){
					  res.setFsalida_recoleccion_operacion(format3.format(rs.getTimestamp("fsalida_recoleccion")));
				  }
				  if(rs.getDate("fingreso_codificacion")!=null){
					  res.setFingreso_codificacion_operacion(format3.format(rs.getTimestamp("fingreso_codificacion")));
				  }
				  if(rs.getDate("fsalida_codificacion")!=null){
					  res.setFsalida_codificacion_operacion(format3.format(rs.getTimestamp("fsalida_codificacion")));
				  }
				  if(rs.getDate("fingreso_digitacion")!=null){
					  res.setFingreso_digitacion_operacion(format3.format(rs.getTimestamp("fingreso_digitacion")));
				  }
				  if(rs.getDate("fsalida_digitacion")!=null){
					  res.setFsalida_digitacion_operacion(format3.format(rs.getTimestamp("fsalida_digitacion")));
				  }
				  if(rs.getDate("fingreso_depuracion")!=null){
					  res.setFingreso_depuracion_operacion(format3.format(rs.getTimestamp("fingreso_depuracion")));
				  }
				  if(rs.getDate("fsalida_depuracion")!=null){
					  res.setFsalida_depuracion_operacion(format3.format(rs.getTimestamp("fsalida_depuracion")));
				  }
				  if(rs.getDate("fingreso_tabulacion")!=null){
					  res.setFingreso_tabulacion_operacion(format3.format(rs.getTimestamp("fingreso_tabulacion")));
				  }
				  if(rs.getDate("fsalida_tabulacion")!=null){
					  res.setFsalida_tabulacion_operacion(format3.format(rs.getTimestamp("fsalida_tabulacion")));
				  }
				  if(rs.getDate("fingreso_entrega")!=null){
					  res.setFingreso_entrega_operacion(format3.format(rs.getTimestamp("fingreso_entrega")));
				  }
				  if(rs.getDate("fsalida_entrega")!=null){
					  res.setFsalida_entrega_operacion(format3.format(rs.getTimestamp("fsalida_entrega")));
				  }
				  
				re.add(res);  
			  }
			  
			  
			  return re;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	
		
	}
	
	@Override
	public ArrayList<ObjEstudio> getListEstudioByUserAsig(int user, String lang,int flujo, int resp){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessIndustria inds = (AccessIndustria) context.getBean("AccessIndustria");
		
		ArrayList<ObjEstudio> re = new ArrayList<ObjEstudio>();
		
		Connection conn = null;
		
		String query="SELECT "
					 +" p.id_proyectom "
					 +" ,p.codigo_proyectom "
					 +" ,p.estado_proyectom "
					 +" ,c.id_cotizacion "
					 +" ,c.codigo_cotizacion "
					 +" ,o.id_operacion "
				     +" ,o.id_cotizacion "
				     +" ,o.canal_venta "
				     +" ,o.cod_sam "
				     +" ,o.tipo_sam "
				     +" ,o.id_sap "
				     +" ,o.cod_operacion "
				     +" ,o.cod_manager "
				     +" ,o.cod_cotizacion "
				     +" ,o.cod_crm "
				     +" ,o.id_cliente "
				     +" ,cli.nombre_cliente "
				     +" ,cli.rut_cliente "
				     +" ,o.area_medicion "
				     +" ,o.sector_medicion "
				     +" ,s.nombre_sector "
				     +" ,o.industria_medicion "
				     +" ,i.des_ind "
				     +" ,o.id_tipo_entrevista "
				     +" ,o.nombre_operacion "
				     +" ,o.estado_medicion "
				     +" ,f.nombre_flujo "
				     +" ,e.id_etapa "
				     +" ,e.nombre_etapa "
				     +" ,o.orden_medicion "
				     +" ,o.cola_operacion "
				     +" ,o.priori_operacion "
				     +" ,o.activa_operacion "
				     +" ,o.fcrea_medicion "
				     +" ,o.screa_medicion "
				     +" ,o.factivacion_medicion "
				     +" ,o.sactivacion_medicion "
				     +" ,o.fmod_medicion "
				     +" ,o.smod_medicion "
				     +" ,o.felimina_medicion "
				     +" ,o.selimina_medicion "
				     +" ,o.elimina_medicion "
				     +" ,o.fingreso_puesta_marcha "
				     +" ,o.fsalida_puesta_marcha "
				     +" ,o.fingreso_implementacion "
				     +" ,o.fsalida_implementacion "
				     +" ,o.fingreso_recoleccion "
				     +" ,o.fsalida_recoleccion "
				     +" ,o.fingreso_codificacion "
				     +" ,o.fsalida_codificacion "
				     +" ,o.fingreso_digitacion "
				     +" ,o.fsalida_digitacion "
				     +" ,o.fingreso_depuracion "
				     +" ,o.fsalida_depuracion "
				     +" ,o.fingreso_tabulacion "
				     +" ,o.fsalida_tabulacion "
				     +" ,o.fingreso_entrega "
				     +" ,o.fsalida_entrega "
					 +" ,m.res_us1_op "
				     +" ,m.num_entrevistas_op"
					 +" ,(us1.nombre_user)as nom1 "
					 +" ,(us1.app_user)as app1 "
					 +" ,m.res_us2_op "
					 +" ,(us2.nombre_user)as nom2 "
					 +" ,(us2.app_user)as app2 "
					 +" ,m.res_us3_op "
					 +" ,(us3.nombre_user)as nom3 "
					 +" ,(us3.app_user)as app3 "
					 +" ,m.num_entrevistas_op "
					 +" ,es.nombre_estado "
					 +" ,(te.des_detalle)as nombre_tipo_entrevista "
					 +" ,o.end_operacion "
				  +" FROM man_proyecto_manager_medicion o "
				  +" INNER JOIN man_proyecto_manager_principal p on o.id_proyectom = p.id_proyectom "
				  +" INNER JOIN man_proyecto_manager_cotizacion c on o.id_cotizacion = c.id_cotizacion "
				  +" INNER JOIN man_proyecto_manager m on m.id_operacion = o.id_operacion "
				  +" INNER JOIN man_cliente cli on o.id_cliente = cli.id_cliente "
				  +" LEFT JOIN man_sector s on s.id_sector = o.sector_medicion "
				  +" LEFT JOIN man_industria i on o.industria_medicion = i.id_ind "
				  +" LEFT JOIN man_flujo f on o.cola_operacion = f.id_flujo "
				  +" LEFT JOIN man_estados es on o.estado_medicion = es.cod_estado "
				  +" LEFT JOIN man_etapas e on e.id_etapa = f.id_etapa "
				  +" LEFT JOIN man_combo_box_detalle te on te.valor_detalle = o.id_tipo_entrevista AND te.id_combo = 5 "
				  +" LEFT JOIN man_login_user us1 on us1.id_user = m.res_us1_op "
				  +" LEFT JOIN man_login_user us2 on us2.id_user = m.res_us2_op "
				  +" LEFT JOIN man_login_user us3 on us3.id_user = m.res_us3_op "
				  +" LEFT JOIN man_proyecto_manager_user_asig usa on usa.id_operacion = m.id_operacion ";
				  
				  if(user != 0 || flujo != 0 || resp != 0){
					  query = query+"  WHERE " ;
				  }
				  if(user != 0){
					  query = query+" usa.id_user= "+ user + " ";
				  }
				  if(user != 0 && flujo != 0){
					  query = query+" AND ";
				  }
				  if(flujo != 0){
					  query = query+" usa.cod_flujo= "+ flujo + " ";
				  }
				  if(resp != 0 && flujo != 0){
					  query = query+" AND ";
				  }
				  if(resp != 0){
					  query = query+" usa.id_respons= "+ resp + " ";
				  }
				  
				 
				  
				  query = query + "ORDER BY o.id_operacion DESC ";
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  ObjEstudio res = new ObjEstudio();
				  res.setId_proyectom(rs.getInt("id_proyectom"));
				  res.setCodigo_proyectom(rs.getString("codigo_proyectom"));
				  res.setEstado_proyectom(rs.getInt("estado_proyectom"));
				  res.setNum_entrevistas_op(rs.getInt("num_entrevistas_op"));
				  res.setId_cotizacion(rs.getInt("id_cotizacion"));
				  res.setCodigo_cotizacion(rs.getString("codigo_cotizacion"));
				  res.setId_operacion(rs.getInt("id_operacion"));
				  res.setCanal_venta(rs.getInt("canal_venta"));
				  res.setCod_sam(rs.getString("cod_sam"));
				  res.setTipo_sam(rs.getString("tipo_sam"));
				  res.setId_sap(rs.getString("id_sap"));
				  res.setId_crm(rs.getString("cod_crm"));
				  res.setCod_operacion(rs.getInt("cod_operacion"));
				  res.setCod_manager(rs.getString("cod_manager"));
				  res.setId_cliente(rs.getInt("id_cliente"));
				  res.setStr_cliente(rs.getString("nombre_cliente"));
				  res.setRut_cliente(rs.getString("rut_cliente"));
				  res.setArea_medicion(rs.getInt("area_medicion"));
				  res.setSector_medicion(rs.getInt("sector_medicion"));
				  res.setStr_sector_medicion(rs.getString("nombre_sector"));
				  res.setIndustria_medicion(rs.getInt("industria_medicion"));
				  res.setStr_industria_medicion(inds.getNombreIndustriaById(rs.getInt("industria_medicion"), lang));
				  res.setId_tipo_entrevista(rs.getString("id_tipo_entrevista"));
				  res.setStr_id_tipo_entrevista(rs.getString("nombre_tipo_entrevista"));
				  res.setNombre_operacion(rs.getString("nombre_operacion"));
				  res.setEstado_medicion(rs.getInt("estado_medicion"));
				  res.setStr_estado_medicion(rs.getString("nombre_estado"));
				  res.setId_etapa(rs.getInt("id_etapa"));
				  res.setNombre_etapa(rs.getString("nombre_etapa"));
				  res.setOrden_medicion(rs.getInt("estado_medicion"));
				  res.setCola_operacion(rs.getInt("cola_operacion"));
				  res.setStr_cola_operacion(rs.getString("nombre_flujo"));
				  res.setPriori_operacion(rs.getInt("priori_operacion"));
				  res.setActiva_operacion(rs.getInt("activa_operacion"));
				  res.setRes_us1_op(rs.getInt("res_us1_op"));
				  res.setStr_res_us1_op(rs.getString("nom1") +" "+rs.getString("app1"));
				  res.setRes_us2_op(rs.getInt("res_us2_op"));
				  res.setStr_res_us2_op(rs.getString("nom2") +" "+rs.getString("app2"));
				  res.setRes_us3_op(rs.getInt("res_us1_op"));
				  res.setStr_res_us3_op(rs.getString("nom3") +" "+rs.getString("app3"));
				  res.setEnd_operacion(rs.getInt("end_operacion"));
				  
				  if(rs.getDate("fcrea_medicion")!= null){
					  res.setFcreacion_proyectom(format3.format(rs.getTimestamp("fcrea_medicion")));
					  res.setScreacion_proyectom(rs.getInt("screa_medicion"));
					  res.setStr_screacion(logins.getStrLoginById(rs.getInt("screa_medicion")));
				  }
				  if(rs.getDate("factivacion_medicion")!= null){
					  res.setFactivacion_medicion(format3.format(rs.getTimestamp("factivacion_medicion")));
					  res.setSactivacion_medicion(rs.getInt("sactivacion_medicion"));
					  res.setStr_sactivacion_medicion(logins.getStrLoginById(rs.getInt("sactivacion_medicion")));
				  }
				  if(rs.getDate("fmod_medicion")!= null){
					  res.setFmod_proyectom(format3.format(rs.getTimestamp("fmod_medicion")));
					  res.setSmod_proyectom(rs.getInt("smod_medicion"));
					  res.setStr_smodp(logins.getStrLoginById(rs.getInt("smod_medicion")));
				  }
				  if(rs.getDate("felimina_medicion")!= null){
					  res.setFelimina_proyectom(format3.format(rs.getTimestamp("felimina_medicion")));
					  res.setSelimina_proyectom(rs.getInt("selimina_medicion"));
					  res.setStr_estado_medicion(logins.getStrLoginById(rs.getInt("selimina_medicion")));
				  }
				  
				  res.setElimina_proyectom(rs.getInt("elimina_medicion"));
				  
				  if(rs.getDate("fingreso_puesta_marcha")!=null){
					  res.setFingreso_puesta_marcha_operacion(format3.format(rs.getTimestamp("fingreso_puesta_marcha")));
				  }
				  if(rs.getDate("fsalida_puesta_marcha")!=null){
					  res.setFsalida_puesta_marcha_operacion(format3.format(rs.getTimestamp("fsalida_puesta_marcha")));
				  }
				  if(rs.getDate("fingreso_implementacion")!=null){
					  res.setFingreso_implementacion_operacion(format3.format(rs.getTimestamp("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!=null){
					  res.setFsalida_implementacion_operacion(format3.format(rs.getTimestamp("fsalida_implementacion")));
				  }
				  if(rs.getDate("fingreso_recoleccion")!=null){
					  res.setFingreso_recoleccion_operacion(format3.format(rs.getTimestamp("fingreso_recoleccion")));
				  }
				  if(rs.getDate("fsalida_recoleccion")!=null){
					  res.setFsalida_recoleccion_operacion(format3.format(rs.getTimestamp("fsalida_recoleccion")));
				  }
				  if(rs.getDate("fingreso_codificacion")!=null){
					  res.setFingreso_codificacion_operacion(format3.format(rs.getTimestamp("fingreso_codificacion")));
				  }
				  if(rs.getDate("fsalida_codificacion")!=null){
					  res.setFsalida_codificacion_operacion(format3.format(rs.getTimestamp("fsalida_codificacion")));
				  }
				  if(rs.getDate("fingreso_digitacion")!=null){
					  res.setFingreso_digitacion_operacion(format3.format(rs.getTimestamp("fingreso_digitacion")));
				  }
				  if(rs.getDate("fsalida_digitacion")!=null){
					  res.setFsalida_digitacion_operacion(format3.format(rs.getTimestamp("fsalida_digitacion")));
				  }
				  if(rs.getDate("fingreso_depuracion")!=null){
					  res.setFingreso_depuracion_operacion(format3.format(rs.getTimestamp("fingreso_depuracion")));
				  }
				  if(rs.getDate("fsalida_depuracion")!=null){
					  res.setFsalida_depuracion_operacion(format3.format(rs.getTimestamp("fsalida_depuracion")));
				  }
				  if(rs.getDate("fingreso_tabulacion")!=null){
					  res.setFingreso_tabulacion_operacion(format3.format(rs.getTimestamp("fingreso_tabulacion")));
				  }
				  if(rs.getDate("fsalida_tabulacion")!=null){
					  res.setFsalida_tabulacion_operacion(format3.format(rs.getTimestamp("fsalida_tabulacion")));
				  }
				  if(rs.getDate("fingreso_entrega")!=null){
					  res.setFingreso_entrega_operacion(format3.format(rs.getTimestamp("fingreso_entrega")));
				  }
				  if(rs.getDate("fsalida_entrega")!=null){
					  res.setFsalida_entrega_operacion(format3.format(rs.getTimestamp("fsalida_entrega")));
				  }
				 
				re.add(res);  
			  }
			  
			  
			  return re;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	
		
	}
	
	@Override
	public int getIdOperacionByUid(String uuid){
		int id = 0;
		Connection conn = null;
		
		String query = " SELECT id_operacion "
				  +"	 FROM man_proyecto_manager_medicion "
				  +"	 WHERE "
				  +" uid_operacion = '" + uuid +"' ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  id = rs.getInt("id_operacion");
			  }
			  
			  return id;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int getIdOperacionDetalleByUid(int idDetalle){
		int id = 0;
		Connection conn = null;
		
		String query = " SELECT id_manager "
				  +"	 FROM man_proyecto_manager "
				  +"	 WHERE "
				  +" id_operacion = '" + idDetalle +"' ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  id = rs.getInt("id_manager");
			  }
			  
			  return id;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int getCodEstudioByUid(int id){
		int id2 = 0;
		Connection conn = null;
		
		String query = " SELECT cod_operacion "
					   +"	FROM man_proyecto_manager_medicion m "
					   +"	INNER JOIN man_proyecto_manager c ON m.id_operacion = c.id_operacion "
					   +"	WHERE c.id_operacion =" + id ;
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  id2 = rs.getInt("cod_operacion") + 1;
			  }
			  
			  return id2;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int getCountEstudios(){
		int id2 = 0;
		
		Connection conn = null;
		
		String query = " SELECT COUNT(cod_cotizacion)as result   "
					   +"	FROM man_proyecto_manager_medicion  "
					   +"	WHERE elimina_medicion = 0 "
					   + "  AND cod_operacion <> 0 " ;
		
		try {
			  String aux = "";
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();	
			  if (rs.next()) {  
				  id2 = rs.getInt("result");
				  logger.debug("Count: "+ id2);
			  }
			  
			  return id2;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public ObjResultCreaCotOp setOperacion(ObjEstudio estudio){
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" INSERT INTO man_proyecto_manager_medicion "
			         +"  (id_cotizacion "
			         +"  ,id_proyectom "
			         +"  ,canal_venta "
			         +"  ,cod_sam "
			         +"  ,tipo_sam "
			         +"  ,id_sap "
			         +"  ,cod_operacion "
			         +"  ,cod_manager "
			         +"  ,cod_cotizacion "
			         +"  ,cod_crm "
			         +"  ,uid_operacion "
			         +"  ,id_cliente "
			         +"  ,area_medicion "
			         +"  ,sector_medicion "
			         +"  ,industria_medicion "
			         +"  ,id_tipo_entrevista "
			         +"  ,nombre_operacion "
			         +"  ,estado_medicion "
			         +"  ,orden_medicion "
			         +"  ,cola_operacion "
			         +"  ,priori_operacion "
			         +"  ,activa_operacion "
			         +"  ,fcrea_medicion "
			         +"  ,screa_medicion "
			         +"  ,fmod_medicion "
			         +"  ,smod_medicion "
			         +"  ,elimina_medicion "
			         +"  ,end_operacion "
			         +"  ) "
			     +" VALUES "
			     +"      ("+ estudio.getId_cotizacion()+"  "
			     +"      ,"+estudio.getId_proyectom()+" "
			     +"      ,"+estudio.getCanal_venta()+" "
			     +"      ,'"+estudio.getCod_sam()+"'  "
			     +"      ,'"+estudio.getTipo_sam()+"'  "
			     +"      ,'"+estudio.getId_sap()+"'  "
			     +"      ,"+estudio.getCod_operacion()+"  "
			     +"      ,'"+estudio.getCod_manager()+"'  "
			     +"      ,"+estudio.getCodigo_cotizacion()+"  "
			     +"      ,'"+estudio.getId_crm()+"'  "
			     +"      ,'"+estudio.getUid_operacion()+"'  "
			     +"      ,"+estudio.getId_cliente()+"  "
			     +"      ,"+estudio.getArea_medicion()+"  "
			     +"      ,"+estudio.getSector_medicion()+"  "
			     +"      ,"+estudio.getIndustria_medicion()+"  "
			     +"      ,'"+estudio.getId_tipo_entrevista()+"'  "
			     +"      ,'"+estudio.getNombre_operacion()+"'  "
			     +"      ,'"+estudio.getEstado_medicion()+"'  "
			     +"      ,"+estudio.getOrden_medicion()+"  "
			     +"      ,"+estudio.getCola_operacion()+"  "
			     +"      ,"+estudio.getPriori_operacion()+"  "
			     +"      ,"+estudio.getActiva_operacion()+"  "
			     
			     +"      ,'"+estudio.getFcreacion_proyectom()+"'  "
			     +"      ,"+estudio.getScreacion_proyectom()+"  "
			     +"      ,'"+estudio.getFmod_proyectom()+"'  "
			     +"      ,"+estudio.getSmod_proyectom()+"  "
			     +"      ,"+estudio.getElimina_proyectom()+"  "
			     +"      ,0  "
			     +"      ) ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(this.getIdOperacionByUid(estudio.getUid_operacion()));
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public ObjResultCreaCotOp setDetalleOperacion(ObjEstudio estudio){
		
		ObjEstudioDetalle det = estudio.getDetalle();
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" INSERT INTO man_proyecto_manager "
			         +"  (id_operacion "
			         +"  ,id_geografia "
			         +"  ,cant_paises "
			         +"  ,id_clie_facturar "
			         +"  ,tipo_estudio "
			         +"  ,num_entrevistas_op "
			         +"  ,precio_venta "
			         +"  ,por_rebate "
			         +"  ,cot_por_inc "
			         +"  ,cot_dur_entrevista "
			         +"  ,booking_legal_entity "
			         +"  ,centro_costo_op "
			         +"  ,por_ejec_estudio "
			         +"  ,digital_op "
			         +"  ,moneda_op "
			         +"  ,date_prob_in_est_op "
			         +"  ,date_prob_entre_est_op "
			         +"  ,ddate_pres_of_equipo_op "
			         +"  ,ddate_pres_gps_op "
			         +"  ,ddate_pres_clie_op "
			         +"  ,res_us1_op "
			         +"  ,res_us2_op "
			         +"  ,res_us3_op "
			         +"  ,obj_obs_op "
			         +"  ,desc_op "
			         +"  ,set_up_1 "
			         +"  ,set_up_2 "
			         +"  ,set_up_3 "
			         +"  ,set_up_4 "
			         +"  ,set_up_5 "
			         +"  ,set_up_6 "
			         +"  ,set_up_7 "
			         +"  ,set_up_8 "
			         +"  ,set_up_9 "
			         +"  ,set_up_10 "
			         +"  ,set_up_11 "
			         +"  ,entre_1 "
			         +"  ,entre_2 "
			         +"  ,entre_3 "
			         +"  ,entre_4 "
			         +"  ,entre_5 "
			         +"  ,entre_6) "
			     +" VALUES "
			     +"      ("+estudio.getId_operacion()+" "
			     +"      ,'"+det.getId_geografia()+"' "
			     +"      ,"+det.getCant_paises()+" "
			     +"      ,"+det.getId_clie_facturar()+" "
			     +"      ,'"+det.getTipo_estudio()+"' "
			     +"      ,"+det.getNum_entrevistas_op()+" "
			     +"      ,'"+det.getPrecio_venta()+"' "
			     +"      ,"+det.getPor_rebate()+" "
			     +"      ,"+det.getCot_por_inc()+" "
			     +"      ,"+det.getCot_dur_entrevista()+" " 
			     +"      ,'"+det.getBooking_legal_entity()+"' " 
			     +"      ,'"+det.getCentro_costo_op()+"' "
			     +"      ,"+det.getPor_ejec_estudio()+" "
			     +"      ,'"+det.getDigital_op() +"' "
			     +"      ,'"+det.getMoneda_op() +"' "
			     +"      ,'"+det.getDate_prob_in_est_op() +"' "
			     +"      ,'"+det.getDate_prob_in_est_op() +"' "
			     +"      ,'"+det.getDdate_pres_of_equipo_op() +"' "
			     +"      ,'"+det.getDdate_pres_gps_op() +"' "
			     +"      ,'"+det.getDdate_pres_clie_op()+"' "
			     +"      ,"+ det.getRes_us1_op()+" "
			     +"      ,"+ det.getRes_us2_op()+" "
			     +"      ,"+ det.getRes_us3_op()+" "
			     +"      ,'"+det.getObj_obs_op()+"' "
			     +"      ,'"+det.getDesc_op()+"' "
			     +"      ,'"+det.getSet_up_1()+"' "
			     +"      ,'"+det.getSet_up_2()+"' "
			     +"      ,'"+det.getSet_up_3()+"' "
			     +"      ,'"+det.getSet_up_4()+"' "
			     +"      ,'"+det.getSet_up_5()+"' "
			     +"      ,'"+det.getSet_up_6()+"' "
			     +"      ,'"+det.getSet_up_7()+"' "
			     +"      ,'"+det.getSet_up_8()+"' "
			     +"      ,'"+det.getSet_up_9()+"' "
			     +"      ,'"+det.getSet_up_10()+"' "
			     +"      ,'"+det.getSet_up_11()+"' "
			     +"      ,'"+det.getEntre_1()+"' "
			     +"      ,'"+det.getEntre_2()+"' "
			     +"      ,'"+det.getEntre_3()+"' "
			     +"      ,'"+det.getEntre_4()+"' "
			     +"      ,'"+det.getEntre_5()+"' "
			     +"      ,'"+det.getEntre_6()+"' ) ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_detalle_operacion(this.getIdOperacionDetalleByUid(estudio.getId_operacion()));
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int updateOperacion(ObjEstudio det){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_proyecto_manager_medicion "
					  +"   SET  ";
		if(det.getId_proyectom() != 0){ query = query + " id_proyectom = '"+det.getId_proyectom()+"' "; }			  
		if(det.getId_sap() != null){ query = query + " ,id_sap = '"+det.getId_sap()+"' "; }
		if(det.getId_crm() != null){ query = query +" ,cod_crm = '"+det.getId_crm()+"' "; }		
		if(det.getId_cliente() != 0){ query = query +" ,id_cliente = "+det.getId_cliente()+" "; }				  
		if(det.getSector_medicion() != 0){ query = query +" ,sector_medicion = "+det.getSector_medicion()+" "; }			  
		if(det.getIndustria_medicion() != 0){ query = query +" ,industria_medicion = "+det.getIndustria_medicion()+" "; }				  
		if(det.getId_tipo_entrevista() != null){ query = query +" ,id_tipo_entrevista = '"+det.getId_tipo_entrevista()+"' "; }				 
		if(det.getNombre_operacion() != null){ query = query +" ,nombre_operacion = '"+det.getNombre_operacion()+"' "; }				  
		
		
		query = query +" WHERE id_operacion ="+ det.getId_operacion() +" ";
						
		try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				logger.debug(query);
				ps.executeUpdate();
					
					result = 1;
				
				return result;
				
	    	} catch (SQLException e) {
	    		//return 2;
				throw new RuntimeException(e);
				
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {}
				}
			}
	}
	@Override
	public int updateDetalleOperacion(ObjEstudioDetalle det){
		int result = 0;
		Connection conn = null;
		String query = "UPDATE man_proyecto_manager "
						+"   SET "
						+"   id_operacion ="+det.getId_operacion()+" "; 
						if(det.getId_geografia() != null){ query = query +" ,id_geografia = "+det.getId_geografia()+" ";}
						if(String.valueOf(det.getCant_paises()) != null){  query = query +" ,cant_paises = "+det.getCant_paises()+" ";}
						if(String.valueOf(det.getId_clie_facturar()) != null){  query = query +" ,id_clie_facturar = "+det.getId_clie_facturar()+" ";}
						if(det.getTipo_estudio() != null){  query = query +" ,tipo_estudio = '"+det.getTipo_estudio()+"' ";}
						if(det.getNum_entrevistas_op() != 0){ query = query +" ,num_entrevistas_op = "+det.getNum_entrevistas_op()+" ";}
						if(String.valueOf(det.getPrecio_venta()) != "0.0" || String.valueOf(det.getPrecio_venta()) != null){ query = query +" ,precio_venta = '"+det.getPrecio_venta()+"' ";}
						if(det.getPor_rebate() != 0){ query = query +" ,por_rebate = "+det.getPor_rebate()+" ";}
						if(det.getCot_por_inc() != 0){ query = query +" ,cot_por_inc = "+det.getCot_por_inc()+" ";}
						if(det.getCot_dur_intro_entrevista() != 0){ query = query +" ,cot_dur_intro_entrevista = "+det.getCot_dur_intro_entrevista()+" ";}
						if(det.getCot_dur_cuest_entrevista() != 0){ query = query +"  ,cot_dur_cuest_entrevista = "+det.getCot_dur_cuest_entrevista()+" ";}
						if(det.getCot_dur_entrevista() != 0){ query = query +" ,cot_dur_entrevista = "+det.getCot_dur_entrevista()+" ";}
						if(det.getBooking_legal_entity() != null){ query = query +" ,booking_legal_entity = '"+det.getBooking_legal_entity()+"' ";}
						if(det.getCentro_costo_op() != null){ query = query +" ,centro_costo_op = '"+det.getCentro_costo_op()+"' ";}
						if(det.getPor_ejec_estudio() != 0){ query = query +"  ,por_ejec_estudio = "+det.getPor_ejec_estudio()+" ";}
						if(det.getDigital_op() != null){ query = query +" ,digital_op = '"+det.getDigital_op()+"' ";}
						if(det.getMoneda_op() != null){ query = query +" ,moneda_op = '"+det.getMoneda_op()+"' ";}
						if(det.getDate_prob_in_est_op() != null){ query = query +" ,date_prob_in_est_op = '"+det.getDate_prob_in_est_op() +"' ";}
						if(det.getDate_prob_entre_est_op() != null){ query = query +" ,date_prob_entre_est_op = '"+det.getDate_prob_entre_est_op()+"' ";}
						if(det.getDdate_pres_of_equipo_op() != null){ query = query +" ,ddate_pres_of_equipo_op = '"+det.getDdate_pres_of_equipo_op()+"' ";}
						if(det.getDdate_pres_gps_op() != null){ query = query +" ,ddate_pres_gps_op = '"+det.getDdate_pres_gps_op()+"' ";}
						if(det.getDdate_pres_clie_op() != null){ query = query +" ,ddate_pres_clie_op = '"+det.getDdate_pres_clie_op()+"' ";}
						if(det.getRes_us1_op() != 0){ query = query +"  ,res_us1_op = "+det.getRes_us1_op()+" ";}
						if(det.getRes_us2_op() != 0){ query = query +"  ,res_us2_op = "+det.getRes_us2_op()+" ";}
						if(det.getRes_us3_op() != 0){ query = query +"  ,res_us3_op = "+det.getRes_us3_op()+" ";}
						if(det.getObj_obs_op() != null){ query = query +"  ,obj_obs_op = '"+det.getObj_obs_op()+"' ";}
						if(det.getDesc_op() != null){ query = query +"     ,desc_op = '"+det.getDesc_op()+"' ";}
						if(det.getSet_up_1() != null){ query = query +"    ,set_up_1 = '"+det.getSet_up_1()+"' ";}
						if(det.getSet_up_2() != null){ query = query +"    ,set_up_2 = '"+det.getSet_up_2()+"' ";}
						if(det.getSet_up_3() != null){ query = query +"    ,set_up_3 = '"+det.getSet_up_3()+"' ";}
						if(det.getSet_up_4() != null){ query = query +"    ,set_up_4 = '"+det.getSet_up_4()+"' ";}
						if(det.getSet_up_5() != null){ query = query +"    ,set_up_5 = '"+det.getSet_up_5()+"' ";}
						if(det.getSet_up_6() != null){ query = query +"    ,set_up_6 = '"+det.getSet_up_6()+"' ";}
						if(det.getSet_up_7() != null){ query = query +"    ,set_up_7 = '"+det.getSet_up_7()+"' ";}
						if(det.getSet_up_8() != null){ query = query +"    ,set_up_8 = '"+det.getSet_up_8()+"' ";}
						if(det.getSet_up_9() != null){ query = query +"    ,set_up_9 = '"+det.getSet_up_9()+"' ";}
						if(det.getSet_up_10() != null){ query = query +"   ,set_up_10 = '"+det.getSet_up_10()+"' ";}
						if(det.getSet_up_11() != null){ query = query +"   ,set_up_11 = '"+det.getSet_up_11()+"' ";}
						if(det.getEntre_1() != null){ query = query +"      ,entre_1 = '"+det.getEntre_1() +"' ";}
						if(det.getEntre_2() != null){ query = query +"      ,entre_2 = '"+det.getEntre_2() +"' ";}
						if(det.getEntre_3() != null){ query = query +"      ,entre_3 = '"+det.getEntre_3() +"' ";}
						if(det.getEntre_4() != null){ query = query +"      ,entre_4 = '"+det.getEntre_4() +"' ";}
						if(det.getEntre_5() != null){ query = query +"      ,entre_5 = '"+det.getEntre_5() +"' ";}
						if(det.getEntre_6() != null){ query = query +"      ,entre_6 = '"+det.getEntre_6() +"' ";}
						if(det.getModo_cot() != null){ query = query +"      ,modo_cot = '"+det.getModo_cot() +"' ";}
						if(det.getSubmodo_cot() != null){ query = query +"      ,submodo_cot = '"+det.getSubmodo_cot() +"' ";}
						if(det.getDur_intro_reco() != 0){ query = query +"  ,dur_intro_reco = "+det.getDur_intro_reco()+" ";}
						if(det.getDur_cuest_reco() != 0){ query = query +"  ,dur_cuest_reco = "+det.getDur_cuest_reco()+" ";}
						if(det.getTotal_entr_reco() != 0){ query = query +"  ,total_entr_reco = "+det.getTotal_entr_reco()+" ";}
						if(det.getPor_supervision() != 0){ query = query +"  ,por_supervision = "+det.getPor_supervision()+" ";}
						if(det.getPor_re_digitacion() != 0){ query = query +"  ,por_re_digitacion = "+det.getPor_re_digitacion()+" ";}
						if(det.getCrea_libro_codigo() != null){ query = query +"      ,crea_libro_codigo = '"+det.getCrea_libro_codigo() +"' ";}
						
						 query = query + " WHERE " 
						 +" id_operacion ="+det.getId_operacion()+" ";
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	@Override
	public ObjEstudioDetalle getDetalleOperacionByUid(int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		ObjEstudioDetalle det = new ObjEstudioDetalle();
		Connection conn = null;
		
		String query = " SELECT o.id_manager "
					   +"   ,o.id_operacion "
					   +"   ,o.id_geografia "
					   +"   ,o.cant_paises "
					   +"   ,o.id_clie_facturar " 
					   +"   ,o.tipo_estudio "
					   +"   ,o.num_entrevistas_op "
					   +"   ,o.precio_venta "
					   +"   ,o.precio_venta_uf_manager "
					   +"   ,o.muestra_manager "
					   +"   ,o.canal_manager "
					   +"   ,o.id_area_recopilacion "
					   +"   ,o.id_canal_recopilacion "
					   +"   ,o.por_rebate "
					   +"   ,o.cot_por_inc "
					   +"   ,o.cot_dur_intro_entrevista "
					   +"   ,o.cot_dur_cuest_entrevista "
					   +"   ,o.cot_dur_entrevista "
					   +"   ,o.booking_legal_entity "
					   +"   ,o.centro_costo_op "
					   +"   ,o.por_ejec_estudio "
					   +"   ,o.digital_op "
					   +"   ,o.moneda_op "
					   +"   ,o.date_prob_in_est_op "
					   +"   ,o.date_prob_entre_est_op "
					   +"   ,o.ddate_pres_of_equipo_op "
					   +"   ,o.ddate_pres_gps_op "
					   +"   ,o.ddate_pres_clie_op "
					   +"   ,o.res_us1_op "
					   +"   ,o.res_us2_op "
					   +"   ,o.res_us3_op "
					   +"   ,o.obj_obs_op "
					   +"   ,o.desc_op "
					   +"   ,o.set_up_1 "
					   +"   ,o.set_up_2 "
					   +"   ,o.set_up_3 "
					   +"   ,o.set_up_4 "
					   +"   ,o.set_up_5 "
					   +"   ,o.set_up_6 "
					   +"   ,o.set_up_7 "
					   +"   ,o.set_up_8 "
					   +"   ,o.set_up_9 "
					   +"   ,o.set_up_10 "
					   +"   ,o.set_up_11 "
					   +"   ,o.entre_1 "
					   +"   ,o.entre_2 "
					   +"   ,o.entre_3 "
					   +"   ,o.entre_4 "
					   +"   ,o.entre_5 "
					   +"   ,o.entre_6 "
					   +"   ,o.modo_cot "
					   +"   ,o.submodo_cot "
					   +"   ,o.dur_intro_reco "
					   +"   ,o.dur_cuest_reco "
					   +"   ,o.total_entr_reco "
					   +"   ,o.por_supervision "
					   +"   ,o.por_re_digitacion "
					   +"   ,o.crea_libro_codigo "
					   +" ,(c2.nombre_cliente) as nombre_cliente_factura "
					   +" FROM man_proyecto_manager o "
					   +" LEFT JOIN man_cliente c2 ON c2.id_cliente = o.id_clie_facturar "
					   +" WHERE id_operacion = " + id +" ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  det.setId_manager(rs.getInt("id_manager"));
				  det.setId_operacion(id);
				  det.setId_geografia(rs.getString("id_geografia"));
				  det.setCant_paises(rs.getInt("cant_paises"));
				  det.setId_clie_facturar(rs.getInt("id_clie_facturar"));
				  det.setStr_clie_facturar(rs.getString("nombre_cliente_factura"));
				  det.setTipo_estudio(rs.getString("tipo_estudio"));
				  det.setNum_entrevistas_op(rs.getInt("num_entrevistas_op"));
				  det.setPrecio_venta(rs.getFloat("precio_venta"));
				  det.setMuestra_manager(rs.getInt("muestra_manager"));
				  det.setCanal_manager(rs.getInt("canal_manager"));
				  det.setId_area_recopilacion(rs.getInt("id_area_recopilacion"));
				  det.setId_canal_recopilacion(rs.getInt("id_canal_recopilacion"));
				  det.setPor_rebate(rs.getInt("por_rebate"));
				  det.setCot_por_inc(rs.getInt("cot_por_inc"));
				  det.setCot_dur_intro_entrevista(rs.getInt("cot_dur_intro_entrevista"));
				  det.setDur_cuest_reco(rs.getInt("cot_dur_cuest_entrevista"));
				  det.setCot_dur_entrevista(rs.getInt("cot_dur_entrevista"));
				  det.setBooking_legal_entity(rs.getString("booking_legal_entity"));
				  det.setCentro_costo_op(rs.getString("centro_costo_op"));
				  det.setPor_ejec_estudio(rs.getInt("por_ejec_estudio"));
				  det.setDigital_op(rs.getString("digital_op"));
				  det.setMoneda_op(rs.getString("moneda_op"));
				  det.setDate_prob_in_est_op(format4.format(rs.getDate("date_prob_in_est_op")));
				  det.setDate_prob_entre_est_op(format4.format(rs.getDate("date_prob_entre_est_op")));
				  det.setDdate_pres_of_equipo_op(format4.format(rs.getDate("ddate_pres_of_equipo_op")));
				  det.setDdate_pres_gps_op(format4.format(rs.getDate("ddate_pres_gps_op")));
				  det.setDdate_pres_clie_op(format4.format(rs.getDate("ddate_pres_clie_op")));
				  det.setRes_us1_op(rs.getInt("res_us1_op"));
				  det.setStr_res_us1_op(logins.getStrNameLoginById(rs.getInt("res_us1_op")));
				  det.setRes_us2_op(rs.getInt("res_us2_op"));
				  det.setStr_res_us2_op(logins.getStrNameLoginById(rs.getInt("res_us2_op")));
				  det.setRes_us3_op(rs.getInt("res_us3_op"));
				  det.setStr_res_us3_op(logins.getStrNameLoginById(rs.getInt("res_us3_op")));
				  det.setObj_obs_op(rs.getString("obj_obs_op"));
				  det.setDesc_op(rs.getString("desc_op"));
				  det.setSet_up_1(rs.getString("set_up_1"));
				  det.setSet_up_2(rs.getString("set_up_2"));
				  det.setSet_up_3(rs.getString("set_up_3"));
				  det.setSet_up_4(rs.getString("set_up_4"));
				  det.setSet_up_5(rs.getString("set_up_5"));
				  det.setSet_up_6(rs.getString("set_up_6"));
				  det.setSet_up_7(rs.getString("set_up_7"));
				  det.setSet_up_8(rs.getString("set_up_8"));
				  det.setSet_up_9(rs.getString("set_up_9"));
				  det.setSet_up_10(rs.getString("set_up_10"));
				  det.setSet_up_11(rs.getString("set_up_11"));
				  det.setEntre_1(rs.getString("entre_1"));
				  det.setEntre_2(rs.getString("entre_2"));
				  det.setEntre_3(rs.getString("entre_3"));
				  det.setEntre_4(rs.getString("entre_4"));
				  det.setEntre_5(rs.getString("entre_5"));
				  det.setEntre_6(rs.getString("entre_6"));
				  det.setModo_cot(rs.getString("modo_cot"));
				  det.setSubmodo_cot(rs.getString("submodo_cot"));
				  det.setPor_supervision(rs.getInt("por_supervision"));
				  det.setPor_re_digitacion(rs.getInt("por_re_digitacion"));
				  det.setCrea_libro_codigo(rs.getString("crea_libro_codigo"));
				  
			  }
			  
			  return det;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	
/** ///////////////////////////////////////////WORK FLOW /////////////////////////////////////////////////////////////////////////////////////////////////////////**/
	@Override
	public int updateActivateOperacion(ObjEstudio est){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_proyecto_manager_medicion "
						+" SET " 
						+"    activa_operacion = '"+est.getActiva_operacion()+"' "
						+"    ,factivacion_medicion = '"+est.getFmod_proyectom()+"' "
						+"	 ,sactivacion_medicion = "+est.getSmod_proyectom()+" "
						+"	WHERE "
						+"	id_operacion = " + est.getId_operacion();
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	
	@Override
	public int updateLastUpdateOperacion(ObjEstudio est){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_proyecto_manager_medicion "
						+" SET " 
						+"    fmod_medicion = '"+est.getFmod_proyectom()+"' "
						+"	 ,smod_medicion = "+est.getSmod_proyectom()+" "
						+"		 WHERE "
						+"		 id_operacion = " + est.getId_operacion();
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	@Override
	public int updatePriorityOperacion(ObjEstudio est){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_proyecto_manager_medicion "
						+" SET " 
						+"    priori_operacion = "+est.getPriori_operacion()+ " "
						+"   ,fmod_medicion = '"+est.getFmod_proyectom()+"' "
						+"	 ,smod_medicion = "+est.getSmod_proyectom()+" "
						+"		 WHERE "
						+"		 id_operacion = " + est.getId_operacion();
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	@Override
	public int updateColaEstadoOperacion(ObjEstudio est){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_proyecto_manager_medicion "
						+" SET " 
						+"    cola_operacion = "+est.getCola_operacion()+ " "
						+"   ,estado_medicion =  "+est.getEstado_medicion()
						+"   ,fmod_medicion = '"+est.getFmod_proyectom()+"' "
						+"	 ,smod_medicion = "+est.getSmod_proyectom()+" "
						+"	  WHERE "
						+"	  id_operacion = " + est.getId_operacion();
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	@Override
	public int updateColaOperacion(ObjEstudio est){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_proyecto_manager_medicion "
						+" SET " 
						+"    cola_operacion = "+est.getCola_operacion()+ " "
						+"   ,fmod_medicion = '"+est.getFmod_proyectom()+"' "
						+"	 ,smod_medicion = "+est.getSmod_proyectom()+" "
						+"	  WHERE "
						+"	  id_operacion = " + est.getId_operacion();
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	@Override
	public int deleteOperacion(ObjEstudio est){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_proyecto_manager_medicion "
						+" SET " 
						+"    estado_medicion =  "+est.getEstado_medicion()
						+"   ,felimina_medicion = '"+est.getFmod_proyectom()+"' "
						+"	 ,selimina_medicion = "+est.getSmod_proyectom()+" "
						+"	 ,elimina_medicion = "+est.getElimina_proyectom()+" "
						+"	  WHERE "
						+"	  id_operacion = " + est.getId_operacion();
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	@Override
	public int updateDateCompromisoOperacion(ObjEstudio est){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_proyecto_manager_medicion "
						+" SET " 
						+"    fcom_ini_campo = '"+est.getFcom_ini_campo()+"' "
						+"    fcom_fin_campo = '"+est.getFcom_fin_campo()+"' "
						+"	 ,fcom_ini_bbdd = "+est.getFcom_ini_bbdd()+" "
						+"	 ,fcom_fin_bbdd = "+est.getFcom_fin_bbdd()+" "
						+"	 ,fcom_entrega = "+est.getFcom_entrega()+" "
						+"	WHERE "
						+"	id_operacion = " + est.getId_operacion();
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	@Override
	public int updateEndOperacion(ObjEstudio est){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_proyecto_manager_medicion "
						+" SET " 
						+"    end_operacion = "+est.getEnd_operacion()+ " "
						+"   ,fmod_medicion = '"+est.getFmod_proyectom()+"' "
						+"	 ,smod_medicion = "+est.getSmod_proyectom()+" "
						+"		 WHERE "
						+"		 id_operacion = " + est.getId_operacion();
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	@Override
	public int updateOperacionFechasEtapa(ObjEstudio det){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_proyecto_manager_medicion "
					  +"   SET  ";
		if(det.getFingreso_puesta_marcha_operacion() != null){ query = query + " fingreso_puesta_marcha = '"+det.getFingreso_puesta_marcha_operacion()+"' "; }			  
		if(det.getFsalida_puesta_marcha_operacion() != null){ query = query + " fsalida_puesta_marcha = '"+det.getFsalida_puesta_marcha_operacion()+"' "; }
		if(det.getFingreso_implementacion_operacion() != null){ query = query + " fingreso_implementacion = '"+det.getFingreso_implementacion_operacion()+"' "; }			  
		if(det.getFsalida_implementacion_operacion() != null){ query = query + " fsalida_implementacion = '"+det.getFsalida_implementacion_operacion()+"' "; }
		if(det.getFingreso_recoleccion_operacion() != null){ query = query + " fingreso_recoleccion = '"+det.getFingreso_recoleccion_operacion()+"' "; }			  
		if(det.getFsalida_recoleccion_operacion() != null){ query = query + " fsalida_recoleccion = '"+det.getFsalida_recoleccion_operacion()+"' "; }
		if(det.getFingreso_codificacion_operacion() != null){ query = query + " fingreso_codificacion = '"+det.getFingreso_codificacion_operacion()+"' "; }			  
		if(det.getFsalida_codificacion_operacion() != null){ query = query + " fsalida_codificacion = '"+det.getFsalida_codificacion_operacion()+"' "; }
		if(det.getFingreso_digitacion_operacion() != null){ query = query + " fingreso_digitacion = '"+det.getFingreso_digitacion_operacion()+"' "; }			  
		if(det.getFsalida_digitacion_operacion() != null){ query = query + " fsalida_digitacion = '"+det.getFsalida_digitacion_operacion()+"' "; }
		if(det.getFingreso_depuracion_operacion() != null){ query = query + " fingreso_depuracion = '"+det.getFingreso_depuracion_operacion()+"' "; }			  
		if(det.getFsalida_depuracion_operacion() != null){ query = query + " fsalida_depuracion = '"+det.getFsalida_depuracion_operacion()+"' "; }
		if(det.getFingreso_tabulacion_operacion() != null){ query = query + " fingreso_tabulacion = '"+det.getFingreso_tabulacion_operacion()+"' "; }			  
		if(det.getFsalida_tabulacion_operacion() != null){ query = query + " fsalida_tabulacion = '"+det.getFsalida_tabulacion_operacion()+"' "; }
		if(det.getFingreso_entrega_operacion() != null){ query = query + " fingreso_entrega = '"+det.getFingreso_entrega_operacion()+"' "; }			  
		if(det.getFsalida_entrega_operacion() != null){ query = query + " fsalida_entrega = '"+det.getFsalida_entrega_operacion()+"' "; }
		if(det.getFcom_ini_campo() != null){ query = query + " fcom_ini_campo = '"+det.getFcom_ini_campo()+"' "; }			  
		if(det.getFcom_fin_campo() != null){ query = query + " fcom_fin_campo = '"+det.getFcom_fin_campo()+"' "; }
		if(det.getFcom_ini_bbdd() != null){ query = query + " fcom_ini_bbdd = '"+det.getFcom_ini_bbdd()+"' "; }			  
		if(det.getFcom_fin_bbdd() != null){ query = query + " fcom_fin_bbdd = '"+det.getFcom_fin_bbdd()+"' "; }
		if(det.getFcom_entrega() != null){ query = query + " fcom_entrega = '"+det.getFcom_entrega()+"' "; }			  
		
		
		query = query +" WHERE id_operacion ="+ det.getId_operacion() +" ";
						
		try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				logger.debug(query);
				ps.executeUpdate();
					
					result = 1;
				
				return result;
				
	    	} catch (SQLException e) {
	    		//return 2;
				throw new RuntimeException(e);
				
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {}
				}
			}
	}
	
	/*************************************************************************/
	/* Valor Cadena Workflow */
	@Override
	public String getValueValorCadenaWorkflow(int idOp, int idAct){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		String result = "";
		Connection conn = null;
		
		String query = " SELECT id_operacion "
						+"      ,id_actividad "
						+"      ,valor_actividad  "
						+"  FROM man_valor_cadena_workflow "
					   +" WHERE id_operacion = " + idOp +" "
					   +" AND id_actividad = "+ idAct;
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  result = rs.getString("valor_actividad");
				  
			  }
			  
			  return result;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int setValueValorCadenaWorkflow(int idOp, int idAct, String value){
		int result = 0;
		Connection conn = null;
		String query = " INSERT INTO man_valor_cadena_workflow "
					   +"        (id_operacion "
					   +"        ,id_actividad "
					   +"        ,valor_actividad) "
					   +"  VALUES "
					   +"        ("+ idOp +" "
					   +"        ,"+ idAct + " "
					   +"        ,'"+value+"') " ;
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	@Override
	public int updateValueValorCadenaWorkflow(int idOp, int idAct, String value){
		int result = 0;
		Connection conn = null;
		String query = " UPDATE man_valor_cadena_workflow "
					  +"	   SET valor_actividad = '"+value+"' "
					  +"	 WHERE id_operacion = "+idOp+" AND id_actividad = "+idAct+" " ;
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	@Override
	public int deleteValueValorCadenaWorkflow(int idOp, int idAct){
		int result = 0;
		Connection conn = null;
		String query = " DELETE FROM dbo.man_valor_cadena_workflow "
						+" WHERE  id_operacion = "+idOp+" AND id_actividad = "+idAct+" " ;
						 		
						 try {
								conn = dataSource.getConnection();
								PreparedStatement ps = conn.prepareStatement(query);
								logger.debug(query);
								ps.executeUpdate();
									
									result = 1;
								
								return result;
								
					    	} catch (SQLException e) {
					    		//return 2;
								throw new RuntimeException(e);
								
							} finally {
								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e) {}
								}
							}
	}
	
	@Override
	public int createActivityPuestaMarcha(int idOp){
			int result = 0;
				
				this.setValueValorCadenaWorkflow(idOp, 4, "PTE");
				this.setValueValorCadenaWorkflow(idOp, 5, "PTE");
				this.setValueValorCadenaWorkflow(idOp, 6, "PTE");
				this.setValueValorCadenaWorkflow(idOp, 7, "PTE");
				this.setValueValorCadenaWorkflow(idOp, 8, "PTE");
				this.setValueValorCadenaWorkflow(idOp, 9, "PTE");
				this.setValueValorCadenaWorkflow(idOp, 10, "PTE");
				this.setValueValorCadenaWorkflow(idOp, 11, "PTE");
			
			return result;
	}
/**************************** CODIFICACION ****************************************************************************************************************************/
	@Override
	public ObjEstudioCodificacion getCodificacionOperacionByUid(int id){
		ObjEstudioCodificacion cod = new ObjEstudioCodificacion();
		Connection conn = null;
		
		String query = " SELECT id_codi "
					   +"   ,id_operacion "
					   +"   ,pre_codigo_codi "
					   +"   ,preg_ab_codi "
					   +"   ,preg_otro_codi "
					   +"   ,cod_ab_ent_clie_codi "
					   +"   ,traducir_codi "
					   +"   ,lang_codi "
					   +"   ,editar_codi "
					   +"   ,nivel_edi_codi "
					   +"   ,otra_info_codi "
					   +"   ,tmo_codi "
					 +" FROM man_proyecto_manager_codificacion "
					 +" WHERE id_operacion = " + id +" ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  cod.setId_codi(rs.getInt("id_codi"));
				  cod.setId_operacion(id);
				  cod.setPre_codigo_codi(rs.getString("pre_codigo_codi"));
				  cod.setPreg_ab_codi(rs.getInt("preg_ab_codi"));
				  cod.setPreg_otro_codi(rs.getInt("preg_otro_codi"));
				  cod.setCod_ab_ent_clie_codi(rs.getString("cod_ab_ent_clie_codi"));
				  cod.setTraducir_codi(rs.getString("traducir_codi"));
				  cod.setLang_codi(rs.getString("lang_codi"));
				  cod.setEditar_codi(rs.getString("editar_codi"));
				  cod.setNivel_edi_codi(rs.getString("nivel_edi_codi"));
				  cod.setOtra_info_codi(rs.getString("otra_info_codi"));
				  cod.setTmo_codi(rs.getInt("tmo_codi"));
			  }
			  
			  return cod;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public ObjResultCreaCotOp setCodificacionOperacion(ObjEstudioCodificacion cod){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" INSERT INTO man_proyecto_manager_codificacion "
				      +"     (id_operacion "
				      +"     ,pre_codigo_codi "
				      +"     ,preg_ab_codi "
				      +"     ,preg_otro_codi "
				      +"     ,cod_ab_ent_clie_codi "
				      +"     ,traducir_codi "
				      +"     ,lang_codi "
				      +"     ,editar_codi "
				      +"     ,nivel_edi_codi "
				      +"     ,otra_info_codi) "
				      +" VALUES "
				      +"     ("+cod.getId_operacion()+"  "
				      +"     ,'"+cod.getPre_codigo_codi()+"' "
				      +"     ,"+cod.getPreg_ab_codi()+" "
				      +"     ,"+cod.getPreg_otro_codi()+" "
				      +"     ,'"+cod.getCod_ab_ent_clie_codi()+"' "
				      +"     ,'"+cod.getTraducir_codi()+"' "
				      +"     ,'"+cod.getLang_codi()+"' "
				      +"     ,'"+cod.getEditar_codi()+"' "
				      +"     ,'"+cod.getNivel_edi_codi()+"' "
				      +"     ,'"+cod.getOtra_info_codi()+"') ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(cod.getId_operacion());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public ObjResultCreaCotOp updateCodificacionOperacion(ObjEstudioCodificacion cod){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" UPDATE man_proyecto_manager_codificacion "
					+"	   SET id_operacion = "+cod.getId_operacion()+" ";
					if(cod.getPre_codigo_codi() != null){ query = query +"	      ,pre_codigo_codi = '"+cod.getPre_codigo_codi()+"' "; }
					if(cod.getPreg_ab_codi() != 0){ query = query +"	      ,preg_ab_codi = "+cod.getPreg_ab_codi()+" "; }
					if(cod.getPreg_otro_codi() != 0){ query = query +"	      ,preg_otro_codi = "+cod.getPreg_otro_codi()+" "; }
					if(cod.getCod_ab_ent_clie_codi() != null){ query = query +",cod_ab_ent_clie_codi = '"+cod.getCod_ab_ent_clie_codi()+"' "; }
					if(cod.getTraducir_codi() != null){ query = query +"	      ,traducir_codi = '"+cod.getTraducir_codi()+"' "; }
					if(cod.getLang_codi() != null){ query = query +"	      ,lang_codi = '"+cod.getLang_codi()+"' "; }
					if(cod.getEditar_codi() != null){ query = query +"	      ,editar_codi = '"+cod.getEditar_codi()+"' "; }
					if(cod.getNivel_edi_codi() != null){ query = query +"	      ,nivel_edi_codi = '"+cod.getNivel_edi_codi()+"' "; }
					if(cod.getOtra_info_codi() != null){ query = query +"	      ,otra_info_codi = '"+cod.getOtra_info_codi()+"' "; }
					query = query +"	 WHERE id_operacion = "+ cod.getId_operacion();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(cod.getId_operacion());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int deleteCodificacionOperacion(int id){
		
		
		
		int result = 0;
		
		Connection conn = null;
		String query=" DELETE FROM man_proyecto_manager_codificacion "
					+"	WHERE id_operacion = "+ id ;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
/**************************** DIGITACION ****************************************************************************************************************************/
	@Override
	public ObjEstudioDigitacion getDigitacionOperacionByUid(int id){
		ObjEstudioDigitacion dig = new ObjEstudioDigitacion();
		Connection conn = null;
		
		String query = " SELECT id_dig "
					+"	      ,id_operacion "
					+"	      ,verbatim_capture "
					+"	  FROM man_proyecto_manager_digitacion "
					+ " WHERE id_operacion = " + id +" ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  dig.setId_dig(rs.getInt("id_dig"));
				  dig.setId_operacion(id);
				  dig.setVerbatim_capture(rs.getString("verbatim_capture"));
			  }
			  
			  return dig;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public ObjResultCreaCotOp setDigitacionOperacion(ObjEstudioDigitacion dig){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" INSERT INTO man_proyecto_manager_digitacion "
					 +"          (id_operacion "
					 +"          ,verbatim_capture) "
					 +"    VALUES "
					 +"          ("+dig.getId_operacion()+" "
					 +"          ,'"+dig.getVerbatim_capture()+"') ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(dig.getId_operacion());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public ObjResultCreaCotOp updateDigitacionOperacion(ObjEstudioDigitacion dig){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" UPDATE man_proyecto_manager_digitacion "
					 +"  SET id_operacion = "+dig.getId_operacion()+" ";
					 if(dig.getVerbatim_capture()!= null){ query = query+ "     ,verbatim_capture = '"+dig.getVerbatim_capture()+"' ";}
					 query = query+" WHERE id_operacion = " + dig.getId_operacion();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(dig.getId_operacion());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int deleteDigitacionOperacion(int id){
		
		
		
		int result = 0;
		
		Connection conn = null;
		String query=" DELETE FROM man_proyecto_manager_digitacion "
					+"	WHERE id_operacion = "+ id ;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
/**************************** TABULACION ****************************************************************************************************************************/
	@Override
	public ObjEstudioTabulacion getTabulacionOperacionByUid(int id){
		ObjEstudioTabulacion tab = new ObjEstudioTabulacion();
		Connection conn = null;
		
		String query = " SELECT id_tab "
					   +"   ,id_operacion "
					   +"   ,esp_datos_entr_tab "
					   +"   ,format_file_tab "
					   +"   ,tabla_prem_tab "
					   +"   ,cuadro_ten_tab "
					   +"   ,tend_tabla_perio_tab "
					   +"   ,num_est_banner_tab "
					   +"   ,anexar_datotos_tab "
					   +"   ,prueba_stat_tab "
					   +"   ,pondera_tab "
					   +"   ,frec_entrega_datos_tab "
					   +"   ,frec_tabla_datos_tab "
					   +"   ,esp_req_tab "
					   +"   ,otra_info_tab "
					   +"   ,test_stat_tab "
					   +"   ,ponderacion_tab "
					   +"   ,ferc_entrega_file_datos_tab "
					   +"   ,frec_tabla_entrega_tab "
					 +" FROM man_proyecto_manager_tabulacion "
					 +" WHERE id_operacion = " + id +" ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				 tab.setId_tab(rs.getInt("id_tab")); 
				 tab.setId_operacion(id);
				 tab.setEsp_datos_entr_tab(rs.getString("esp_datos_entr_tab"));
				 tab.setFormat_file_tab(rs.getString("format_file_tab"));
				 tab.setTabla_prem_tab(rs.getString("tabla_prem_tab"));
				 tab.setCuadro_ten_tab(rs.getString("cuadro_ten_tab"));
				 tab.setTend_tabla_perio_tab(rs.getString("tend_tabla_perio_tab"));
				 tab.setNum_est_banner_tab(rs.getInt("num_est_banner_tab"));
				 tab.setAnexar_datotos_tab(rs.getString("anexar_datotos_tab"));
				 tab.setPrueba_stat_tab(rs.getString("prueba_stat_tab"));
				 tab.setPondera_tab(rs.getString("pondera_tab"));
				 tab.setFrec_entrega_datos_tab(rs.getString("frec_entrega_datos_tab"));
				 tab.setFrec_tabla_datos_tab(rs.getString("frec_tabla_datos_tab"));
				 tab.setEsp_req_tab(rs.getString("esp_req_tab"));
				 tab.setOtra_info_tab(rs.getString("otra_info_tab"));
				 tab.setTest_stat_tab(rs.getString("test_stat_tab"));
				 tab.setPondera_tab(rs.getString("ponderacion_tab"));
				 tab.setFerc_entrega_file_datos_tab(rs.getString("ferc_entrega_file_datos_tab"));
				 tab.setFrec_tabla_entrega_tab(rs.getString("frec_tabla_entrega_tab"));
			  }
			  
			  return tab;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public ObjResultCreaCotOp setTabulacionOperacion(ObjEstudioTabulacion tab){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" INSERT INTO man_proyecto_manager_tabulacion "
				     +"      (id_operacion "
				     +"      ,esp_datos_entr_tab "
				     +"      ,format_file_tab "
				     +"      ,tabla_prem_tab "
				     +"      ,cuadro_ten_tab "
				     +"      ,tend_tabla_perio_tab "
				     +"      ,num_est_banner_tab "
				     +"      ,anexar_datotos_tab "
				     +"      ,prueba_stat_tab "
				     +"      ,pondera_tab "
				     +"      ,frec_entrega_datos_tab "
				     +"      ,frec_tabla_datos_tab "
				     +"      ,esp_req_tab "
				     +"      ,otra_info_tab "
				     +"      ,test_stat_tab "
				     +"      ,ponderacion_tab "
				     +"      ,ferc_entrega_file_datos_tab "
				     +"      ,frec_tabla_entrega_tab) "
				    +" VALUES "
				    +"       ("+tab.getId_operacion()+" "
				    +"       ,'"+tab.getEsp_datos_entr_tab()+"' "
				    +"       ,'"+tab.getFormat_file_tab()+"' "
				    +"       ,'"+tab.getTabla_prem_tab()+"' "
				    +"       ,'"+tab.getTabla_prem_tab()+"' "
				    +"       ,'"+tab.getTend_tabla_perio_tab()+"' "
				    +"       ,"+tab.getNum_est_banner_tab()+" "
				    +"       ,'"+tab.getAnexar_datotos_tab()+"' "
				    +"       ,'"+tab.getPrueba_stat_tab()+"' "
				    +"       ,'"+tab.getPondera_tab()+"' "
				    +"       ,'"+tab.getFrec_entrega_datos_tab()+"' "
				    +"       ,'"+tab.getFrec_tabla_datos_tab()+"' "
				    +"       ,'"+tab.getEsp_req_tab()+"' "
				    +"       ,'"+tab.getOtra_info_tab()+"' "
				    +"       ,'"+tab.getTest_stat_tab()+"' "
				    +"       ,'"+tab.getPonderacion_tab()+"' "
				    +"       ,'"+tab.getFerc_entrega_file_datos_tab()+"' "
				    +"       ,'"+tab.getFrec_tabla_entrega_tab()+"') "; 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(tab.getId_operacion());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public ObjResultCreaCotOp updateTabulacionOperacion(ObjEstudioTabulacion tab){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" UPDATE man_proyecto_manager_tabulacion "
					 +"  SET id_operacion = "+tab.getId_operacion()+" ";
					 if(tab.getEsp_datos_entr_tab() != null){ query = query +" ,esp_datos_entr_tab = '"+tab.getEsp_datos_entr_tab()+"' ";}
					 if(tab.getFormat_file_tab() != null){ query = query +"     ,format_file_tab = '"+tab.getFormat_file_tab()+"' ";}
					 if(tab.getTabla_prem_tab() != null){ query = query +"     ,tabla_prem_tab = '"+tab.getTabla_prem_tab()+"' ";}
					 if(tab.getCuadro_ten_tab() != null){ query = query +"     ,cuadro_ten_tab = '"+tab.getCuadro_ten_tab()+"' ";}
					 if(tab.getTend_tabla_perio_tab() != null){ query = query +"     ,tend_tabla_perio_tab = '"+tab.getTend_tabla_perio_tab()+"' ";}
					 if(tab.getNum_est_banner_tab() != 0){ query = query +"     ,num_est_banner_tab = "+tab.getNum_est_banner_tab()+" ";}
					 if(tab.getAnexar_datotos_tab() != null){ query = query +"     ,anexar_datotos_tab = '"+tab.getAnexar_datotos_tab()+"' ";}
					 if(tab.getPrueba_stat_tab() != null){ query = query +"     ,prueba_stat_tab = '"+tab.getPrueba_stat_tab()+"' ";}
					 if(tab.getPondera_tab() != null){ query = query +"     ,pondera_tab = '"+tab.getPondera_tab()+"' ";}
					 if(tab.getFrec_entrega_datos_tab() != null){ query = query +"     ,frec_entrega_datos_tab = '"+tab.getFrec_entrega_datos_tab()+"' ";}
					 if(tab.getFrec_tabla_datos_tab() != null){ query = query +"     ,frec_tabla_datos_tab = '"+tab.getFrec_tabla_datos_tab()+"' ";}
					 if(tab.getEsp_req_tab() != null){ query = query +"     ,esp_req_tab = '"+tab.getEsp_req_tab()+"' ";}
					 if(tab.getOtra_info_tab() != null){ query = query +"     ,otra_info_tab = '"+tab.getOtra_info_tab()+"' ";}
					 if(tab.getTest_stat_tab() != null){ query = query +"     ,test_stat_tab = '"+tab.getTest_stat_tab()+"' ";}
					 if(tab.getPonderacion_tab() != null){ query = query +"     ,ponderacion_tab = '"+tab.getPonderacion_tab()+"' ";}
					 if(tab.getFerc_entrega_file_datos_tab() != null){ query = query +"     ,ferc_entrega_file_datos_tab = '"+tab.getFerc_entrega_file_datos_tab()+"' ";}
					 if(tab.getFrec_tabla_entrega_tab() != null){ query = query +"     ,frec_tabla_entrega_tab = '"+tab.getFrec_tabla_entrega_tab()+"' ";}
					 query = query +" WHERE id_operacion = " + tab.getId_operacion();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(tab.getId_operacion());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int deleteTabulacionOperacion(int id){
		
		
		
		int result = 0;
		
		Connection conn = null;
		String query=" DELETE FROM man_proyecto_manager_tabulacion "
					+"	WHERE id_operacion = "+ id ;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
/**************************** ANALISIS ****************************************************************************************************************************/
	@Override
	public ObjEstudioAnalisis getAnalisisOperacionByUid(int id){
		ObjEstudioAnalisis ana = new ObjEstudioAnalisis();
		Connection conn = null;
		
		String query = " SELECT id_ana "
					   +"   ,id_operacion "
					   +"   ,dis_muestra_ana "
					   +"   ,tom_mue_mez_pan_ana "
					   +"  ,desp_lin_fij_cel_rdd_nec_ana "
					   +"   ,tel_fuente_eje_ana "
					   +"   ,pond_nec_mark_muest_est_ana "
					   +"   ,compl_pond_ana "
					   +"   ,pond_nec_ana "
					   +"   ,pondera_cal_nec_est_ana "
					   +"   ,uni_nec_ana "
					   +"   ,punto_ref_nec_ana "
					   +"   ,wave_frec_ana "
					   +"   ,met_enc_ana "
					  +" FROM man_proyecto_manager_analisis " 
					  +" WHERE id_operacion =  " + id +" ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				 ana.setId_ana(rs.getInt("id_ana"));
				 ana.setId_operacion(id);
				 ana.setDis_muestra_ana(rs.getString("dis_muestra_ana"));
				 ana.setTom_mue_mez_pan_ana(rs.getString("tom_mue_mez_pan_ana"));
				 ana.setDesp_lin_fij_cel_rdd_nec_ana(rs.getString("desp_lin_fij_cel_rdd_nec_ana"));
				 ana.setTel_fuente_eje_ana(rs.getString("tel_fuente_eje_ana"));
				 ana.setPond_nec_mark_muest_est_ana(rs.getString("pond_nec_mark_muest_est_ana"));
				 ana.setCompl_pond_ana(rs.getString("compl_pond_ana"));
				 ana.setPond_nec_ana(rs.getInt("pond_nec_ana"));
				 ana.setPondera_cal_nec_est_ana(rs.getString("pondera_cal_nec_est_ana"));
				 ana.setUni_nec_ana(rs.getString("uni_nec_ana"));
				 ana.setPunto_ref_nec_ana(rs.getString("punto_ref_nec_ana"));
				 ana.setWave_frec_ana(rs.getString("wave_frec_ana"));
				 ana.setMet_enc_ana(rs.getString("met_enc_ana"));
			  }
			  
			  return ana;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public ObjResultCreaCotOp setAnalisisOperacion(ObjEstudioAnalisis ana){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" INSERT INTO man_proyecto_manager_analisis "
					 +"          (id_operacion "
					 +"          ,dis_muestra_ana "
					 +"          ,tom_mue_mez_pan_ana "
					 +"          ,desp_lin_fij_cel_rdd_nec_ana "
					 +"          ,tel_fuente_eje_ana "
					 +"          ,pond_nec_mark_muest_est_ana "
					 +"          ,compl_pond_ana "
					 +"          ,pond_nec_ana "
					 +"          ,pondera_cal_nec_est_ana "
					 +"          ,uni_nec_ana "
					 +"          ,punto_ref_nec_ana " 
					 +"          ,wave_frec_ana " 
					 +"          ,met_enc_ana) "
					 +"     VALUES  "
					 +"          ( "
					 +"          "+ana.getId_operacion()+" "
					 +"          ,'"+ana.getDis_muestra_ana()+"' "
					 +"          ,'"+ana.getTom_mue_mez_pan_ana()+"' "
					 +"          ,'"+ana.getDesp_lin_fij_cel_rdd_nec_ana()+"' "
					 +"          ,'"+ana.getTel_fuente_eje_ana()+"' "
					 +"          ,'"+ana.getPond_nec_mark_muest_est_ana()+"' "
					 +"          ,'"+ana.getCompl_pond_ana()+"' "
					 +"          ,"+ana.getPond_nec_ana()+" "
					 +"          ,'"+ana.getPondera_cal_nec_est_ana()+"' "
					 +"          ,'"+ana.getUni_nec_ana()+"' "
					 +"          ,'"+ana.getPunto_ref_nec_ana()+"' "
					 +"          ,'"+ana.getWave_frec_ana()+"' "
					 +"          ,'"+ana.getMet_enc_ana()+"') "; 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(ana.getId_operacion());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public ObjResultCreaCotOp updateAnalisisOperacion(ObjEstudioAnalisis ana){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" UPDATE man_proyecto_manager_analisis "
					+"	SET id_operacion = "+ana.getId_operacion()+" ";
					 if(ana.getDis_muestra_ana() != null){ query = query +",dis_muestra_ana = '"+ana.getDis_muestra_ana()+"' ";}
					 if(ana.getTom_mue_mez_pan_ana()  != null){ query = query +" ,tom_mue_mez_pan_ana = '"+ana.getTom_mue_mez_pan_ana()+"' ";}
					 if(ana.getDesp_lin_fij_cel_rdd_nec_ana()  != null){ query = query +" ,desp_lin_fij_cel_rdd_nec_ana = '"+ana.getDesp_lin_fij_cel_rdd_nec_ana()+"' ";}
					 if(ana.getTel_fuente_eje_ana()  != null){ query = query +" ,tel_fuente_eje_ana = '"+ana.getTel_fuente_eje_ana()+"' ";}
					 if(ana.getPond_nec_mark_muest_est_ana()  != null){ query = query +" ,pond_nec_mark_muest_est_ana = '"+ana.getPond_nec_mark_muest_est_ana()+"' ";}
					 if(ana.getCompl_pond_ana()  != null){ query = query +" ,compl_pond_ana = '"+ana.getCompl_pond_ana()+"' ";}
					 if(ana.getPond_nec_ana()  != 0){ query = query +" ,pond_nec_ana = "+ana.getPond_nec_ana()+" ";}
					 if(ana.getPondera_cal_nec_est_ana()  != null){ query = query +" ,pondera_cal_nec_est_ana = '"+ana.getPondera_cal_nec_est_ana()+"' ";}
					 if(ana.getUni_nec_ana()  != null){ query = query +" ,uni_nec_ana = '"+ana.getUni_nec_ana()+"' ";}
					 if(ana.getPunto_ref_nec_ana()  != null){ query = query +" ,punto_ref_nec_ana = '"+ana.getPunto_ref_nec_ana()+"' ";}
					 if(ana.getWave_frec_ana()  != null){ query = query +" ,wave_frec_ana = '"+ana.getWave_frec_ana()+"' ";}
					 if(ana.getMet_enc_ana()  != null){ query = query +" ,met_enc_ana = '"+ana.getMet_enc_ana()+"' ";}
					 query = query +" WHERE id_operacion = " + ana.getId_operacion();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(ana.getId_operacion());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int deleteAnalisisOperacion(int id){
		
		
		
		int result = 0;
		
		Connection conn = null;
		String query=" DELETE FROM man_proyecto_manager_analisis "
					+"	WHERE id_operacion = "+ id ;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
/**************************** RECOLECCION ****************************************************************************************************************************/
	@Override
	public ObjEstudioRecoleccion getRecoleccionOperacionByUid(int id){
		ObjEstudioRecoleccion rec = new ObjEstudioRecoleccion();
		Connection conn = null;
		
		String query = " SELECT id_reco "
					   +"   ,id_operacion "
					   +"   ,id_pais "
					   +"   ,lang_reco "
					   +"   ,frec_reco "
					   +"   ,frec_wave_reco "
					   +"   ,cant_wave_reco "
					   +"   ,otros_frec_reco "
					   +"   ,modo_reco "
					   +"   ,sub_modo_reco "
					   +"   ,porcent_inc_reco "
					   +"   ,dur_intro_reco "
					   +"   ,dur_cuest_reco "
					   +"   ,total_entr_reco "
					   +"   ,req_prod_test_reco "
					   +"   ,req_compra_prod_test_reco "
					   +"   ,tipo__test_reco "
					   +"   ,req_retorno_test_reco "
					   +"   ,descripcion_test_reco "
					   +"   ,req_reclutamiento_test_reco "
					   +"   ,modo_reclutamiento_test_reco "
					   +"   ,sub_cuota_test_reco "
					   +"   ,descr_recluta_test_reco "
					   +"   ,tipo_entrev_reco "
					   +"   ,descrip_entrev_reco "
					   +"   ,ident_clie_respond_entrev_reco "
					   +"   ,como_entrev_reco "
					   +"   ,req_piloto_entrev_reco "
					   +"  ,des_req_piloto_entrev_reco "
					   +"   ,met_random_entrev_reco "
					   +"   ,random_rute_entrev_reco "
					   +"   ,adres_rute_entrev_reco "
					   +"   ,adm_route_entrev_reco "
					   +"   ,met_sel_respon_entrev_reco "
					   +"   ,num_preg__entrev_reco "
					   +"   ,obj_taza_resp_entrev_reco "
					   +"   ,info_met_prob_entrev_reco "
					   +"   ,nec_mat_entrev_reco "
					   +"   ,esp_mater_entrev_reco "
					   +"   ,req_sesion_info_entrev_reco "
					   +"   ,clie_entr_inse_entrev_reco "
					   +"   ,respon_entrev_reco "
					   +"   ,deescr_entrev_reco "
					   +"   ,rdd_land_cati "
					   +"   ,rdd_cell_cati "
					   +"   ,target_cati "
					   +"   ,client_fone_cati "
					   +"   ,other_cati "
					   +"   ,def_eje_cati "
					   +"   ,defi_cati "
					   +"   ,otros_cati "
					   +"   ,def_ej_ob_cati "
					   +"   ,por_cell_clie_cati "
					   +"   ,num_cell_ded_cati "
					   +"   ,req_el_dupli_cati "
					   +"   ,total_file_x_file_cati "
					   +"   ,total_n_rec_cati "
					   +"   ,por_use_rec_cati "
					   +"   ,frec_file_del_cati "
					   +"   ,cual_cati "
					  +" FROM man_proyecto_manager_recoleccion  "
					  +" WHERE id_reco =   " + id +" ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				 rec.setId_reco(id);
				 rec.setId_operacion(rs.getInt("id_operacion"));
				 rec.setId_pais(rs.getInt("id_pais"));
				 rec.setLang_reco(rs.getString("lang_reco"));
				 rec.setFrec_reco(rs.getString("frec_reco"));
				 rec.setFrec_wave_reco(rs.getString("frec_wave_reco"));
				 rec.setCant_wave_reco(rs.getInt("cant_wave_reco"));
				 rec.setOtros_frec_reco(rs.getString("otros_frec_reco"));
				 rec.setModo_reco(rs.getString("modo_reco"));
				 rec.setSub_modo_reco(rs.getString("sub_modo_reco"));
				 rec.setPorcent_inc_reco(rs.getInt("porcent_inc_reco"));
				 rec.setDur_intro_reco(rs.getInt("dur_intro_reco"));
				 rec.setDur_cuest_reco(rs.getInt("dur_cuest_reco"));
				 rec.setTotal_entr_reco(rs.getInt("total_entr_reco"));
				 rec.setReq_prod_test_reco(rs.getString("req_prod_test_reco"));
				 rec.setReq_compra_prod_test_reco(rs.getString("req_compra_prod_test_reco"));
				 rec.setTipo__test_reco(rs.getString("tipo__test_reco"));
				 rec.setReq_retorno_test_reco(rs.getString("req_retorno_test_reco"));
				 rec.setDescripcion_test_reco(rs.getString("descripcion_test_reco"));
				 rec.setReq_reclutamiento_test_reco(rs.getString("req_reclutamiento_test_reco"));
				 rec.setModo_reclutamiento_test_reco(rs.getString("modo_reclutamiento_test_reco"));
				 rec.setSub_cuota_test_reco(rs.getString("sub_cuota_test_reco"));
				 rec.setDescr_recluta_test_reco(rs.getString("descr_recluta_test_reco"));
				 rec.setTipo_entrev_reco(rs.getString("tipo_entrev_reco"));
				 rec.setDescrip_entrev_reco(rs.getString("descrip_entrev_reco"));
				 rec.setIdent_clie_respond_entrev_reco(rs.getString("ident_clie_respond_entrev_reco"));
				 rec.setComo_entrev_reco(rs.getString("como_entrev_reco"));
				 rec.setReq_piloto_entrev_reco(rs.getString("req_piloto_entrev_reco"));
				 rec.setDes_req_piloto_entrev_reco(rs.getString("des_req_piloto_entrev_reco"));
				 rec.setMet_random_entrev_reco(rs.getString("met_random_entrev_reco"));
				 rec.setRandom_rute_entrev_reco(rs.getString("random_rute_entrev_reco"));
				 rec.setAdres_rute_entrev_reco(rs.getString("adres_rute_entrev_reco"));
				 rec.setAdm_route_entrev_reco(rs.getString("adm_route_entrev_reco"));
				 rec.setMet_sel_respon_entrev_reco(rs.getString("met_sel_respon_entrev_reco"));
				 rec.setNum_preg__entrev_reco(rs.getInt("num_preg__entrev_reco"));
				 rec.setObj_taza_resp_entrev_reco(rs.getString("obj_taza_resp_entrev_reco"));
				 rec.setInfo_met_prob_entrev_reco(rs.getString("info_met_prob_entrev_reco"));
				 rec.setNec_mat_entrev_reco(rs.getString("nec_mat_entrev_reco"));
				 rec.setEsp_mater_entrev_reco(rs.getString("esp_mater_entrev_reco"));
				 rec.setReq_sesion_info_entrev_reco(rs.getString("req_sesion_info_entrev_reco"));
				 rec.setClie_entr_inse_entrev_reco(rs.getString("clie_entr_inse_entrev_reco"));
				 rec.setRespon_entrev_reco(rs.getInt("respon_entrev_reco"));
				 rec.setDeescr_entrev_reco(rs.getString("deescr_entrev_reco"));
				 rec.setRdd_land_cati(rs.getString("rdd_land_cati"));
				 rec.setRdd_cell_cati(rs.getString("rdd_cell_cati"));
				 rec.setTarget_cati(rs.getString("target_cati"));
				 rec.setClient_fone_cati(rs.getString("client_fone_cati"));
				 rec.setOther_cati(rs.getString("other_cati"));
				 rec.setDef_eje_cati(rs.getString("def_eje_cati"));
				 rec.setDefi_cati(rs.getString("defi_cati"));
				 rec.setOtros_cati(rs.getString("otros_cati"));
				 rec.setDef_ej_ob_cati(rs.getString("def_ej_ob_cati"));
				 rec.setPor_cell_clie_cati(rs.getInt("por_cell_clie_cati"));
				 rec.setNum_cell_ded_cati(rs.getString("num_cell_ded_cati"));
				 rec.setReq_el_dupli_cati(rs.getString("req_el_dupli_cati"));
				 rec.setTotal_file_x_file_cati(rs.getInt("total_file_x_file_cati"));
				 rec.setTotal_n_rec_cati(rs.getInt("total_n_rec_cati"));
				 rec.setPor_use_rec_cati(rs.getInt("por_use_rec_cati"));
				 rec.setFrec_file_del_cati(rs.getString("frec_file_del_cati"));
				 rec.setCual_cati(rs.getString("cual_cati"));
			}
			  
			  return rec;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public ArrayList<ObjEstudioRecoleccion> getListRecoleccionOperacionByUid(int id){
		ArrayList<ObjEstudioRecoleccion> recs = new ArrayList<ObjEstudioRecoleccion>();
		Connection conn = null;
		
		String query = " SELECT id_reco "
					   +"   ,id_operacion "
					   +"   ,id_pais "
					   +"   ,lang_reco "
					   +"   ,frec_reco "
					   +"   ,frec_wave_reco "
					   +"   ,cant_wave_reco "
					   +"   ,otros_frec_reco "
					   +"   ,modo_reco "
					   +"   ,sub_modo_reco "
					   +"   ,porcent_inc_reco "
					   +"   ,dur_intro_reco "
					   +"   ,dur_cuest_reco "
					   +"   ,total_entr_reco "
					   +"   ,req_prod_test_reco "
					   +"   ,req_compra_prod_test_reco "
					   +"   ,tipo__test_reco "
					   +"   ,req_retorno_test_reco "
					   +"   ,descripcion_test_reco "
					   +"   ,req_reclutamiento_test_reco "
					   +"   ,modo_reclutamiento_test_reco "
					   +"   ,sub_cuota_test_reco "
					   +"   ,descr_recluta_test_reco "
					   +"   ,tipo_entrev_reco "
					   +"   ,descrip_entrev_reco "
					   +"   ,ident_clie_respond_entrev_reco "
					   +"   ,como_entrev_reco "
					   +"   ,req_piloto_entrev_reco "
					   +"  ,des_req_piloto_entrev_reco "
					   +"   ,met_random_entrev_reco "
					   +"   ,random_rute_entrev_reco "
					   +"   ,adres_rute_entrev_reco "
					   +"   ,adm_route_entrev_reco "
					   +"   ,met_sel_respon_entrev_reco "
					   +"   ,num_preg__entrev_reco "
					   +"   ,obj_taza_resp_entrev_reco "
					   +"   ,info_met_prob_entrev_reco "
					   +"   ,nec_mat_entrev_reco "
					   +"   ,esp_mater_entrev_reco "
					   +"   ,req_sesion_info_entrev_reco "
					   +"   ,clie_entr_inse_entrev_reco "
					   +"   ,respon_entrev_reco "
					   +"   ,deescr_entrev_reco "
					   +"   ,rdd_land_cati "
					   +"   ,rdd_cell_cati "
					   +"   ,target_cati "
					   +"   ,client_fone_cati "
					   +"   ,other_cati "
					   +"   ,def_eje_cati "
					   +"   ,defi_cati "
					   +"   ,otros_cati "
					   +"   ,def_ej_ob_cati "
					   +"   ,por_cell_clie_cati "
					   +"   ,num_cell_ded_cati "
					   +"   ,req_el_dupli_cati "
					   +"   ,total_file_x_file_cati "
					   +"   ,total_n_rec_cati "
					   +"   ,por_use_rec_cati "
					   +"   ,frec_file_del_cati "
					   +"   ,cual_cati "
					  +" FROM man_proyecto_manager_recoleccion  "
					  +" WHERE id_operacion =   " + id +" ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				 ObjEstudioRecoleccion rec = new ObjEstudioRecoleccion();
				 rec.setId_reco(id);
				 rec.setId_operacion(rs.getInt("id_operacion"));
				 rec.setId_pais(rs.getInt("id_pais"));
				 rec.setLang_reco(rs.getString("lang_reco"));
				 rec.setFrec_reco(rs.getString("frec_reco"));
				 rec.setFrec_wave_reco(rs.getString("frec_wave_reco"));
				 rec.setCant_wave_reco(rs.getInt("cant_wave_reco"));
				 rec.setOtros_frec_reco(rs.getString("otros_frec_reco"));
				 rec.setModo_reco(rs.getString("modo_reco"));
				 rec.setSub_modo_reco(rs.getString("sub_modo_reco"));
				 rec.setPorcent_inc_reco(rs.getInt("porcent_inc_reco"));
				 rec.setDur_intro_reco(rs.getInt("dur_intro_reco"));
				 rec.setDur_cuest_reco(rs.getInt("dur_cuest_reco"));
				 rec.setTotal_entr_reco(rs.getInt("total_entr_reco"));
				 rec.setReq_prod_test_reco(rs.getString("req_prod_test_reco"));
				 rec.setReq_compra_prod_test_reco(rs.getString("req_compra_prod_test_reco"));
				 rec.setTipo__test_reco(rs.getString("tipo__test_reco"));
				 rec.setReq_retorno_test_reco(rs.getString("req_retorno_test_reco"));
				 rec.setDescripcion_test_reco(rs.getString("descripcion_test_reco"));
				 rec.setReq_reclutamiento_test_reco(rs.getString("req_reclutamiento_test_reco"));
				 rec.setModo_reclutamiento_test_reco(rs.getString("modo_reclutamiento_test_reco"));
				 rec.setSub_cuota_test_reco(rs.getString("sub_cuota_test_reco"));
				 rec.setDescr_recluta_test_reco(rs.getString("descr_recluta_test_reco"));
				 rec.setTipo_entrev_reco(rs.getString("tipo_entrev_reco"));
				 rec.setDescrip_entrev_reco(rs.getString("descrip_entrev_reco"));
				 rec.setIdent_clie_respond_entrev_reco(rs.getString("ident_clie_respond_entrev_reco"));
				 rec.setComo_entrev_reco(rs.getString("como_entrev_reco"));
				 rec.setReq_piloto_entrev_reco(rs.getString("req_piloto_entrev_reco"));
				 rec.setDes_req_piloto_entrev_reco(rs.getString("des_req_piloto_entrev_reco"));
				 rec.setMet_random_entrev_reco(rs.getString("met_random_entrev_reco"));
				 rec.setRandom_rute_entrev_reco(rs.getString("random_rute_entrev_reco"));
				 rec.setAdres_rute_entrev_reco(rs.getString("adres_rute_entrev_reco"));
				 rec.setAdm_route_entrev_reco(rs.getString("adm_route_entrev_reco"));
				 rec.setMet_sel_respon_entrev_reco(rs.getString("met_sel_respon_entrev_reco"));
				 rec.setNum_preg__entrev_reco(rs.getInt("num_preg__entrev_reco"));
				 rec.setObj_taza_resp_entrev_reco(rs.getString("obj_taza_resp_entrev_reco"));
				 rec.setInfo_met_prob_entrev_reco(rs.getString("info_met_prob_entrev_reco"));
				 rec.setNec_mat_entrev_reco(rs.getString("nec_mat_entrev_reco"));
				 rec.setEsp_mater_entrev_reco(rs.getString("esp_mater_entrev_reco"));
				 rec.setReq_sesion_info_entrev_reco(rs.getString("req_sesion_info_entrev_reco"));
				 rec.setClie_entr_inse_entrev_reco(rs.getString("clie_entr_inse_entrev_reco"));
				 rec.setRespon_entrev_reco(rs.getInt("respon_entrev_reco"));
				 rec.setDeescr_entrev_reco(rs.getString("deescr_entrev_reco"));
				 rec.setRdd_land_cati(rs.getString("rdd_land_cati"));
				 rec.setRdd_cell_cati(rs.getString("rdd_cell_cati"));
				 rec.setTarget_cati(rs.getString("target_cati"));
				 rec.setClient_fone_cati(rs.getString("client_fone_cati"));
				 rec.setOther_cati(rs.getString("other_cati"));
				 rec.setDef_eje_cati(rs.getString("def_eje_cati"));
				 rec.setDefi_cati(rs.getString("defi_cati"));
				 rec.setOtros_cati(rs.getString("otros_cati"));
				 rec.setDef_ej_ob_cati(rs.getString("def_ej_ob_cati"));
				 rec.setPor_cell_clie_cati(rs.getInt("por_cell_clie_cati"));
				 rec.setNum_cell_ded_cati(rs.getString("num_cell_ded_cati"));
				 rec.setReq_el_dupli_cati(rs.getString("req_el_dupli_cati"));
				 rec.setTotal_file_x_file_cati(rs.getInt("total_file_x_file_cati"));
				 rec.setTotal_n_rec_cati(rs.getInt("total_n_rec_cati"));
				 rec.setPor_use_rec_cati(rs.getInt("por_use_rec_cati"));
				 rec.setFrec_file_del_cati(rs.getString("frec_file_del_cati"));
				 rec.setCual_cati(rs.getString("cual_cati"));
				 recs.add(rec);
			  }
			  
			  return recs;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public ObjResultCreaCotOp setRecoleccionOperacion(ObjEstudioRecoleccion rec){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" INSERT INTO man_proyecto_manager_recoleccion "
			          +" (id_operacion "
			           +",id_pais "
			           +",lang_reco "
			           +",frec_reco "
			           +",frec_wave_reco "
			           +" ,cant_wave_reco "
			           +" ,otros_frec_reco "
			           +" ,modo_reco "
			           +" ,sub_modo_reco "
			           +" ,porcent_inc_reco "
			           +" ,dur_intro_reco "
			           +" ,dur_cuest_reco "
			           +" ,total_entr_reco "
			           +" ,req_prod_test_reco "
			           +" ,req_compra_prod_test_reco "
			           +" ,tipo__test_reco "
			           +" ,req_retorno_test_reco "
			           +" ,descripcion_test_reco "
			           +" ,req_reclutamiento_test_reco "
			           +" ,modo_reclutamiento_test_reco "
			           +" ,sub_cuota_test_reco "
			           +" ,descr_recluta_test_reco "
			           +" ,tipo_entrev_reco "
			           +" ,descrip_entrev_reco "
			           +" ,ident_clie_respond_entrev_reco "
			           +" ,como_entrev_reco "
			           +" ,req_piloto_entrev_reco "
			           +" ,des_req_piloto_entrev_reco "
			           +" ,met_random_entrev_reco "
			           +" ,random_rute_entrev_reco "
			           +" ,adres_rute_entrev_reco "
			           +" ,adm_route_entrev_reco "
			           +" ,met_sel_respon_entrev_reco "
			           +" ,num_preg__entrev_reco "
			           +" ,obj_taza_resp_entrev_reco "
			           +" ,info_met_prob_entrev_reco "
			           +" ,nec_mat_entrev_reco "
			           +" ,esp_mater_entrev_reco "
			           +" ,req_sesion_info_entrev_reco "
			           +" ,clie_entr_inse_entrev_reco "
			           +" ,respon_entrev_reco "
			           +" ,deescr_entrev_reco "
			           +" ,rdd_land_cati "
			           +" ,rdd_cell_cati "
			           +" ,target_cati "
			           +" ,client_fone_cati "
			           +" ,other_cati "
			           +" ,def_eje_cati "
			           +" ,defi_cati "
			           +" ,otros_cati "
			           +" ,def_ej_ob_cati "
			           +" ,por_cell_clie_cati "
			           +" ,num_cell_ded_cati "
			           +" ,req_el_dupli_cati "
			           +" ,total_file_x_file_cati "
			           +" ,total_n_rec_cati "
			           +" ,por_use_rec_cati "
			           +" ,frec_file_del_cati "
			           +" ,cual_cati) "
			           +" VALUES "
			           +" ( "+rec.getId_operacion()+" " 
			           +" ,"+rec.getId_pais()+" " 
			           +" ,'"+rec.getLang_reco()+"' "
			           +" ,'"+rec.getFrec_reco()+"' "
			           +" ,'"+rec.getFrec_wave_reco()+"' "
			           +" ,"+rec.getCant_wave_reco()+" "
			           +" ,'"+rec.getOtros_frec_reco()+"' "
			           +" ,'"+rec.getModo_reco()+"' "
			           +" ,'"+rec.getSub_modo_reco()+"' "
			           +" ,"+rec.getPorcent_inc_reco()+" "
			           +" ,"+rec.getDur_intro_reco()+" "
			           +" ,"+rec.getDur_cuest_reco()+" "
			           +" ,"+rec.getTotal_entr_reco()+" "
			           +" ,'"+rec.getReq_prod_test_reco()+"' "
			           +" ,'"+rec.getReq_compra_prod_test_reco()+"' "
			           +" ,'"+rec.getTipo__test_reco()+"' "
			           +" ,'"+rec.getReq_retorno_test_reco()+"' "
			           +" ,'"+rec.getDescripcion_test_reco()+"' "
			           +" ,'"+rec.getReq_reclutamiento_test_reco()+"' "
			           +" ,'"+rec.getModo_reclutamiento_test_reco()+"' "
			           +" ,'"+rec.getSub_cuota_test_reco()+"' "
			           +" ,'"+rec.getDescr_recluta_test_reco()+"' "
			           +" ,'"+rec.getTipo_entrev_reco()+"' "
			           +" ,'"+rec.getDescrip_entrev_reco()+"' "
			           +" ,'"+rec.getIdent_clie_respond_entrev_reco()+"' "
			           +" ,'"+rec.getComo_entrev_reco()+"' "
			           +" ,'"+rec.getReq_piloto_entrev_reco()+"' "
			           +" ,'"+rec.getDes_req_piloto_entrev_reco()+"' "
			           +" ,'"+rec.getMet_random_entrev_reco()+"' "
			           +" ,'"+rec.getRandom_rute_entrev_reco()+"' "
			           +" ,'"+rec.getAdres_rute_entrev_reco()+"' "
			           +" ,'"+rec.getAdm_route_entrev_reco()+"' "
			           +" ,'"+rec.getMet_sel_respon_entrev_reco()+"' "
			           +" ,"+rec.getNum_preg__entrev_reco()+" "
			           +" ,'"+rec.getObj_taza_resp_entrev_reco()+"' "
			           +" ,'"+rec.getInfo_met_prob_entrev_reco()+"' "
			           +" ,'"+rec.getNec_mat_entrev_reco()+"' "
			           +" ,'"+rec.getEsp_mater_entrev_reco()+"' "
			          +"  ,'"+rec.getReq_sesion_info_entrev_reco()+"' "
			           +" ,'"+rec.getClie_entr_inse_entrev_reco()+"' "
			           +" ,"+rec.getRespon_entrev_reco()+" "
			           +" ,'"+rec.getDeescr_entrev_reco()+"' "
			           +" ,'"+rec.getRdd_land_cati()+"' "
			           +" ,'"+rec.getRdd_cell_cati()+"' "
			           +" ,'"+rec.getTarget_cati()+"' "
			           +" ,'"+rec.getClient_fone_cati()+"' "
			           +" ,'"+rec.getOther_cati()+"' "
			           +" ,'"+rec.getDef_eje_cati()+"' "
			           +" ,'"+rec.getDefi_cati()+"' "
			           +" ,'"+rec.getOtros_cati()+"' "
			           +" ,'"+rec.getDef_ej_ob_cati()+"' "
			           +" ,"+rec.getPor_cell_clie_cati()+" "
			           +" ,'"+rec.getNum_cell_ded_cati()+"' "
			           +" ,'"+rec.getReq_el_dupli_cati()+"' "
			           +" ,"+rec.getTotal_file_x_file_cati()+" "
			           +" ,"+rec.getTotal_n_rec_cati()+" "
			           +" ,"+rec.getPor_use_rec_cati()+" "
			           +" ,'"+rec.getFrec_file_del_cati()+"' "
			           +" ,'"+rec.getCual_cati()+"' ) "; 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(rec.getId_operacion());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public ObjResultCreaCotOp updateRecoleccionOperacion(ObjEstudioRecoleccion rec){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" UPDATE man_proyecto_manager_recoleccion "
					+"	SET id_operacion = "+rec.getId_operacion()+" ";
					if(rec.getId_pais() != 0){ query = query +" ,id_pais = '"+rec.getId_pais()+"' ";}
					if(rec.getLang_reco() != null){ query = query +" ,lang_reco = '"+rec.getLang_reco()+"' ";}
					if(rec.getFrec_reco() != null){ query = query +" ,frec_reco = '"+rec.getFrec_reco()+"' ";}
					if(rec.getFrec_wave_reco() != null){ query = query +" ,frec_wave_reco = '"+rec.getFrec_wave_reco()+"' ";}
					if(rec.getCant_wave_reco() != 0){ query = query +" ,cant_wave_reco = "+rec.getCant_wave_reco()+" ";}
					if(rec.getOtros_frec_reco() != null){ query = query +" ,otros_frec_reco = '"+rec.getOtros_frec_reco()+"' ";}
					if(rec.getModo_reco() != null){ query = query +" ,modo_reco = '"+rec.getModo_reco()+"' ";}
					if(rec.getSub_modo_reco() != null){ query = query +" ,sub_modo_reco = '"+rec.getSub_modo_reco()+"' ";}
					if(rec.getPorcent_inc_reco() != 0){ query = query +"  ,porcent_inc_reco = "+rec.getPorcent_inc_reco()+" ";}
					if(rec.getDur_intro_reco() != 0){ query = query +"  ,dur_intro_reco = "+rec.getDur_intro_reco()+" ";}
					if(rec.getDur_cuest_reco() != 0){ query = query +"  ,dur_cuest_reco = "+rec.getDur_cuest_reco()+" ";}
					if(rec.getTotal_entr_reco() != 0){ query = query +"  ,total_entr_reco = "+rec.getTotal_entr_reco()+" ";}
					if(rec.getReq_prod_test_reco() != null){ query = query +"  ,req_prod_test_reco = '"+rec.getReq_prod_test_reco()+"' ";}
					if(rec.getReq_compra_prod_test_reco() != null){ query = query +"  ,req_compra_prod_test_reco = '"+rec.getReq_compra_prod_test_reco()+"' ";}
					if(rec.getTipo__test_reco() != null){ query = query +"  ,tipo__test_reco = '"+rec.getTipo__test_reco()+"' ";}
					if(rec.getReq_retorno_test_reco() != null){ query = query +"  ,req_retorno_test_reco = '"+rec.getReq_retorno_test_reco()+"' ";}
					if(rec.getDescripcion_test_reco() != null){ query = query +"  ,descripcion_test_reco = '"+rec.getDescripcion_test_reco()+"' ";}
					if(rec.getReq_reclutamiento_test_reco() != null){ query = query +"  ,req_reclutamiento_test_reco = '"+rec.getReq_reclutamiento_test_reco()+"' ";}
					if(rec.getModo_reclutamiento_test_reco() != null){ query = query +"  ,modo_reclutamiento_test_reco = '"+rec.getModo_reclutamiento_test_reco()+"' ";}
					if(rec.getSub_cuota_test_reco() != null){ query = query +"  ,sub_cuota_test_reco = '"+rec.getSub_cuota_test_reco()+"' ";}
					if(rec.getDescr_recluta_test_reco() != null){ query = query +"  ,descr_recluta_test_reco = '"+rec.getDescr_recluta_test_reco()+"' ";}
					if(rec.getTipo_entrev_reco() != null){ query = query +"  ,tipo_entrev_reco = '"+rec.getTipo_entrev_reco()+"' ";}
					if(rec.getDescrip_entrev_reco() != null){ query = query +"  ,descrip_entrev_reco = '"+rec.getDescrip_entrev_reco()+"' ";}
					if(rec.getIdent_clie_respond_entrev_reco() != null){ query = query +"  ,ident_clie_respond_entrev_reco = '"+rec.getIdent_clie_respond_entrev_reco()+"' ";}
					if(rec.getComo_entrev_reco() != null){ query = query +"  ,como_entrev_reco = '"+rec.getComo_entrev_reco()+"' ";}
					if(rec.getReq_piloto_entrev_reco() != null){ query = query +"  ,req_piloto_entrev_reco = '"+rec.getReq_piloto_entrev_reco()+"' ";}
					if(rec.getDes_req_piloto_entrev_reco() != null){ query = query +"  ,des_req_piloto_entrev_reco = '"+rec.getDes_req_piloto_entrev_reco()+"' ";}
					if(rec.getMet_random_entrev_reco() != null){ query = query +"  ,met_random_entrev_reco = '"+rec.getMet_random_entrev_reco()+"' ";}
					if(rec.getRandom_rute_entrev_reco() != null){ query = query +"  ,random_rute_entrev_reco = '"+rec.getRandom_rute_entrev_reco()+"' ";}
					if(rec.getAdres_rute_entrev_reco() != null){ query = query +"  ,adres_rute_entrev_reco = '"+rec.getAdres_rute_entrev_reco()+"' ";}
					if(rec.getAdm_route_entrev_reco() != null){ query = query +"  ,adm_route_entrev_reco = '"+rec.getAdm_route_entrev_reco()+"' ";}
					if(rec.getMet_sel_respon_entrev_reco() != null){ query = query +"  ,met_sel_respon_entrev_reco = '"+rec.getMet_sel_respon_entrev_reco()+"' ";}
					if(rec.getNum_preg__entrev_reco() != 0){ query = query +"  ,num_preg__entrev_reco = "+rec.getNum_preg__entrev_reco() +" ";}
					if(rec.getObj_taza_resp_entrev_reco() != null){ query = query +"  ,obj_taza_resp_entrev_reco = '"+rec.getObj_taza_resp_entrev_reco()+"' ";}
					if(rec.getInfo_met_prob_entrev_reco() != null){ query = query +"  ,info_met_prob_entrev_reco = '"+rec.getInfo_met_prob_entrev_reco()+"' ";}
					if(rec.getNec_mat_entrev_reco() != null){ query = query +"  ,nec_mat_entrev_reco = '"+rec.getNec_mat_entrev_reco()+"' ";}
					if(rec.getEsp_mater_entrev_reco() != null){ query = query +"  ,esp_mater_entrev_reco = '"+rec.getEsp_mater_entrev_reco()+"' ";}
					if(rec.getReq_sesion_info_entrev_reco() != null){ query = query +"  ,req_sesion_info_entrev_reco = '"+rec.getReq_sesion_info_entrev_reco()+"' ";}
					if(rec.getClie_entr_inse_entrev_reco() != null){ query = query +"  ,clie_entr_inse_entrev_reco = '"+rec.getClie_entr_inse_entrev_reco()+"' ";}
					if(rec.getRespon_entrev_reco() != 0){ query = query +"  ,respon_entrev_reco = "+rec.getRespon_entrev_reco()+" ";}
					if(rec.getDeescr_entrev_reco() != null){ query = query +"  ,deescr_entrev_reco = '"+rec.getDeescr_entrev_reco()+"' ";}
					if(rec.getRdd_land_cati() != null){ query = query +"  ,rdd_land_cati = '"+rec.getRdd_land_cati()+"' ";}
					if(rec.getRdd_cell_cati() != null){ query = query +"  ,rdd_cell_cati = '"+rec.getRdd_cell_cati()+"' ";}
					if(rec.getTarget_cati() != null){ query = query +"  ,target_cati = '"+rec.getTarget_cati()+"' ";}
					if(rec.getClient_fone_cati() != null){ query = query +"  ,client_fone_cati = '"+rec.getClient_fone_cati()+"' ";}
					if(rec.getOther_cati() != null){ query = query +"  ,other_cati = '"+rec.getOther_cati()+"' ";}
					if(rec.getDef_eje_cati() != null){ query = query +"  ,def_eje_cati = '"+rec.getDef_eje_cati()+"' ";}
					if(rec.getDefi_cati() != null){ query = query +"  ,defi_cati = '"+rec.getDefi_cati()+"' ";}
					if(rec.getOtros_cati() != null){ query = query +"  ,otros_cati = '"+rec.getOtros_cati()+"' ";}
					if(rec.getDef_ej_ob_cati() != null){ query = query +"  ,def_ej_ob_cati = '"+rec.getDef_ej_ob_cati()+"' ";}
					if(rec.getPor_cell_clie_cati() != 0){ query = query +"  ,por_cell_clie_cati = "+rec.getPor_cell_clie_cati()+" ";}
					if(rec.getNum_cell_ded_cati() != null){ query = query +"  ,num_cell_ded_cati = '"+rec.getNum_cell_ded_cati()+"' ";}
					if(rec.getReq_el_dupli_cati() != null){ query = query +"  ,req_el_dupli_cati = '"+rec.getReq_el_dupli_cati()+"' ";}
					if(rec.getTotal_file_x_file_cati() != 0){ query = query +"  ,total_file_x_file_cati = "+rec.getTotal_file_x_file_cati()+" ";}
					if(rec.getTotal_n_rec_cati() != 0){ query = query +"  ,total_n_rec_cati = "+rec.getTotal_n_rec_cati()+" ";}
					if(rec.getPor_use_rec_cati() != 0){ query = query +"  ,por_use_rec_cati = "+rec.getPor_use_rec_cati()+" ";}
					if(rec.getFrec_file_del_cati() != null){ query = query +"  ,frec_file_del_cati = '"+rec.getFrec_file_del_cati()+"' ";}
					if(rec.getCual_cati() != null){ query = query +"  ,cual_cati = '"+rec.getCual_cati()+"' ";}
					
					query = query +" WHERE id_reco = " + rec.getId_reco();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(rec.getId_operacion());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int deleteRecoleccionOperacion(int id){
		
		
		
		int result = 0;
		
		Connection conn = null;
		String query=" DELETE FROM man_proyecto_manager_recoleccion "
					+"	WHERE id_reco = "+ id ;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}	

/**************************** BUSSNESS CASE ****************************************************************************************************************************/
	@Override
	public ObjEstudioBussnessCase getBussnessCaseOperacionByUid(int id){
		ObjEstudioBussnessCase bc = new ObjEstudioBussnessCase();
		Connection conn = null;
		
		String query = " SELECT id_ceps_sam "
					   +"   ,id_operacion "
					   +"   ,b_case_1_ceps_sam "
					   +"   ,b_case_1_text_ceps_sam "
					   +"   ,b_case_2_ceps_sam "
					   +"   ,b_case_2_text_ceps_sam "
					   +"   ,b_case_3_ceps_sam "
					   +"   ,b_case_4_ceps_sam "
					   +"   ,b_case_4_text_ceps_sam "
					   +"   ,b_case_5_ceps_sam "
					   +"   ,b_case_5_text_ceps_sam "
					   +"   ,b_case_6_text_ceps_sam "
					   +"   ,b_case_7_ceps_sam "
					   +"   ,b_case_7_text_ceps_sam " 
					   +"   ,b_case_8_ceps_sam "
					   +"   ,b_case_9_ceps_sam "
					   +"   ,b_case_9_text_ceps_sam "
					   +"   ,b_case_10_text_ceps_sam "
					   +"   ,b_case_11_text_ceps_sam "
					   +"   ,b_case_12_ceps_sam "
					   +"   ,b_case_13_ceps_sam "
					   +"   ,b_case_13_text_ceps_sam "
					   +"   ,b_case_14_text_ceps_sam "
					   +"   ,ceps_file_op_ceps_sam "
					   +"   ,ceps_file_dat_ceps_sam "
					   +"   ,ceps_file_master_ceps_sam "
					  +" FROM man_proyecto_principal_bussness_case  "
					  +" WHERE id_operacion =   " + id +" ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				 bc.setId_ceps_sam(rs.getInt("id_ceps_sam"));
				 bc.setId_operacion(id);
				 bc.setB_case_1_ceps_sam(rs.getString("b_case_1_ceps_sam"));
				 bc.setB_case_1_text_ceps_sam(rs.getString("b_case_1_text_ceps_sam"));
				 bc.setB_case_2_ceps_sam(rs.getString("b_case_2_ceps_sam"));
				 bc.setB_case_2_text_ceps_sam(rs.getString("b_case_2_text_ceps_sam"));
				 bc.setB_case_3_ceps_sam(rs.getString("b_case_3_ceps_sam"));
				 bc.setB_case_4_ceps_sam(rs.getString("b_case_4_ceps_sam"));
				 bc.setB_case_4_text_ceps_sam(rs.getString("b_case_4_text_ceps_sam"));
				 bc.setB_case_5_ceps_sam(rs.getString("b_case_5_ceps_sam"));
				 bc.setB_case_5_text_ceps_sam(rs.getString("b_case_5_text_ceps_sam"));
				 bc.setB_case_6_text_ceps_sam(rs.getString("b_case_6_text_ceps_sam"));
				 bc.setB_case_7_ceps_sam(rs.getString("b_case_7_ceps_sam"));
				 bc.setB_case_7_text_ceps_sam(rs.getString("b_case_7_text_ceps_sam"));
				 bc.setB_case_8_ceps_sam(rs.getString("b_case_8_ceps_sam"));
				 bc.setB_case_9_ceps_sam(rs.getString("b_case_9_ceps_sam"));
				 bc.setB_case_9_text_ceps_sam(rs.getString("b_case_9_text_ceps_sam"));
				 bc.setB_case_10_text_ceps_sam(rs.getString("b_case_10_text_ceps_sam"));
				 bc.setB_case_11_text_ceps_sam(rs.getString("b_case_11_text_ceps_sam"));
				 bc.setB_case_12_ceps_sam(rs.getString("b_case_12_ceps_sam"));
				 bc.setB_case_13_ceps_sam(rs.getString("b_case_13_ceps_sam"));
				 bc.setB_case_13_text_ceps_sam(rs.getString("b_case_13_text_ceps_sam"));
				 bc.setB_case_14_text_ceps_sam(rs.getString("b_case_14_text_ceps_sam"));
				 bc.setCeps_file_op_ceps_sam(rs.getString("ceps_file_op_ceps_sam"));
				 bc.setCeps_file_dat_ceps_sam(rs.getString("ceps_file_dat_ceps_sam"));
				 bc.setCeps_file_master_ceps_sam(rs.getString("ceps_file_master_ceps_sam"));
				 
			  }
			  
			  return bc;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public ObjResultCreaCotOp setBussnessCaseOperacion(ObjEstudioBussnessCase bc){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" INSERT INTO man_proyecto_principal_bussness_case "
			         +"  (id_operacion "
			         +"  ,b_case_1_ceps_sam "
			         +"  ,b_case_1_text_ceps_sam "
			         +"  ,b_case_2_ceps_sam "
			         +"  ,b_case_2_text_ceps_sam "
			         +"  ,b_case_3_ceps_sam "
			         +"  ,b_case_4_ceps_sam "
			         +"  ,b_case_4_text_ceps_sam "
			         +"  ,b_case_5_ceps_sam "
			         +"  ,b_case_5_text_ceps_sam "
			         +"  ,b_case_6_text_ceps_sam "
			         +"  ,b_case_7_ceps_sam "
			         +"  ,b_case_7_text_ceps_sam "
			         +"  ,b_case_8_ceps_sam "
			         +"  ,b_case_9_ceps_sam "
			         +"  ,b_case_9_text_ceps_sam "
			         +"  ,b_case_10_text_ceps_sam "
			         +"  ,b_case_11_text_ceps_sam "
			         +"  ,b_case_12_ceps_sam "
			         +"  ,b_case_13_ceps_sam "
			         +"  ,b_case_13_text_ceps_sam "
			         +"  ,b_case_14_text_ceps_sam "
			         +"  ,ceps_file_op_ceps_sam "
			         +"  ,ceps_file_dat_ceps_sam "
			         +"  ,ceps_file_master_ceps_sam) "
			     +" VALUES "
			     +"     ("+bc.getId_operacion()+ " "
			     +"      ,'"+bc.getB_case_1_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_1_text_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_2_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_2_text_ceps_sam()+"' "
			     +"     ,'"+bc.getB_case_3_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_4_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_4_text_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_5_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_5_text_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_6_text_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_7_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_7_text_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_8_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_9_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_9_text_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_10_text_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_11_text_ceps_sam()+"' "
			     +"     ,'"+bc.getB_case_12_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_13_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_13_text_ceps_sam()+"' "
			     +"      ,'"+bc.getB_case_14_text_ceps_sam()+"' "
			     +"      ,'"+bc.getCeps_file_op_ceps_sam()+"' "
			     +"      ,'"+bc.getCeps_file_dat_ceps_sam()+"' "
			     +"      ,'"+bc.getCeps_file_master_ceps_sam()+"' )  "; 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(bc.getId_operacion());
			
			return result;
			
		} catch (SQLException e) {
			//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public ObjResultCreaCotOp updateBussnessCaseOperacion(ObjEstudioBussnessCase bc){
		
		
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" UPDATE man_proyecto_principal_bussness_case "
					 +"  SET id_operacion = "+bc.getId_operacion()+" "
					 +"     ,b_case_1_ceps_sam = '"+bc.getB_case_1_ceps_sam()+"' "
					 +"     ,b_case_1_text_ceps_sam = '"+bc.getB_case_1_text_ceps_sam()+"' "
					 +"     ,b_case_2_ceps_sam = '"+bc.getB_case_2_ceps_sam()+"' "
					 +"     ,b_case_2_text_ceps_sam = '"+bc.getB_case_2_text_ceps_sam()+"' "
					 +"     ,b_case_3_ceps_sam = '"+bc.getB_case_3_ceps_sam()+"' "
					 +"     ,b_case_4_ceps_sam = '"+bc.getB_case_4_ceps_sam()+"' "
					 +"     ,b_case_4_text_ceps_sam = '"+bc.getB_case_4_text_ceps_sam()+"' "
					 +"     ,b_case_5_ceps_sam = '"+bc.getB_case_5_ceps_sam()+"' "
					 +"     ,b_case_5_text_ceps_sam = '"+bc.getB_case_5_text_ceps_sam()+"' "
					 +"     ,b_case_6_text_ceps_sam = '"+bc.getB_case_6_text_ceps_sam()+"' "
					 +"     ,b_case_7_ceps_sam = '"+bc.getB_case_7_ceps_sam()+"' "
					 +"     ,b_case_7_text_ceps_sam = '"+bc.getB_case_7_text_ceps_sam()+"' "
					 +"     ,b_case_8_ceps_sam = '"+bc.getB_case_8_ceps_sam()+"' "
					 +"     ,b_case_9_ceps_sam = '"+bc.getB_case_9_ceps_sam()+"' "
					 +"     ,b_case_9_text_ceps_sam = '"+bc.getB_case_9_text_ceps_sam()+"' "
					 +"     ,b_case_10_text_ceps_sam = '"+bc.getB_case_10_text_ceps_sam()+"' "
					 +"     ,b_case_11_text_ceps_sam = '"+bc.getB_case_11_text_ceps_sam()+"' "
					 +"     ,b_case_12_ceps_sam = '"+bc.getB_case_12_ceps_sam()+"' "
					 +"     ,b_case_13_ceps_sam = '"+bc.getB_case_13_ceps_sam()+"' "
					 +"     ,b_case_13_text_ceps_sam = '"+bc.getB_case_13_text_ceps_sam()+"' "
					 +"     ,b_case_14_text_ceps_sam = '"+bc.getB_case_14_text_ceps_sam()+"' "
					 +"     ,ceps_file_op_ceps_sam = '"+bc.getCeps_file_op_ceps_sam()+"' "
					 +"     ,ceps_file_dat_ceps_sam = '"+bc.getCeps_file_dat_ceps_sam()+"' "
					 +"     ,ceps_file_master_ceps_sam = '"+bc.getCeps_file_master_ceps_sam()+"' "
					 +" WHERE id_operacion = " + bc.getId_operacion();
					
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(bc.getId_operacion());
			
			return result;
			
		} catch (SQLException e) {
			//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	@Override
	public int deleteBussnessCaseOperacion(int id){
		
		
		
		int result = 0;
		
		Connection conn = null;
		String query=" DELETE FROM man_proyecto_principal_bussness_case "
					+"	WHERE id_operacion = "+ id ;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			return result;
			
		} catch (SQLException e) {
			//return 2;
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	
	
	@Override
	public void updateColaEstudio(int nuevo_estado , int id_operacion){
		
		
		
		Connection conn = null;
		String query=" UPDATE man_proyecto_manager_medicion "
			         +"  SET cola_operacion = " + nuevo_estado  + " WHERE id_operacion= " + id_operacion;
			         
			 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			
    	} catch (SQLException e) {
    		
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	
	
	
	
	

	
}