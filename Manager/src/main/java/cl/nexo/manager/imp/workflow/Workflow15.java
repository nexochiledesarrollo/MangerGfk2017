package cl.nexo.manager.imp.workflow;

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

import cl.nexo.manager.access.industria.AccessIndustria;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.workflow.AccessWorkflow15;
import cl.nexo.manager.obj.proyecto.ObjEstudio;


public class Workflow15 implements AccessWorkflow15 {
	private static final Logger logger = Logger.getLogger(Workflow15.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public ArrayList<ObjEstudio> getListEstudioByUser(int id, String lang){
		ArrayList<ObjEstudio> result = new ArrayList<ObjEstudio>();
		
		ArrayList<ObjEstudio> result1 = this.getListEstudioByUserResAsig(1,id,lang);
		ArrayList<ObjEstudio> result2 = this.getListEstudioByUserResAsig(2,id,lang);
		ArrayList<ObjEstudio> result3 = this.getListEstudioByUserResAsig(3,id,lang);

		for(ObjEstudio li: result1 ){
			result.add(li);
		}
		for(ObjEstudio li2: result2 ){
			result.add(li2);
		}
		for(ObjEstudio li3: result3 ){
			result.add(li3);
		}

		return result;
	}
	
	@Override
	public ArrayList<ObjEstudio> getListEstudioByUserKick(int id, String lang){
		ArrayList<ObjEstudio> result = new ArrayList<ObjEstudio>();
		
		ArrayList<ObjEstudio> result1 = this.getListEstudioByUserResAsigKick(1,id,lang);
		ArrayList<ObjEstudio> result2 = this.getListEstudioByUserResAsigKick(2,id,lang);
		ArrayList<ObjEstudio> result3 = this.getListEstudioByUserResAsigKick(3,id,lang);

		for(ObjEstudio li: result1 ){
			result.add(li);
		}
		for(ObjEstudio li2: result2 ){
			result.add(li2);
		}
		for(ObjEstudio li3: result3 ){
			result.add(li3);
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
				  
				  query = query + " AND o.estado_medicion = 10 AND o.cola_operacion = 18 AND o.activa_operacion = 0 ";
				  query = query + " ORDER BY o.id_operacion DESC ";
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
	public ArrayList<ObjEstudio> getListEstudioByUserResAsigKick(int idUser, int User, String lang){
		
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
				  
				  query = query + " AND o.estado_medicion = 10 AND o.cola_operacion = 20 AND o.activa_operacion = 0 ";
				  query = query + " ORDER BY o.id_operacion DESC ";
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
	
	
	
	
	
	
	

}
