package cl.nexo.manager.imp.proyecto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.proyecto.AccessProyecto;
import cl.nexo.manager.obj.proyecto.ObjProyectoEstandar;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Proyecto implements AccessProyecto {
	private static final Logger logger = Logger.getLogger(Proyecto.class);
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
	public String getKeyProyecto(){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		int longKey = Integer.valueOf(tools.getValorConfigById(29));
		
		String key = new BigInteger(130, random).toString(longKey);
		
		return key;
	}
	
	@Override
	public int getCodOperacion(Long op){
		int result = 0;
		
		return result;
	}
	
	@Override 
	public ObjProyectoEstandar getProyectoPadre(Long id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		
		ObjProyectoEstandar res = new ObjProyectoEstandar();
		
		Connection conn = null;
		String query = " SELECT id_proyectom "
					   +"   ,codigo_proyectom "
					   +"   ,nombre_proyectom "
					   +"   ,estado_proyectom "
					   +"   ,id_cliente "
					   +"   ,fcreacion_proyectom "
					   +"   ,screacion_proyectom "
					   +"   ,fmod_proyectom "
					   +"   ,smod_proyectom "
					   +"   ,felimina_proyectom "
					   +"   ,selimina_proyectom "
					   +"   ,elimina_proyectom "
					   +" FROM man_proyecto_manager_principal "
					   +" WHERE id_proyectom = ? ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ps.setLong(1, id);
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
				  res.setId_proyectom(rs.getLong("id_proyectom"));
				  res.setCodigo_proyectom(rs.getString("codigo_proyectom"));
				  res.setNombre_proyectop(rs.getString("nombre_proyectom"));
				  res.setEstado_proyectop(rs.getInt("estado_proyectom"));
				  res.setId_cliente(rs.getInt("id_cliente"));
				  res.setFcreacion_proyectom(format3.format(rs.getTime("fcreacion_proyectom")));
				  res.setScreacion_proyectom(rs.getInt("screacion_proyectom"));
				  res.setStr_screa(logins.getStrLoginById(rs.getInt("screacion_proyectom")));
				  res.setFmod_proyectom(format3.format(rs.getTime("fmod_proyectom")));
				  res.setSmod_proyectom(rs.getInt("smod_proyectom"));
				  res.setStr_smod(logins.getStrLoginById(rs.getInt("smod_proyectom")));
				  if(rs.getDate("felimina_proyectom")!= null){
					  res.setFelimina_proyectom(format3.format(rs.getTime("felimina_proyectom")));
					  res.setSelimina_proyectom(rs.getInt("selimina_proyectom"));
					  res.setStr_seliminap(logins.getStrLoginById(rs.getInt("selimina_proyectom")));
				  }
				  res.setElimina_proyectom(rs.getInt("elimina_proyectom"));
				  
				  
			  }else{
				  res.setId_proyectom(new Long("0"));
			  }
			  
			  return res;
			  
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
	public ObjProyectoEstandar getOperacion(Long id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		
		ObjProyectoEstandar res = new ObjProyectoEstandar();
		
		Connection conn = null;
		String query = "  SELECT id_operacion "
						+"      ,id_proyectom "
						+"      ,cod_sam "
						+"      ,cod_operacion "
						+"      ,cod_manager "
						+"      ,area_medicion "
						+"      ,nombre_operacion "
						+"      ,estado_medicion "
						+"      ,orden_medicion "
						+"      ,cola_operacion "
						+"      ,priori_operacion "
						+"      ,fcrea_medicion "
						+"      ,screa_medicion "
						+"      ,factivacion_medicion "
						+"      ,sactivacion_medicion "
						+"      ,fmod_medicion "
						+"      ,smod_medicion "
						+"      ,felimina_medicion "
						+"      ,selimina_medicion "
						+"      ,elimina_medicion "
						+"      ,fingreso_puesta_marcha "
						+"      ,fsalida_puesta_marcha "
						+"      ,fingreso_implementacion "
						+"      ,fsalida_implementacion "
						+"      ,fingreso_recoleccion "
						+"      ,fsalida_recoleccion "
						+"      ,fingreso_codificacion "
						+"      ,fsalida_codificacion "
						+"      ,fingreso_digitacion "
						+"      ,fsalida_digitacion "
						+"      ,fingreso_depuracion "
						+"      ,fsalida_depuracion "
						+"      ,fingreso_tabulacion "
						+"      ,fsalida_tabulacion "
						+"      ,fingreso_entrega "
						+"      ,fsalida_entrega "
						+"  FROM man_proyecto_manager_medicion "
					   +" WHERE id_operacion = ? ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ps.setLong(1, id);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
				  res.setId_operacion(rs.getLong("id_operacion"));
				  res.setId_proyectom(rs.getLong("id_proyectom"));
				  res.setCod_sam(rs.getString("cod_sam"));
				  res.setCod_operacion(rs.getInt("cod_operacion"));
				  res.setCod_manager(rs.getString("cod_manager"));
				  res.setArea_medicion(rs.getInt("area_medicion"));
				  res.setNombre_proyectop(rs.getString("nombre_operacion"));
				  res.setEstado_medicion(rs.getInt("estado_medicion"));
				  res.setOrden_medicion(rs.getInt("orden_medicion"));
				  res.setCola_operacion(rs.getInt("cola_operacion"));
				  res.setPriori_operacion(rs.getInt("priori_operacion"));
				  res.setFcrea_operacion(format3.format(rs.getTime("fcrea_medicion")));
				  res.setScrea_operacion(rs.getInt("screa_medicion"));
				  res.setStr_screacion(logins.getStrLoginById(rs.getInt("screa_medicion")));
				  res.setFactivacion_operacion(format3.format(rs.getTime("factivacion_medicion")));
				  res.setSactivacion_operacion(rs.getInt("sactivacion_medicion"));
				  res.setStr_sactivacion(logins.getStrLoginById(rs.getInt("sactivacion_medicion")));
				  res.setFmod_operacion(format3.format(rs.getTime("fmod_medicion")));
				  res.setSmod_operacion(rs.getInt("smod_medicion"));
				  res.setStr_smod(logins.getStrLoginById(rs.getInt("smod_medicion")));
				  if(rs.getDate("felimina_medicion")!=null){
					  res.setFelimina_operacion(format3.format(rs.getTime("felimina_medicion")));
					  res.setSelimina_operacion(rs.getInt("selimina_medicion"));
					  res.setStr_selimina_operacion(logins.getStrLoginById(rs.getInt("selimina_medicion")));
				  }
				  res.setElimina_operacion(rs.getInt("elimina_medicion"));
				  
				  if(rs.getDate("fingreso_puesta_marcha")!=null){
					  res.setFingreso_puesta_marcha_operacion(format3.format(rs.getTime("fingreso_puesta_marcha")));
				  }
				  if(rs.getDate("fsalida_puesta_marcha")!=null){
					  res.setFsalida_puesta_marcha_operacion(format3.format(rs.getTime("fsalida_puesta_marcha")));
				  }
				  if(rs.getDate("fingreso_implementacion")!=null){
					  res.setFingreso_implementacion_operacion(format3.format(rs.getTime("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!=null){
					  res.setFsalida_implementacion_operacion(format3.format(rs.getTime("fsalida_implementacion")));
				  }
				  if(rs.getDate("fingreso_recoleccion")!=null){
					  res.setFingreso_recoleccion_operacion(format3.format(rs.getTime("fingreso_recoleccion")));
				  }
				  if(rs.getDate("fsalida_recoleccion")!=null){
					  res.setFsalida_recoleccion_operacion(format3.format(rs.getTime("fsalida_recoleccion")));
				  }
				  if(rs.getDate("fingreso_codificacion")!=null){
					  res.setFingreso_codificacion_operacion(format3.format(rs.getTime("fingreso_codificacion")));
				  }
				  if(rs.getDate("fsalida_codificacion")!=null){
					  res.setFsalida_codificacion_operacion(format3.format(rs.getTime("fsalida_codificacion")));
				  }
				  if(rs.getDate("fingreso_digitacion")!=null){
					  res.setFingreso_digitacion_operacion(format3.format(rs.getTime("fingreso_digitacion")));
				  }
				  if(rs.getDate("fsalida_digitacion")!=null){
					  res.setFsalida_digitacion_operacion(format3.format(rs.getTime("fsalida_digitacion")));
				  }
				  if(rs.getDate("fingreso_depuracion")!=null){
					  res.setFingreso_depuracion_operacion(format3.format(rs.getTime("fingreso_depuracion")));
				  }
				  if(rs.getDate("fsalida_depuracion")!=null){
					  res.setFsalida_depuracion_operacion(format3.format(rs.getTime("fsalida_depuracion")));
				  }
				  if(rs.getDate("fingreso_tabulacion")!=null){
					  res.setFingreso_tabulacion_operacion(format3.format(rs.getTime("fingreso_tabulacion")));
				  }
				  if(rs.getDate("fsalida_tabulacion")!=null){
					  res.setFsalida_tabulacion_operacion(format3.format(rs.getTime("fsalida_tabulacion")));
				  }
				  if(rs.getDate("fingreso_entrega")!=null){
					  res.setFingreso_entrega_operacion(format3.format(rs.getTime("fingreso_entrega")));
				  }
				  if(rs.getDate("fsalida_entrega")!=null){
					  res.setFsalida_entrega_operacion(format3.format(rs.getTime("fsalida_entrega")));
				  }
				  
			  }else{
				  res.setId_operacion(new Long("0"));
			  }
			  
			  return res;
			  
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
	public ObjProyectoEstandar getOperacionDetalle(Long id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		
		ObjProyectoEstandar res = new ObjProyectoEstandar();
		
		Connection conn = null;
		String query = " SELECT id_manager "
						     +" ,id_operacion "
						     +" ,id_clie_facturar "
						     +" ,precio_venta "
						     +" ,precio_venta_uf_manager "
						     +" ,tipo_estudio "
						     +" ,muestra_manager "
						     +" ,canal_manager "
						     +" ,res_us1_op "
						     +" ,res_us2_op "
						     +" ,res_us3_op "
						     +" ,id_area_recopilacion "
						     +" ,id_canal_recopilacion "
						     +" ,submodo_cot "
						     +" ,revision_pd_manager "
						     +" ,supervision_pd_manager "
						     +" ,codificacion_pd_manager "
						     +" ,digitacion_pd_manager "
						     +" ,procesamiento_pd_manager "
						     +" ,porcentaje_supervicion_ra_manager "
						     +" ,porcentaje_re_digitacion_ra_manager "
						     +" ,libro_codigo_ra_manager "
						     +" ,verbatims_ra_manager "
						     +" ,pre_proceso_ra_manager "
						     +" ,abiertas_codificar_od_manager "
						     +" ,otros_codificar_od_manager "
						     +" ,tmo_od_manager "
						     +" ,id_industria "
						     +" ,id_producto "
						     +" ,id_tipo_producto1 "
						     +" ,id_tipo_producto2 "
						     +" ,id_digital "
						     +" ,fingreso_implementacion "
						     +" ,fsalida_implementacion "
						     +" ,fingreso_campo "
						     +" ,fsalida_campo "
						     +" ,fentrada_base "
						     +" ,fsalida_base "
						     +" ,fentrada_presentacion "
						     +" ,weekly_semana "
						     +" ,weekly_ano "
						     +" FROM man_proyecto_manager "
						     +" WHERE id_proyectom = ? ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ps.setLong(1, id);
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
				  res.setId_manager(rs.getLong("id_manager"));
				  res.setId_operacion(rs.getLong("id_operacion"));
				  res.setId_cliente_factura(rs.getInt("id_clie_facturar"));
				  res.setPrecio_venta_manager(rs.getLong("precio_venta"));
				  res.setPrecio_venta_uf_manager(rs.getFloat("precio_venta_uf_manager"));
				  res.setId_tipo_estudio(rs.getInt("tipo_estudio"));
				  res.setMuestra_manager(rs.getInt("muestra_manager"));
				  res.setCanal_manager(rs.getInt("canal_manager"));
				  res.setId_user_coordinador_manager(rs.getInt("res_us1_op"));
				  res.setId_user_director_estudio_manager(rs.getInt("res_us2_op"));
				  res.setId_user_jefe_estudio_manager(rs.getInt("res_us3_op"));
				  res.setId_area_recopilacion(rs.getInt("id_area_recopilacion"));
				  res.setId_canal_recopilacion(rs.getInt("id_canal_recopilacion"));
				  res.setId_sub_modo_recopilacion(rs.getInt("submodo_cot"));
				  res.setRevision_pd_manager(rs.getInt("revision_pd_manager"));
				  res.setSupervision_pd_manager(rs.getInt("supervision_pd_manager"));
				  res.setCodificacion_pd_manager(rs.getInt("codificacion_pd_manager"));
				  res.setDigitacion_pd_manager(rs.getInt("digitacion_pd_manager"));
				  res.setProcesamiento_pd_manager(rs.getInt("procesamiento_pd_manager"));
				  res.setPorcentaje_supervicion_ra_manager(rs.getInt("porcentaje_supervicion_ra_manager"));
				  res.setPorcentaje_re_digitacion_ra_manager(rs.getInt("porcentaje_re_digitacion_ra_manager"));
				  res.setLibro_codigo_ra_manager(rs.getInt("libro_codigo_ra_manager"));
				  res.setVerbatims_ra_manager(rs.getInt("verbatims_ra_manager"));
				  res.setPre_proceso_ra_manager(rs.getInt("pre_proceso_ra_manager"));
				  res.setAbiertas_codificar_od_manager(rs.getInt("abiertas_codificar_od_manager"));
				  res.setOtros_codificar_od_manager(rs.getInt("otros_codificar_od_manager"));
				  res.setTmo_od_manager(rs.getInt("tmo_od_manager"));
				  res.setId_industria(rs.getInt("id_industria"));
				  res.setId_producto(rs.getInt("id_producto"));
				  res.setId_tipo_producto1(rs.getInt("id_tipo_producto1"));
				  res.setId_tipo_producto2(rs.getInt("id_tipo_producto2"));
				  res.setId_digital(rs.getInt("id_digital"));
				  
				  if(rs.getDate("fingreso_implementacion")!=null){
					  res.setFingreso_implementacion_proyecto(format3.format(rs.getTime("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!=null){
					  res.setFsalida_implementacion_proyecto(format3.format(rs.getTime("fsalida_implementacion")));
				  }
				  if(rs.getDate("fingreso_campo")!=null){
					  res.setFingreso_campo_proyecto(format3.format(rs.getTime("fingreso_campo")));
				  }
				  if(rs.getDate("fsalida_campo")!=null){
					  res.setFsalida_campo_proyecto(format3.format(rs.getTime("fsalida_campo")));
				  }
				  if(rs.getDate("fentrada_base")!=null){
					  res.setFentrada_base_proyecto(format3.format(rs.getTime("fentrada_base")));
				  }
				  if(rs.getDate("fsalida_base")!=null){
					  res.setFsalida_base_proyecto(format3.format(rs.getTime("fsalida_base")));
				  }
				  if(rs.getDate("fentrada_presentacion")!=null){
					  res.setFentrada_presentacion(format3.format(rs.getTime("fentrada_presentacion")));
				  }
				  res.setWeekly_semana(rs.getInt("weekly_semana"));
				  res.setWeekly_ano(rs.getInt("weekly_ano"));
				  
				  
				  
				  
				  
			  }else{
				  res.setId_manager(new Long("0"));
			  }
			  
			  return res;
			  
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
	public ObjProyectoEstandar getProyecto(Long id, int tipo){
		/*
		 * tipo = 1 -- Operacion
		 * tipo = 2 -- Proyecto p
		 * */
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		ObjProyectoEstandar res = new ObjProyectoEstandar();
		Connection conn = null;
		
		String query =	" SELECT p.id_proyectom "
				     +" ,p.codigo_proyectom "
				     +" ,p.nombre_proyectom "
				     +" ,p.estado_proyectom "
				     +" ,p.id_cliente "
					 +" ,c1.nombre_cliente "
				     +" ,p.fcreacion_proyectom "
				     +" ,p.screacion_proyectom "
				     +" ,p.fmod_proyectom "
				     +" ,p.smod_proyectom "
				     +" ,p.felimina_proyectom "
				     +" ,p.selimina_proyectom "
				     +" ,p.elimina_proyectom "
					 +" ,m.id_operacion "
				     +" ,m.cod_sam "
				     +" ,m.cod_operacion "
				     +" ,m.cod_manager "
				     +" ,m.area_medicion "
					 +" ,a.cod_area "
					 +" ,a.nombre_area "
				     +" ,m.nombre_operacion "
				     +" ,m.estado_medicion "
				     +" ,m.orden_medicion "
				     +" ,m.cola_operacion "
				     +" ,m.priori_operacion "
				     +" ,m.fcrea_medicion "
				     +" ,m.screa_medicion "
				     +" ,m.factivacion_medicion "
				     +" ,m.sactivacion_medicion "
				     +" ,m.fmod_medicion "
				     +" ,m.smod_medicion "
				     +" ,m.felimina_medicion "
				     +" ,m.selimina_medicion "
				     +" ,m.elimina_medicion "
				     +" ,m.fingreso_puesta_marcha "
				     +" ,m.fsalida_puesta_marcha "
				     +" ,m.fingreso_implementacion "
				     +" ,m.fsalida_implementacion "
				     +" ,m.fingreso_recoleccion "
				     +" ,m.fsalida_recoleccion "
				     +" ,m.fingreso_codificacion "
				     +" ,m.fsalida_codificacion "
				     +" ,m.fingreso_digitacion "
				     +" ,m.fsalida_digitacion "
				     +" ,m.fingreso_depuracion "
				     +" ,m.fsalida_depuracion "
				     +" ,m.fingreso_tabulacion "
				     +" ,m.fsalida_tabulacion "
				     +" ,m.fingreso_entrega "
				     +" ,m.fsalida_entrega "
					 +" ,o.id_manager "
				     +" ,o.id_clie_facturar "
					 +" ,(c2.nombre_cliente) as nombre_cliente_factura "
				     +" ,o.precio_venta "
				     +" ,o.precio_costo_manager "
				     +" ,o.precio_venta_uf_manager "
				     +" ,o.tipo_estudio "
				     +" ,o.muestra_manager "
				     +" ,o.canal_manager "
				     +" ,o.res_us1_op "
				     +" ,o.res_us2_op "
				     +" ,o.res_us3_op "
				     +" ,o.id_area_recopilacion "
				     +" ,o.id_canal_recopilacion "
				     +" ,o.submodo_cot "
				     +" ,o.revision_pd_manager "
				     +" ,o.supervision_pd_manager "
				     +" ,o.codificacion_pd_manager "
				     +" ,o.digitacion_pd_manager "
				     +" ,o.procesamiento_pd_manager "
				     +" ,o.porcentaje_supervicion_ra_manager "
				     +" ,o.porcentaje_re_digitacion_ra_manager "
				     +" ,o.libro_codigo_ra_manager "
				     +" ,o.verbatims_ra_manager "
				     +" ,o.pre_proceso_ra_manager "
				     +" ,o.abiertas_codificar_od_manager "
				     +" ,o.otros_codificar_od_manager "
				     +" ,o.tmo_od_manager "
				     +" ,o.id_industria "
				     +" ,o.id_producto "
				     +" ,o.id_tipo_producto1 "
				     +" ,o.id_tipo_producto2 "
				     +" ,o.id_digital "
				     +" ,o.fingreso_implementacion "
				     +" ,o.fsalida_implementacion "
				     +" ,o.fingreso_campo "
				     +" ,o.fsalida_campo "
				     +" ,o.fentrada_base "
				     +" ,o.fsalida_base "
				     +" ,o.fentrada_presentacion "
				     +" ,o.weekly_semana "
				     +" ,o.weekly_ano "
				     +" ,m.id_tipo_entrevista "
				     +" ,e.nombre_tipo_entrevista "
				     + ",f.nombre_flujo "
				     +" ,nombre_tipo_estudio"
				+" FROM man_proyecto_manager_principal p "
				+" INNER JOIN man_proyecto_manager_medicion m ON p.id_proyectom = m.id_proyectom "
				+" LEFT JOIN man_area a ON m.area_medicion = a.id_area "
				+" LEFT JOIN man_cliente c1 ON c1.id_cliente = p.id_cliente " 
				+" INNER JOIN man_proyecto_manager o ON o.id_operacion = m.id_operacion "
				+" LEFT JOIN man_cliente c2 ON c2.id_cliente = o.id_clie_facturar "
				+" LEFT JOIN man_tipo_entrevista e ON m.id_tipo_entrevista = e.id_tipo_entrevista "
				+" LEFT JOIN man_flujo f ON f.id_flujo = m.estado_medicion "
				+" LEFT JOIN man_tipo_estudio t ON t.id_tipo_estudio = o.tipo_estudio "
				+" WHERE "
				+" p.elimina_proyectom = 0 "
				+" AND m.elimina_medicion = 0 ";
				
				if(tipo == 1){
					query = query + " AND m.id_operacion = ? "
								  +" ORDER BY "
								  +" m.fcrea_medicion DESC ";
				}
				if(tipo == 2){
					query = query + " AND m.id_proyectom = ? "
							  +" ORDER BY "
							  +" m.orden_medicion DESC ";
				}
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ps.setLong(1, id);
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
				  res.setId_proyectom(rs.getLong("id_proyectom"));
				  res.setCodigo_proyectom(rs.getString("codigo_proyectom"));
				  res.setNombre_proyectop(rs.getString("nombre_proyectom"));
				  res.setEstado_proyectop(rs.getInt("estado_proyectom"));
				  res.setId_cliente(rs.getInt("id_cliente"));
				  res.setNombre_cliente(rs.getString("nombre_cliente"));
				  res.setFcreacion_proyectom(format3.format(rs.getDate("fcreacion_proyectom")));
				  res.setScreacion_proyectom(rs.getInt("screacion_proyectom"));
				  res.setStr_screa(logins.getStrLoginById(rs.getInt("screacion_proyectom")));
				  res.setFmod_proyectom(format3.format(rs.getDate("fmod_proyectom")));
				  res.setSmod_proyectom(rs.getInt("smod_proyectom"));
				  res.setStr_smod(logins.getStrLoginById(rs.getInt("smod_proyectom")));
				  if(rs.getDate("felimina_proyectom")!= null){
					  res.setFelimina_proyectom(format3.format(rs.getDate("felimina_proyectom")));
					  res.setSelimina_proyectom(rs.getInt("selimina_proyectom"));
					  res.setStr_seliminap(logins.getStrLoginById(rs.getInt("selimina_proyectom")));
				  }
				  res.setElimina_proyectom(rs.getInt("elimina_proyectom"));
				  
				  res.setId_operacion(rs.getLong("id_operacion"));
				  res.setCod_sam(rs.getString("cod_sam"));
				  res.setCod_operacion(rs.getInt("cod_operacion"));
				  res.setCod_manager(rs.getString("cod_manager"));
				  res.setArea_medicion(rs.getInt("area_medicion"));
				  res.setCod_area(rs.getString("cod_area"));
				  res.setNombre_area(rs.getString("nombre_area"));
				  res.setNombre_operacion(rs.getString("nombre_operacion"));
				  res.setEstado_medicion(rs.getInt("estado_medicion"));
				  res.setStr_estado_medicion(rs.getString("nombre_flujo"));
				  res.setOrden_medicion(rs.getInt("orden_medicion"));
				  res.setCola_operacion(rs.getInt("cola_operacion"));
				  res.setPriori_operacion(rs.getInt("priori_operacion"));
				  res.setFcrea_operacion(format3.format(rs.getDate("fcrea_medicion")));
				  res.setScrea_operacion(rs.getInt("screa_medicion"));
				  res.setStr_screacion(logins.getStrLoginById(rs.getInt("screa_medicion")));
				  if(rs.getDate("factivacion_medicion")!= null){
					  res.setFactivacion_operacion(format3.format(rs.getDate("factivacion_medicion")));
					  res.setSactivacion_operacion(rs.getInt("sactivacion_medicion"));
					  res.setStr_sactivacion(logins.getStrLoginById(rs.getInt("sactivacion_medicion")));
				  }
				  res.setFmod_operacion(format3.format(rs.getDate("fmod_medicion")));
				  res.setSmod_operacion(rs.getInt("smod_medicion"));
				  res.setStr_smod(logins.getStrLoginById(rs.getInt("smod_medicion")));
				  if(rs.getDate("felimina_medicion")!=null){
					  res.setFelimina_operacion(format3.format(rs.getDate("felimina_medicion")));
					  res.setSelimina_operacion(rs.getInt("selimina_medicion"));
					  res.setStr_selimina_operacion(logins.getStrLoginById(rs.getInt("selimina_medicion")));
				  }
				  res.setElimina_operacion(rs.getInt("elimina_medicion"));
				  
				  if(rs.getDate("fingreso_puesta_marcha")!=null){
					  res.setFingreso_puesta_marcha_operacion(format3.format(rs.getDate("fingreso_puesta_marcha")));
				  }
				  if(rs.getDate("fsalida_puesta_marcha")!=null){
					  res.setFsalida_puesta_marcha_operacion(format3.format(rs.getDate("fsalida_puesta_marcha")));
				  }
				  if(rs.getDate("fingreso_implementacion")!=null){
					  res.setFingreso_implementacion_operacion(format3.format(rs.getDate("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!=null){
					  res.setFsalida_implementacion_operacion(format3.format(rs.getDate("fsalida_implementacion")));
				  }
				  if(rs.getDate("fingreso_recoleccion")!=null){
					  res.setFingreso_recoleccion_operacion(format3.format(rs.getDate("fingreso_recoleccion")));
				  }
				  if(rs.getDate("fsalida_recoleccion")!=null){
					  res.setFsalida_recoleccion_operacion(format3.format(rs.getDate("fsalida_recoleccion")));
				  }
				  if(rs.getDate("fingreso_codificacion")!=null){
					  res.setFingreso_codificacion_operacion(format3.format(rs.getDate("fingreso_codificacion")));
				  }
				  if(rs.getDate("fsalida_codificacion")!=null){
					  res.setFsalida_codificacion_operacion(format3.format(rs.getDate("fsalida_codificacion")));
				  }
				  if(rs.getDate("fingreso_digitacion")!=null){
					  res.setFingreso_digitacion_operacion(format3.format(rs.getDate("fingreso_digitacion")));
				  }
				  if(rs.getDate("fsalida_digitacion")!=null){
					  res.setFsalida_digitacion_operacion(format3.format(rs.getDate("fsalida_digitacion")));
				  }
				  if(rs.getDate("fingreso_depuracion")!=null){
					  res.setFingreso_depuracion_operacion(format3.format(rs.getDate("fingreso_depuracion")));
				  }
				  if(rs.getDate("fsalida_depuracion")!=null){
					  res.setFsalida_depuracion_operacion(format3.format(rs.getDate("fsalida_depuracion")));
				  }
				  if(rs.getDate("fingreso_tabulacion")!=null){
					  res.setFingreso_tabulacion_operacion(format3.format(rs.getDate("fingreso_tabulacion")));
				  }
				  if(rs.getDate("fsalida_tabulacion")!=null){
					  res.setFsalida_tabulacion_operacion(format3.format(rs.getDate("fsalida_tabulacion")));
				  }
				  if(rs.getDate("fingreso_entrega")!=null){
					  res.setFingreso_entrega_operacion(format3.format(rs.getDate("fingreso_entrega")));
				  }
				  if(rs.getDate("fsalida_entrega")!=null){
					  res.setFsalida_entrega_operacion(format3.format(rs.getDate("fsalida_entrega")));
				  }
				  
				  res.setId_manager(rs.getLong("id_manager"));
				  
				  res.setId_cliente_factura(rs.getInt("id_clie_facturar"));
				  res.setNombre_cliente_factura(rs.getString("nombre_cliente_factura"));
				  res.setPrecio_venta_manager(rs.getLong("precio_venta"));
				  res.setPrecio_costo_manager(rs.getLong("precio_costo_manager"));
				  res.setPrecio_venta_uf_manager(rs.getFloat("precio_venta_uf_manager"));
				  res.setId_tipo_estudio(rs.getInt("id_tipo_estudio"));
				  res.setStr_tipo_estudio(rs.getString("nombre_tipo_estudio"));
				  
				  res.setId_tipo_entrevista(rs.getInt("id_tipo_entrevista"));
				  res.setStr_tipo_entrevista(rs.getString("nombre_tipo_entrevista"));
				  
				  res.setMuestra_manager(rs.getInt("muestra_manager"));
				  res.setCanal_manager(rs.getInt("canal_manager"));
				  res.setId_user_coordinador_manager(rs.getInt("res_us1_op"));
				  res.setId_user_director_estudio_manager(rs.getInt("res_us2_op"));
				  res.setId_user_jefe_estudio_manager(rs.getInt("res_us3_op"));
				  res.setId_area_recopilacion(rs.getInt("id_area_recopilacion"));
				  res.setId_canal_recopilacion(rs.getInt("id_canal_recopilacion"));
				  res.setId_sub_modo_recopilacion(rs.getInt("submodo_cot"));
				  res.setRevision_pd_manager(rs.getInt("revision_pd_manager"));
				  res.setSupervision_pd_manager(rs.getInt("supervision_pd_manager"));
				  res.setCodificacion_pd_manager(rs.getInt("codificacion_pd_manager"));
				  res.setDigitacion_pd_manager(rs.getInt("digitacion_pd_manager"));
				  res.setProcesamiento_pd_manager(rs.getInt("procesamiento_pd_manager"));
				  res.setPorcentaje_supervicion_ra_manager(rs.getInt("porcentaje_supervicion_ra_manager"));
				  res.setPorcentaje_re_digitacion_ra_manager(rs.getInt("porcentaje_re_digitacion_ra_manager"));
				  res.setLibro_codigo_ra_manager(rs.getInt("libro_codigo_ra_manager"));
				  res.setVerbatims_ra_manager(rs.getInt("verbatims_ra_manager"));
				  res.setPre_proceso_ra_manager(rs.getInt("pre_proceso_ra_manager"));
				  res.setAbiertas_codificar_od_manager(rs.getInt("abiertas_codificar_od_manager"));
				  res.setOtros_codificar_od_manager(rs.getInt("otros_codificar_od_manager"));
				  res.setTmo_od_manager(rs.getInt("tmo_od_manager"));
				  res.setId_industria(rs.getInt("id_industria"));
				  res.setId_producto(rs.getInt("id_producto"));
				  res.setId_tipo_producto1(rs.getInt("id_tipo_producto1"));
				  res.setId_tipo_producto2(rs.getInt("id_tipo_producto2"));
				  res.setId_digital(rs.getInt("id_digital"));
				  
				  if(rs.getDate("fingreso_implementacion")!=null){
					  res.setFingreso_implementacion_proyecto(format3.format(rs.getDate("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!=null){
					  res.setFsalida_implementacion_proyecto(format3.format(rs.getDate("fsalida_implementacion")));
				  }
				  if(rs.getDate("fingreso_campo")!=null){
					  res.setFingreso_campo_proyecto(format4.format(rs.getDate("fingreso_campo")));
				  }
				  if(rs.getDate("fsalida_campo")!=null){
					  res.setFsalida_campo_proyecto(format4.format(rs.getDate("fsalida_campo")));
				  }
				  if(rs.getDate("fentrada_base")!=null){
					  res.setFentrada_base_proyecto(format4.format(rs.getDate("fentrada_base")));
				  }
				  if(rs.getDate("fsalida_base")!=null){
					  res.setFsalida_base_proyecto(format4.format(rs.getDate("fsalida_base")));
				  }
				  if(rs.getDate("fentrada_presentacion")!=null){
					  res.setFentrada_presentacion(format4.format(rs.getDate("fentrada_presentacion")));
				  }
				  res.setWeekly_semana(rs.getInt("weekly_semana"));
				  res.setWeekly_ano(rs.getInt("weekly_ano"));
				  
				  
			  }else{
				  res.setId_operacion(new Long("0"));
			  }
			  
			  return res;
			  
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
	public ArrayList<ObjProyectoEstandar> getProyecto(int tipo, String id, int estado , int valorEstado){
		/*
		 * tipo = 0 -- TODO
		 * tipo = 1 -- cliente
		 * tipo = 2 -- Proyecto p
		 * tipo = 3 -- Coordinador
		 * tipo = 4 -- Director de estudios
		 * tipo = 5 -- Jefe de estudios
		 * tipo = 6 -- Area de proyecto
		 * */
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		ArrayList<ObjProyectoEstandar> ress = new ArrayList<ObjProyectoEstandar>();
		Connection conn = null;
		
		String query=" SELECT p.id_proyectom "
				     +" ,p.codigo_proyectom "
				     +" ,p.nombre_proyectom "
				     +" ,p.estado_proyectom "
				     +" ,p.id_cliente "
					 +" ,c1.nombre_cliente "
				     +" ,p.fcreacion_proyectom "
				     +" ,p.screacion_proyectom "
				     +" ,p.fmod_proyectom "
				     +" ,p.smod_proyectom "
				     +" ,p.felimina_proyectom "
				     +" ,p.selimina_proyectom "
				     +" ,p.elimina_proyectom "
					 +" ,m.id_operacion "
				     +" ,m.cod_sam "
				     +" ,m.cod_operacion "
				     +" ,m.cod_manager "
				     +" ,m.area_medicion "
					 +" ,a.cod_area "
					 +" ,a.nombre_area "
				     +" ,m.nombre_operacion "
				     +" ,m.estado_medicion "
				     +" ,m.orden_medicion "
				     +" ,m.cola_operacion "
				     +" ,m.priori_operacion "
				     +" ,m.fcrea_medicion "
				     +" ,m.screa_medicion "
				     +" ,m.factivacion_medicion "
				     +" ,m.sactivacion_medicion "
				     +" ,m.fmod_medicion "
				     +" ,m.smod_medicion "
				     +" ,m.felimina_medicion "
				     +" ,m.selimina_medicion "
				     +" ,m.elimina_medicion "
				     +" ,m.fingreso_puesta_marcha "
				     +" ,m.fsalida_puesta_marcha "
				     +" ,m.fingreso_implementacion "
				     +" ,m.fsalida_implementacion "
				     +" ,m.fingreso_recoleccion "
				     +" ,m.fsalida_recoleccion "
				     +" ,m.fingreso_codificacion "
				     +" ,m.fsalida_codificacion "
				     +" ,m.fingreso_digitacion "
				     +" ,m.fsalida_digitacion "
				     +" ,m.fingreso_depuracion "
				     +" ,m.fsalida_depuracion "
				     +" ,m.fingreso_tabulacion "
				     +" ,m.fsalida_tabulacion "
				     +" ,m.fingreso_entrega "
				     +" ,m.fsalida_entrega "
					 +" ,o.id_manager "
				     +" ,o.id_clie_facturar "
					 +" ,(c2.nombre_cliente) as nombre_cliente_factura "
				     +" ,o.precio_venta "
				     +" ,o.precio_venta_uf_manager "
				     +" ,o.tipo_estudio "
				     +" ,o.muestra_manager "
				     +" ,o.canal_manager "
				     +" ,o.res_us1_op "
				     +" ,o.res_us2_op "
				     +" ,o.res_us3_op "
				     +" ,o.id_area_recopilacion "
				     +" ,o.id_canal_recopilacion "
				     +" ,o.submodo_cot "
				     +" ,o.revision_pd_manager "
				     +" ,o.supervision_pd_manager "
				     +" ,o.codificacion_pd_manager "
				     +" ,o.digitacion_pd_manager "
				     +" ,o.procesamiento_pd_manager "
				     +" ,o.porcentaje_supervicion_ra_manager "
				     +" ,o.porcentaje_re_digitacion_ra_manager "
				     +" ,o.libro_codigo_ra_manager "
				     +" ,o.verbatims_ra_manager "
				     +" ,o.pre_proceso_ra_manager "
				     +" ,o.abiertas_codificar_od_manager "
				     +" ,o.otros_codificar_od_manager "
				     +" ,o.tmo_od_manager "
				     +" ,o.id_industria "
				     +" ,o.id_producto "
				     +" ,o.id_tipo_producto1 "
				     +" ,o.id_tipo_producto2 "
				     +" ,o.id_digital "
				     +" ,o.fingreso_implementacion "
				     +" ,o.fsalida_implementacion "
				     +" ,o.fingreso_campo "
				     +" ,o.fsalida_campo "
				     +" ,o.fentrada_base "
				     +" ,o.fsalida_base "
				     +" ,o.fentrada_presentacion "
				     +" ,o.weekly_semana "
				     +" ,o.weekly_ano "
				     +" ,m.id_tipo_entrevista "
				     +" ,e.nombre_tipo_entrevista "
				     + ",f.nombre_flujo "
				     +" ,nombre_tipo_estudio "
				     +" ,(l1.app_user)as app_coordinador "
				     +" ,(l1.nombre_user)as nombre_coordinador "
				     +" ,(l2.app_user)as app_director "
				     +" ,(l2.nombre_user)as nombre_director "
				     +" ,(l3.app_user)as app_jefe "
				     +" ,(l3.nombre_user)as nombre_jefe "
				+" FROM man_proyecto_manager_principal p "
				+" INNER JOIN man_proyecto_manager_medicion m ON p.id_proyectom = m.id_proyectom "
				+" LEFT JOIN man_area a ON m.area_medicion = a.id_area "
				+" LEFT JOIN man_cliente c1 ON c1.id_cliente = p.id_cliente " 
				+" INNER JOIN man_proyecto_manager o ON o.id_operacion = m.id_operacion "
				+" LEFT JOIN man_cliente c2 ON c2.id_cliente = o.id_clie_facturar "
				+" LEFT JOIN man_tipo_entrevista e ON m.id_tipo_entrevista = e.id_tipo_entrevista "
				+" LEFT JOIN man_flujo f ON f.id_flujo = m.estado_medicion "
				+" LEFT JOIN man_tipo_estudio t ON t.id_tipo_estudio = o.tipo_estudio "
				+" LEFT JOIN man_login_user l1 ON l1.id_user = o.res_us1_op "
				+" LEFT JOIN man_login_user l2 ON l2.id_user = o.res_us2_op "
				+" LEFT JOIN man_login_user l3 ON l3.id_user = o.res_us3_op "
				+" WHERE "
				+" p.elimina_proyectom = 0 "
				+" AND m.elimina_medicion = 0 ";
		
				if(estado == 1){
					query = query +" AND m.estado_medicion = "+ valorEstado +" ";
				}
				
				if(tipo == 0){
					query = query +" ORDER BY "
							  +" m.fcrea_medicion DESC ";
				}else if(tipo == 1){
					query = query + " AND p.id_cliente = "+Integer.parseInt(id)+" "
								  +" ORDER BY "
								  +" m.fcrea_medicion DESC ";
				}else if(tipo == 2){
					query = query + " AND m.id_proyectom = "+Integer.parseInt(id)+" "
							  +" ORDER BY "
							  +" m.fcrea_medicion DESC ";
				}else if(tipo == 3){
					query = query + " AND o.res_us1_op = "+Integer.parseInt(id)+" "
							  +" ORDER BY "
							  +" m.fcrea_medicion DESC ";
				}else if(tipo == 4){
					query = query + " AND o.res_us2_op = "+Integer.parseInt(id)+" "
							  +" ORDER BY "
							  +" m.fcrea_medicion DESC ";
				}else if(tipo == 5){
					query = query + " AND o.res_us3_op = "+Integer.parseInt(id)+" "
							  +" ORDER BY "
							  +" m.fcrea_medicion DESC ";
				}else if(tipo == 6){
					query = query + " AND o.area_medicion IN("+id+") "
							  +" ORDER BY "
							  +" m.fcrea_medicion DESC ";
				}
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.info(query);
			  ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjProyectoEstandar res = new ObjProyectoEstandar();
				  
				  res.setId_proyectom(rs.getLong("id_proyectom"));
				  res.setCodigo_proyectom(rs.getString("codigo_proyectom"));
				  res.setNombre_proyectop(rs.getString("nombre_proyectom"));
				  res.setEstado_proyectop(rs.getInt("estado_proyectom"));
				  res.setId_cliente(rs.getInt("id_cliente"));
				  res.setNombre_cliente(rs.getString("nombre_cliente"));
				  res.setFcreacion_proyectom(format3.format(rs.getDate("fcreacion_proyectom")));
				  res.setScreacion_proyectom(rs.getInt("screacion_proyectom"));
				  res.setStr_screa(logins.getStrLoginById(rs.getInt("screacion_proyectom")));
				  res.setFmod_proyectom(format3.format(rs.getDate("fmod_proyectom")));
				  res.setSmod_proyectom(rs.getInt("smod_proyectom"));
				  res.setStr_smod(logins.getStrLoginById(rs.getInt("smod_proyectom")));
				  if(rs.getDate("felimina_proyectom")!= null){
					  res.setFelimina_proyectom(format3.format(rs.getDate("felimina_proyectom")));
					  res.setSelimina_proyectom(rs.getInt("selimina_proyectom"));
					  res.setStr_seliminap(logins.getStrLoginById(rs.getInt("selimina_proyectom")));
				  }
				  res.setElimina_proyectom(rs.getInt("elimina_proyectom"));
				  
				  res.setId_operacion(rs.getLong("id_operacion"));
				  res.setCod_sam(rs.getString("cod_sam"));
				  res.setCod_operacion(rs.getInt("cod_operacion"));
				  res.setCod_manager(rs.getString("cod_manager"));
				  res.setArea_medicion(rs.getInt("area_medicion"));
				  res.setCod_area(rs.getString("cod_area"));
				  res.setNombre_area(rs.getString("nombre_area"));
				  res.setNombre_operacion(rs.getString("nombre_operacion"));
				  res.setEstado_medicion(rs.getInt("estado_medicion"));
				  res.setStr_estado_medicion(rs.getString("nombre_flujo"));
				  res.setOrden_medicion(rs.getInt("orden_medicion"));
				  res.setCola_operacion(rs.getInt("cola_operacion"));
				  res.setPriori_operacion(rs.getInt("priori_operacion"));
				  res.setFcrea_operacion(format3.format(rs.getDate("fcrea_medicion")));
				  res.setScrea_operacion(rs.getInt("screa_medicion"));
				  res.setStr_screacion(logins.getStrLoginById(rs.getInt("screa_medicion")));
				  if(rs.getDate("factivacion_medicion")!= null){
					  res.setFactivacion_operacion(format3.format(rs.getDate("factivacion_medicion")));
					  res.setSactivacion_operacion(rs.getInt("sactivacion_medicion"));
					  res.setStr_sactivacion(logins.getStrLoginById(rs.getInt("sactivacion_medicion")));
				  }
				  res.setFmod_operacion(format3.format(rs.getDate("fmod_medicion")));
				  res.setSmod_operacion(rs.getInt("smod_medicion"));
				  res.setStr_smod(logins.getStrLoginById(rs.getInt("smod_medicion")));
				  if(rs.getDate("felimina_medicion")!=null){
					  res.setFelimina_operacion(format3.format(rs.getDate("felimina_medicion")));
					  res.setSelimina_operacion(rs.getInt("selimina_medicion"));
					  res.setStr_selimina_operacion(logins.getStrLoginById(rs.getInt("selimina_medicion")));
				  }
				  res.setElimina_operacion(rs.getInt("elimina_medicion"));
				  
				  if(rs.getDate("fingreso_puesta_marcha")!=null){
					  res.setFingreso_puesta_marcha_operacion(format3.format(rs.getDate("fingreso_puesta_marcha")));
				  }
				  if(rs.getDate("fsalida_puesta_marcha")!=null){
					  res.setFsalida_puesta_marcha_operacion(format3.format(rs.getDate("fsalida_puesta_marcha")));
				  }
				  if(rs.getDate("fingreso_implementacion")!=null){
					  res.setFingreso_implementacion_operacion(format3.format(rs.getDate("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!=null){
					  res.setFsalida_implementacion_operacion(format3.format(rs.getDate("fsalida_implementacion")));
				  }
				  if(rs.getDate("fingreso_recoleccion")!=null){
					  res.setFingreso_recoleccion_operacion(format3.format(rs.getDate("fingreso_recoleccion")));
				  }
				  if(rs.getDate("fsalida_recoleccion")!=null){
					  res.setFsalida_recoleccion_operacion(format3.format(rs.getDate("fsalida_recoleccion")));
				  }
				  if(rs.getDate("fingreso_codificacion")!=null){
					  res.setFingreso_codificacion_operacion(format3.format(rs.getDate("fingreso_codificacion")));
				  }
				  if(rs.getDate("fsalida_codificacion")!=null){
					  res.setFsalida_codificacion_operacion(format3.format(rs.getDate("fsalida_codificacion")));
				  }
				  if(rs.getDate("fingreso_digitacion")!=null){
					  res.setFingreso_digitacion_operacion(format3.format(rs.getDate("fingreso_digitacion")));
				  }
				  if(rs.getDate("fsalida_digitacion")!=null){
					  res.setFsalida_digitacion_operacion(format3.format(rs.getDate("fsalida_digitacion")));
				  }
				  if(rs.getDate("fingreso_depuracion")!=null){
					  res.setFingreso_depuracion_operacion(format3.format(rs.getDate("fingreso_depuracion")));
				  }
				  if(rs.getDate("fsalida_depuracion")!=null){
					  res.setFsalida_depuracion_operacion(format3.format(rs.getDate("fsalida_depuracion")));
				  }
				  if(rs.getDate("fingreso_tabulacion")!=null){
					  res.setFingreso_tabulacion_operacion(format3.format(rs.getDate("fingreso_tabulacion")));
				  }
				  if(rs.getDate("fsalida_tabulacion")!=null){
					  res.setFsalida_tabulacion_operacion(format3.format(rs.getDate("fsalida_tabulacion")));
				  }
				  if(rs.getDate("fingreso_entrega")!=null){
					  res.setFingreso_entrega_operacion(format3.format(rs.getDate("fingreso_entrega")));
				  }
				  if(rs.getDate("fsalida_entrega")!=null){
					  res.setFsalida_entrega_operacion(format3.format(rs.getDate("fsalida_entrega")));
				  }
				  
				  res.setId_manager(rs.getLong("id_manager"));
				  
				  res.setId_cliente_factura(rs.getInt("id_clie_facturar"));
				  res.setNombre_cliente_factura(rs.getString("nombre_cliente_factura"));
				  res.setPrecio_venta_manager(rs.getLong("precio_venta"));
				  res.setPrecio_venta_uf_manager(rs.getFloat("precio_venta_uf_manager"));
				  res.setId_tipo_estudio(rs.getInt("tipo_estudio"));
				  res.setStr_tipo_estudio(rs.getString("nombre_tipo_estudio"));
				  
				  res.setId_tipo_entrevista(rs.getInt("id_tipo_entrevista"));
				  res.setStr_tipo_entrevista(rs.getString("nombre_tipo_entrevista"));
				  
				  res.setMuestra_manager(rs.getInt("muestra_manager"));
				  res.setCanal_manager(rs.getInt("canal_manager"));
				  res.setId_user_coordinador_manager(rs.getInt("res_us1_op"));
				  res.setStr_user_coordinador_manager(rs.getString("app_coordinador")+" "+ rs.getString("nombre_coordinador"));
				  res.setId_user_director_estudio_manager(rs.getInt("res_us2_op"));
				  res.setStr_user_director_estudio_manager(rs.getString("app_director")+" "+ rs.getString("nombre_director"));
				  res.setId_user_jefe_estudio_manager(rs.getInt("res_us3_op"));
				  res.setStr_user_jefe_estudio_manager(rs.getString("app_jefe")+" "+ rs.getString("nombre_jefe"));
				  res.setId_area_recopilacion(rs.getInt("id_area_recopilacion"));
				  res.setId_canal_recopilacion(rs.getInt("id_canal_recopilacion"));
				  res.setId_sub_modo_recopilacion(rs.getInt("submodo_cot"));
				  res.setRevision_pd_manager(rs.getInt("revision_pd_manager"));
				  res.setSupervision_pd_manager(rs.getInt("supervision_pd_manager"));
				  res.setCodificacion_pd_manager(rs.getInt("codificacion_pd_manager"));
				  res.setDigitacion_pd_manager(rs.getInt("digitacion_pd_manager"));
				  res.setProcesamiento_pd_manager(rs.getInt("procesamiento_pd_manager"));
				  res.setPorcentaje_supervicion_ra_manager(rs.getInt("porcentaje_supervicion_ra_manager"));
				  res.setPorcentaje_re_digitacion_ra_manager(rs.getInt("porcentaje_re_digitacion_ra_manager"));
				  res.setLibro_codigo_ra_manager(rs.getInt("libro_codigo_ra_manager"));
				  res.setVerbatims_ra_manager(rs.getInt("verbatims_ra_manager"));
				  res.setPre_proceso_ra_manager(rs.getInt("pre_proceso_ra_manager"));
				  res.setAbiertas_codificar_od_manager(rs.getInt("abiertas_codificar_od_manager"));
				  res.setOtros_codificar_od_manager(rs.getInt("otros_codificar_od_manager"));
				  res.setTmo_od_manager(rs.getInt("tmo_od_manager"));
				  res.setId_industria(rs.getInt("id_industria"));
				  res.setId_producto(rs.getInt("id_producto"));
				  res.setId_tipo_producto1(rs.getInt("id_tipo_producto1"));
				  res.setId_tipo_producto2(rs.getInt("id_tipo_producto2"));
				  res.setId_digital(rs.getInt("id_digital"));
				  
				  if(rs.getDate("fingreso_implementacion")!=null){
					  res.setFingreso_implementacion_proyecto(format3.format(rs.getDate("fingreso_implementacion")));
				  }
				  if(rs.getDate("fsalida_implementacion")!=null){
					  res.setFsalida_implementacion_proyecto(format3.format(rs.getDate("fsalida_implementacion")));
				  }
				  if(rs.getDate("fingreso_campo")!=null){
					  res.setFingreso_campo_proyecto(format3.format(rs.getDate("fingreso_campo")));
				  }
				  if(rs.getDate("fsalida_campo")!=null){
					  res.setFsalida_campo_proyecto(format3.format(rs.getDate("fsalida_campo")));
				  }
				  if(rs.getDate("fentrada_base")!=null){
					  res.setFentrada_base_proyecto(format3.format(rs.getDate("fentrada_base")));
				  }
				  if(rs.getDate("fsalida_base")!=null){
					  res.setFsalida_base_proyecto(format3.format(rs.getDate("fsalida_base")));
				  }
				  if(rs.getDate("fentrada_presentacion")!=null){
					  res.setFentrada_presentacion(format3.format(rs.getDate("fentrada_presentacion")));
				  }
				  res.setWeekly_semana(rs.getInt("weekly_semana"));
				  res.setWeekly_ano(rs.getInt("weekly_ano"));
				  
				  ress.add(res);
			  }
			  logger.info("size: " + ress.size());
			  return ress;
			  
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
	public Long createProyectoPadre(ObjProyectoEstandar proy){
		Long result = new Long("0");
		
		Connection conn = null;
		
		String query =" INSERT INTO man_proyecto_manager_principal "
						       +"    (codigo_proyectom "
						       +"    ,nombre_proyectom "
						       +"    ,estado_proyectom "
						       +"    ,id_cliente "
						       +"    ,fcreacion_proyectom "
						       +"    ,screacion_proyectom "
						       +"    ,fmod_proyectom "
						       +"    ,smod_proyectom "
						       +"    ,elimina_proyectom) "
						     +" VALUES "
						     +"      (?,?,?,?,?,?,?,?,?) ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, proy.getCodigo_proyectom());
			ps.setString(2, proy.getNombre_proyectop());
			ps.setInt(3, proy.getEstado_proyectop());
			ps.setInt(4, proy.getId_cliente());
			ps.setString(5, proy.getFcreacion_proyectom());
			ps.setInt(6, proy.getScreacion_proyectom());
			ps.setString(7, proy.getFcreacion_proyectom());
			ps.setInt(8, proy.getScreacion_proyectom());
			ps.setInt(9, proy.getElimina_proyectom());
			
			logger.info(query);
			
			int affectedRows = ps.executeUpdate();
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	              result = Long.valueOf(generatedKeys.getLong(1));
	              logger.info("Id Proyecto Padre es generado con KEY ID: "+result + "-- por usuario Id Key: "+ proy.getScreacion_proyectom());
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			
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
	public int createProyectoPadreInt(ObjProyectoEstandar proy){
		ResultSet generatedKeys = null;
	    int generatedKey = -1;
		
	    
		Connection conn = null;
		
		String query =" INSERT INTO man_proyecto_manager_principal "
						       +"    (codigo_proyectom "
						       +"    ,nombre_proyectom "
						       +"    ,estado_proyectom "
						       +"    ,id_cliente "
						       +"    ,fcreacion_proyectom "
						       +"    ,screacion_proyectom "
						       +"    ,fmod_proyectom "
						       +"    ,smod_proyectom "
						       +"    ,elimina_proyectom) "
						     +" VALUES "
						     +"      (?,?,?,?,?,?,?,?,?) ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, proy.getCodigo_proyectom());
			ps.setString(2, proy.getNombre_proyectop());
			ps.setInt(3, proy.getEstado_proyectop());
			ps.setInt(4, proy.getId_cliente());
			ps.setString(5, proy.getFcreacion_proyectom());
			ps.setInt(6, proy.getScreacion_proyectom());
			ps.setString(7, proy.getFcreacion_proyectom());
			ps.setInt(8, proy.getScreacion_proyectom());
			ps.setInt(9, proy.getElimina_proyectom());
			
			logger.info(query);
			
			 //ps.execute();
			ps.executeUpdate();
			generatedKeys = ps.getGeneratedKeys();

			if(generatedKeys.next() && generatedKeys != null){
			   return generatedKeys.getInt(1);
			} else {
				return 0;
			}
			
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
	public Long createOperacion(ObjProyectoEstandar proy){
		Long result = new Long("0");
		
		Connection conn = null;
		
		String query =" INSERT INTO man_proyecto_manager_medicion "
          +" (id_proyectom "
          +" ,cod_sam "
          +" ,cod_operacion " // ****
          +" ,cod_manager  "
          +" ,area_medicion  "
          +" ,nombre_operacion  "
          +" ,estado_medicion  "
          +" ,orden_medicion "
          +" ,cola_operacion "
          +" ,priori_operacion "
          +" ,fcrea_medicion "
          +" ,screa_medicion "
          +" ,factivacion_medicion  "
          +" ,sactivacion_medicion  "
          +" ,fmod_medicion  "
          +" ,smod_medicion  "
          +" ,elimina_medicion  "
          +" ,fingreso_puesta_marcha "
          +" ) "
          +" VALUES "
          +"   ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, proy.getId_proyectom());
			ps.setString(2, proy.getCod_sam());
			ps.setInt(3, this.getCodOperacion(proy.getId_proyectom()));
			ps.setString(4, proy.getCod_manager());
			ps.setInt(5, proy.getArea_medicion());
			ps.setString(6, proy.getNombre_operacion());
			ps.setInt(7, proy.getEstado_medicion());
			ps.setInt(8, proy.getOrden_medicion());
			ps.setInt(9, proy.getCola_operacion());
			ps.setInt(10, proy.getPriori_operacion());
			ps.setString(11, proy.getFcrea_operacion());
			ps.setInt(12, proy.getScrea_operacion());
			ps.setString(13,proy.getFactivacion_operacion());
			ps.setInt(14, proy.getSactivacion_operacion());
			ps.setString(15, proy.getFmod_operacion());
			ps.setInt(16, proy.getSmod_operacion());
			ps.setInt(17, proy.getElimina_operacion());
			ps.setString(18, proy.getFingreso_puesta_marcha_operacion());
			
			
			logger.info(query);
			
			int affectedRows = ps.executeUpdate();
			
			if (affectedRows == 0) {
			 throw new SQLException("Creating user failed, no rows affected.");
			}
			
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
			 if (generatedKeys.next()) {
			   result = Long.valueOf(generatedKeys.getLong(1));
			   logger.info("Id Operación es generado con KEY ID: "+result + "-- por usuario Id Key: "+ proy.getScrea_operacion());
			 }
			 else {
			     throw new SQLException("Creating user failed, no ID obtained.");
			 }
			}
			
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
	public Long createProyectoDetalle(ObjProyectoEstandar proy){
		Long result = new Long("0");
		
		Connection conn = null;
		String query =" INSERT INTO man_proyecto_manager "
          +" (id_operacion "
          +" ,id_clie_facturar "
          +" ,precio_venta "
          +" ,precio_venta_uf_manager "
          +" ,tipo_estudio "
          +" ,muestra_manager "
          +" ,canal_manager "
          +" ,res_us1_op "
          +" ,res_us2_op "
          +" ,res_us3_op "
          +" ,id_area_recopilacion "
          +" ,id_canal_recopilacion "
          +" ,submodo_cot "
          +" ,revision_pd_manager "
          +" ,supervision_pd_manager "
          +" ,codificacion_pd_manager "
          +" ,digitacion_pd_manager "
          +" ,procesamiento_pd_manager "
          +" ,porcentaje_supervicion_ra_manager "
          +" ,porcentaje_re_digitacion_ra_manager "
          +" ,libro_codigo_ra_manager "
          +" ,verbatims_ra_manager "
          +" ,pre_proceso_ra_manager "
          +" ,abiertas_codificar_od_manager "
          +" ,otros_codificar_od_manager "
          +" ,tmo_od_manager "
          +" ,id_industria "
          +" ,id_producto "
          +" ,id_tipo_producto1 "
          +" ,id_tipo_producto2 "
          +" ,id_digital "
          +" ,weekly_semana "
          +" ,weekly_ano)  "
          +" VALUES "
       	  +" (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, proy.getId_operacion());
			ps.setInt(2, proy.getId_cliente_factura());
			ps.setLong(3, proy.getPrecio_venta_manager());
			ps.setFloat(4, proy.getPrecio_venta_uf_manager());
			ps.setInt(5, proy.getId_tipo_estudio());
			ps.setInt(6, proy.getMuestra_manager());
			ps.setInt(7, proy.getCanal_manager());
			ps.setInt(8, proy.getId_user_coordinador_manager());
			ps.setInt(9, proy.getId_user_director_estudio_manager());
			ps.setInt(10, proy.getId_user_jefe_estudio_manager());
			ps.setInt(11, proy.getId_area_recopilacion());
			ps.setInt(12, proy.getId_canal_recopilacion());
			ps.setInt(13, proy.getId_sub_modo_recopilacion());
			ps.setInt(14, proy.getRevision_pd_manager());
			ps.setInt(15, proy.getSupervision_pd_manager());
			ps.setInt(16, proy.getCodificacion_pd_manager());
			ps.setInt(17, proy.getDigitacion_pd_manager());
			ps.setInt(18, proy.getProcesamiento_pd_manager());
			ps.setInt(19, proy.getPorcentaje_supervicion_ra_manager());
			ps.setInt(20, proy.getPorcentaje_re_digitacion_ra_manager());
			ps.setInt(21, proy.getLibro_codigo_ra_manager());
			ps.setInt(22, proy.getVerbatims_ra_manager());
			ps.setInt(23, proy.getPre_proceso_ra_manager());
			ps.setInt(24, proy.getAbiertas_codificar_od_manager());
			ps.setInt(25, proy.getOtros_codificar_od_manager());
			ps.setInt(26, proy.getTmo_od_manager());
			ps.setInt(27, proy.getId_industria());
			ps.setInt(28, proy.getId_producto());
			ps.setInt(29, proy.getId_tipo_producto1());
			ps.setInt(30, proy.getId_tipo_producto2());
			ps.setInt(31, proy.getId_digital());
			ps.setInt(32, proy.getWeekly_semana());
			ps.setInt(33, proy.getWeekly_ano());
			
			logger.info(query);
			
			int affectedRows = ps.executeUpdate();
			
			if (affectedRows == 0) {
			 throw new SQLException("Creating user failed, no rows affected.");
			}
			
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
			 if (generatedKeys.next()) {
			   result = Long.valueOf(generatedKeys.getLong(1));
			   logger.info("Id Operación es generado con KEY ID: "+result + "-- por usuario Id Key: "+ proy.getScrea_operacion());
			 }
			 else {
			     throw new SQLException("Creating user failed, no ID obtained.");
			 }
			}
			
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
	public Long createProyecto(ObjProyectoEstandar proy){
		Long result = new Long("0");
		/*
		 * proy.getProyecto_padre() = 1 -- No existe Proyecto Padre se debe crear
		 * proy.getProyecto_padre() = 0 -- Existe Proyecto Padre se debe Linkear Proyecto
		 * proy.getProyecto_padre() = 2 -- Creacion de Proyecto desde SAM
		 * */
		
		if(proy.getProyecto_padre() == 1){
			
			Long proyp = this.createProyectoPadre(proy);
			
			proy.setId_proyectom(proyp);
			
			Long oper = this.createOperacion(proy);
			
			proy.setId_operacion(oper);
			
			Long man = this.createProyectoDetalle(proy);
			
			
		}else if(proy.getProyecto_padre() == 2){
			
			//SAM
			// Proyecto padre contenedor SAM id = 1
			
			proy.setId_proyectom(new Long("1"));
			
			Long oper = this.createOperacion(proy);
			
			proy.setId_operacion(oper);
			
			Long man = this.createProyectoDetalle(proy);
			
		
		}else if(proy.getProyecto_padre() == 0){	
			
			//EXISTE PROYECTO
			
			Long oper = this.createOperacion(proy);
			
			proy.setId_operacion(oper);
			
			Long man = this.createProyectoDetalle(proy);
			
		}
		
		
		return result;
	}
	
	
	
	
	
}
