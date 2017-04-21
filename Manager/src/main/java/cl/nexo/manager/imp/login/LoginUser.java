package cl.nexo.manager.imp.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;


public class LoginUser implements LoginAccess {
	
	private static final Logger logger = Logger.getLogger(LoginUser.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	
	@Override
	public ArrayList<ObjComboSelect2ValueInt> getCombo(int tipo, int id,String place){
		/*
		 * tipo = 0 -- todo
		 * tipo = 1 -- Sdiv
		 * Tipo = 2 -- Cargo
		 * */
		
		
		
		Connection conn = null;
		ArrayList<ObjComboSelect2ValueInt> combos = new ArrayList<ObjComboSelect2ValueInt>();
		
		String query="SELECT u.id_user "
				+"	      ,u.nombre_user "
				+"	      ,u.app_user "
				+"	      ,u.id_cargo "
				+"	      ,c.nombre_cargo "
				+"	FROM man_login_user u "
				+"	INNER JOIN  man_cargo c ON  u.id_cargo = c.id_cargo "
				+ " WHERE u.id_user <> 3 ";
		if(tipo == 1){
			query= query+"	 "
					+ " c.id_sdiv = "+id+" ";
		}
		if(tipo == 2){
			query= query+" "
					+ " u.id_cargo = "+id+" ";
		}
		
		query= query+"	ORDER BY "
				+"	c.nombre_cargo, u.app_user, u.nombre_user";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ResultSet rs = ps.executeQuery();
			 
				 ObjComboSelect2ValueInt combo2 = new ObjComboSelect2ValueInt();
				  combo2.setId(0);
				  combo2.setText(place);
				  combos.add(combo2);
				 while(rs.next()) {
					  ObjComboSelect2ValueInt combo = new ObjComboSelect2ValueInt();
					  combo.setId(rs.getInt("id_user"));
					  combo.setText(rs.getString("app_user") + " "+ rs.getString("nombre_user") +" -- " + rs.getString("nombre_cargo"));
					  combos.add(combo);
				  }
			 
			  return combos;
			  
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
	public ObjLoginUser getUserById(int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		
		Connection conn = null;
		ObjLoginUser user = new ObjLoginUser();
		
		String query = "SELECT id_user "
				      +",run_user "
				      +",nombre_user "
				      +",app_user "
				      +",login_user "
				      +",mail_user "
				      +",pass_user "
				      +",id_perfil "
				      +",fnacimiento_user "
				      +",anexo_user "
				      +",id_cliente "
				      +",direccion_user "
				      +",id_departamento "
				      +",id_cargo "
				      +",id_area "
				      +",id_division "
				      +",fingreso_user "
				      +",id_comuna "
				      +",id_ciudad "
				      +",id_region "
				      +",id_pais "
				      +",id_puestoCotiz "
				      +",id_global "
				      +",id_tipocontrato "
				      +",id_tipopago "
				      +",id_jornada "
				      +",otp_user "
				      +",rol_user "
				      +",fcreacion_user "
				      +",screacion_user "
				      +",fmod_user "
				      +",smod_user "
				      +",felimna_user "
				      +",selimina_user "
				      +",elimina_user "
				      +",estado_user "
				      +",idsam_user "
				      +",id_zona "
				      +",exist_delivery "
				      +",keyactiveotp_user "
				      +",id_sdiv "
				      +",if_user "
				      +",lang_user "
				      +",numero_user "
				      +",dpto_user "
				      +",casilla_user "
				  +"FROM man_login_user  "
				  +"WHERE  "
				  +"id_user = "+id;
				
				 try {
					  conn = dataSource.getConnection();
					  PreparedStatement ps = conn.prepareStatement(query);
					  ResultSet rs = ps.executeQuery();
					  while (rs.next()) {
						  user.setId_user(rs.getInt("id_user"));
						  user.setRun(rs.getString("run_user"));
						  user.setNombre_user(rs.getString("nombre_user"));
						  user.setApp_user(rs.getString("app_user"));
						  user.setLogin_user(rs.getString("login_user"));
						  user.setMail_user(rs.getString("mail_user"));
						  user.setPass_user(rs.getString("pass_user"));
						  user.setId_perfil(rs.getInt("id_perfil"));
						  user.setStr_perfil(perfils.getStrPerfilById(rs.getInt("id_perfil")));
						  user.setNumero_user(rs.getString("numero_user"));
						  user.setDpto_user(rs.getString("dpto_user"));
						  user.setCasilla_user(rs.getString("casilla_user"));
						  if(rs.getDate("fnacimiento_user") != null){
							  user.setFnacimiento_user(format4.format(rs.getDate("fnacimiento_user")));
						  }
						  user.setAnexo_user(rs.getString("anexo_user"));
						  user.setId_cliente(rs.getInt("id_cliente"));
						  user.setDireccion_user(rs.getString("direccion_user"));
						  user.setId_departamento(rs.getInt("id_departamento"));
						  user.setId_cargo(rs.getInt("id_cargo"));
						  user.setId_area(rs.getInt("id_area"));
						  user.setId_division(rs.getInt("id_division"));
						  if(rs.getDate("fingreso_user") != null){
							  user.setFingreso_user(format4.format(rs.getDate("fingreso_user")));
						  }
						  user.setId_comuna(rs.getInt("id_comuna"));
						  user.setId_ciudad(rs.getInt("id_ciudad"));
						  user.setId_region(rs.getInt("id_region"));
						  user.setId_pais(rs.getInt("id_pais"));
						  user.setId_puesto_cotizacion(rs.getInt("id_puestoCotiz"));
						  user.setId_global(rs.getInt("id_global"));
						  user.setId_tipo_contrato(rs.getInt("id_tipocontrato"));
						  user.setId_tipo_pago(rs.getInt("id_tipopago"));
						  user.setId_jornada(rs.getInt("id_jornada"));
						  user.setOtp_user(rs.getInt("otp_user"));
						  user.setRol_user(rs.getString("rol_user"));
						  user.setFcreacion_user(format3.format(rs.getTime("fcreacion_user")));
						  user.setScreacion_user(rs.getInt("screacion_user"));
						  user.setStr_screacion_user(logins.getStrLoginById(rs.getInt("screacion_user")));
						  user.setFmod_user(format3.format(rs.getTime("fmod_user")));
						  user.setSmod_user(rs.getInt("smod_user"));
						  user.setStr_smod_user(logins.getStrLoginById(rs.getInt("smod_user")));
						  if(rs.getDate("felimna_user")!= null ){
							  user.setFelimina_user(format3.format(rs.getTime("felimna_user")));
							  user.setStr_selimina_user(logins.getStrLoginById(rs.getInt("selimina_user")));
						  }
						  user.setSelimina_user(rs.getInt("selimina_user"));
						  user.setElimina_user(rs.getInt("elimina_user"));
						  user.setEstado_user(rs.getInt("estado_user"));
						  user.setId_sam_user(rs.getInt("idsam_user"));
						  user.setId_zona(rs.getInt("id_zona"));
						  user.setExiste_delivery(rs.getString("exist_delivery"));
						  user.setKeyactiveotp_user(rs.getString("keyactiveotp_user"));
						  user.setId_sdiv(rs.getInt("id_sdiv"));
						  user.setIf_user(rs.getInt("if_user"));
						  user.setLang_user(rs.getString("lang_user"));
						  
					  }
					  
					  return user;
					  
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
	public ObjLoginUser getUserByLogin(String login){
		Connection conn = null;
		ObjLoginUser user = new ObjLoginUser();
		
		String query = "SELECT id_user "
				      +",run_user "
				      +",nombre_user "
				      +",app_user "
				      +",login_user "
				      +",mail_user "
				      +",pass_user "
				      +",id_perfil "
				      +",fnacimiento_user "
				      +",anexo_user "
				      +",id_cliente "
				      +",direccion_user "
				      +",id_departamento "
				      +",id_cargo "
				      +",id_area "
				      +",id_division "
				      +",fingreso_user "
				      +",id_comuna "
				      +",id_ciudad "
				      +",id_region "
				      +",id_pais "
				      +",id_puestoCotiz "
				      +",id_global "
				      +",id_tipocontrato "
				      +",id_tipopago "
				      +",id_jornada "
				      +",otp_user "
				      +",rol_user "
				      +",fcreacion_user "
				      +",screacion_user "
				      +",fmod_user "
				      +",smod_user "
				      +",felimna_user "
				      +",selimina_user "
				      +",elimina_user "
				      +",estado_user "
				      +",idsam_user "
				      +",id_zona "
				      +",exist_delivery "
				      +",keyactiveotp_user "
				      +",id_sdiv "
				      +",if_user "
				      +",lang_user "
				      +",numero_user "
				      +",dpto_user "
				      +",casilla_user "
				  +"FROM man_login_user  "
				  +"WHERE  "
				  +"login_user = '"+ login +"'";
				
				  try {
					  conn = dataSource.getConnection();
					  PreparedStatement ps = conn.prepareStatement(query);
					  ResultSet rs = ps.executeQuery();
					  while (rs.next()) {
						  user.setId_user(rs.getInt("id_user"));
						  user.setRun(rs.getString("run_user"));
						  user.setNombre_user(rs.getString("nombre_user"));
						  user.setApp_user(rs.getString("app_user"));
						  user.setLogin_user(rs.getString("login_user"));
						  user.setMail_user(rs.getString("mail_user"));
						  user.setPass_user(rs.getString("pass_user"));
						  user.setId_perfil(rs.getInt("id_perfil"));
						  user.setNumero_user(rs.getString("numero_user"));
						  user.setDpto_user(rs.getString("dpto_user"));
						  user.setCasilla_user(rs.getString("casilla_user"));
						  if(rs.getDate("fnacimiento_user") != null){
							  user.setFnacimiento_user(format4.format(rs.getDate("fnacimiento_user")));
						  }
						  user.setAnexo_user(rs.getString("anexo_user"));
						  user.setId_cliente(rs.getInt("id_cliente"));
						  user.setDireccion_user(rs.getString("direccion_user"));
						  user.setId_departamento(rs.getInt("id_departamento"));
						  user.setId_cargo(rs.getInt("id_cargo"));
						  user.setId_area(rs.getInt("id_area"));
						  user.setId_division(rs.getInt("id_division"));
						  if(rs.getDate("fingreso_user") != null){
							  user.setFingreso_user(format4.format(rs.getDate("fingreso_user")));
						  }
						  user.setId_comuna(rs.getInt("id_comuna"));
						  user.setId_ciudad(rs.getInt("id_ciudad"));
						  user.setId_region(rs.getInt("id_region"));
						  user.setId_pais(rs.getInt("id_pais"));
						  user.setId_puesto_cotizacion(rs.getInt("id_puestoCotiz"));
						  user.setId_global(rs.getInt("id_global"));
						  user.setId_tipo_contrato(rs.getInt("id_tipocontrato"));
						  user.setId_tipo_pago(rs.getInt("id_tipopago"));
						  user.setId_jornada(rs.getInt("id_jornada"));
						  user.setOtp_user(rs.getInt("otp_user"));
						  user.setRol_user(rs.getString("rol_user"));
						  user.setFcreacion_user(format3.format(rs.getTime("fcreacion_user")));
						  user.setScreacion_user(rs.getInt("screacion_user"));
						  user.setFmod_user(format3.format(rs.getTime("fmod_user")));
						  user.setSmod_user(rs.getInt("smod_user"));
						  if(rs.getDate("felimna_user")!= null ){
							  user.setFelimina_user(format3.format(rs.getTime("felimna_user")));
						  }
						  user.setSelimina_user(rs.getInt("selimina_user"));
						  user.setElimina_user(rs.getInt("elimina_user"));
						  user.setEstado_user(rs.getInt("estado_user"));
						  user.setId_sam_user(rs.getInt("idsam_user"));
						  user.setId_zona(rs.getInt("id_zona"));
						  user.setExiste_delivery(rs.getString("exist_delivery"));
						  user.setKeyactiveotp_user(rs.getString("keyactiveotp_user"));
						  user.setId_sdiv(rs.getInt("id_sdiv"));
						  user.setIf_user(rs.getInt("if_user"));
						  user.setLang_user(rs.getString("lang_user"));
					  }
					  
					  return user;
					  
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
	public ObjLoginUser getUserByMail(String mail){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		
		Connection conn = null;
		ObjLoginUser user = new ObjLoginUser();
		
		String query = "SELECT id_user "
				      +",run_user "
				      +",nombre_user "
				      +",app_user "
				      +",login_user "
				      +",mail_user "
				      +",pass_user "
				      +",id_perfil "
				      +",fnacimiento_user "
				      +",anexo_user "
				      +",id_cliente "
				      +",direccion_user "
				      +",id_departamento "
				      +",id_cargo "
				      +",id_area "
				      +",id_division "
				      +",fingreso_user "
				      +",id_comuna "
				      +",id_ciudad "
				      +",id_region "
				      +",id_pais "
				      +",id_puestoCotiz "
				      +",id_global "
				      +",id_tipocontrato "
				      +",id_tipopago "
				      +",id_jornada "
				      +",otp_user "
				      +",rol_user "
				      +",fcreacion_user "
				      +",screacion_user "
				      +",fmod_user "
				      +",smod_user "
				      +",felimna_user "
				      +",selimina_user "
				      +",elimina_user "
				      +",estado_user "
				      +",idsam_user "
				      +",id_zona "
				      +",exist_delivery "
				      +",keyactiveotp_user "
				      +",id_sdiv "
				      +",if_user "
				      +",lang_user "
				      +",numero_user "
				      +",dpto_user "
				      +",casilla_user "
				  +"FROM man_login_user  "
				  +"WHERE  "
				  +"mail_user = '"+ mail + "'";
				  //+" AND otp_user = 0";
				
				 try {
					  conn = dataSource.getConnection();
					  PreparedStatement ps = conn.prepareStatement(query);
					  logger.debug(query);
					  ResultSet rs = ps.executeQuery();
					  if (rs.next()) {
						  user.setId_user(rs.getInt("id_user"));
						  user.setRun(rs.getString("run_user"));
						  user.setNombre_user(rs.getString("nombre_user"));
						  user.setApp_user(rs.getString("app_user"));
						  user.setLogin_user(rs.getString("login_user"));
						  user.setMail_user(rs.getString("mail_user"));
						  user.setPass_user(rs.getString("pass_user"));
						  user.setId_perfil(rs.getInt("id_perfil"));
						  user.setStr_perfil(perfils.getStrPerfilById(rs.getInt("id_perfil")));
						  user.setNumero_user(rs.getString("numero_user"));
						  user.setDpto_user(rs.getString("dpto_user"));
						  user.setCasilla_user(rs.getString("casilla_user"));
						  if(rs.getDate("fnacimiento_user") != null){
							  user.setFnacimiento_user(format4.format(rs.getDate("fnacimiento_user")));
						  }
						  user.setAnexo_user(rs.getString("anexo_user"));
						  user.setId_cliente(rs.getInt("id_cliente"));
						  user.setDireccion_user(rs.getString("direccion_user"));
						  user.setId_departamento(rs.getInt("id_departamento"));
						  user.setId_cargo(rs.getInt("id_cargo"));
						  user.setId_area(rs.getInt("id_area"));
						  user.setId_division(rs.getInt("id_division"));
						  if(rs.getDate("fingreso_user") != null){
							  user.setFingreso_user(format4.format(rs.getDate("fingreso_user")));
						  }
						  user.setId_comuna(rs.getInt("id_comuna"));
						  user.setId_ciudad(rs.getInt("id_ciudad"));
						  user.setId_region(rs.getInt("id_region"));
						  user.setId_pais(rs.getInt("id_pais"));
						  user.setId_puesto_cotizacion(rs.getInt("id_puestoCotiz"));
						  user.setId_global(rs.getInt("id_global"));
						  user.setId_tipo_contrato(rs.getInt("id_tipocontrato"));
						  user.setId_tipo_pago(rs.getInt("id_tipopago"));
						  user.setId_jornada(rs.getInt("id_jornada"));
						  user.setOtp_user(rs.getInt("otp_user"));
						  user.setRol_user(rs.getString("rol_user"));
						  user.setFcreacion_user(format3.format(rs.getTime("fcreacion_user")));
						  user.setScreacion_user(rs.getInt("screacion_user"));
						  user.setStr_screacion_user(logins.getStrLoginById(rs.getInt("screacion_user")));
						  user.setFmod_user(format3.format(rs.getTime("fmod_user")));
						  user.setSmod_user(rs.getInt("smod_user"));
						  user.setStr_smod_user(logins.getStrLoginById(rs.getInt("smod_user")));
						  if(rs.getDate("felimna_user")!= null ){
							  user.setFelimina_user(format3.format(rs.getTime("felimna_user")));
							  user.setStr_selimina_user(logins.getStrLoginById(rs.getInt("selimina_user")));
						  }
						  user.setSelimina_user(rs.getInt("selimina_user"));
						  user.setElimina_user(rs.getInt("elimina_user"));
						  user.setEstado_user(rs.getInt("estado_user"));
						  user.setId_sam_user(rs.getInt("idsam_user"));
						  user.setId_zona(rs.getInt("id_zona"));
						  user.setExiste_delivery(rs.getString("exist_delivery"));
						  user.setKeyactiveotp_user(rs.getString("keyactiveotp_user"));
						  user.setId_sdiv(rs.getInt("id_sdiv"));
						  user.setIf_user(rs.getInt("if_user"));
						  user.setLang_user(rs.getString("lang_user"));
						  
					  }else{
						  user.setId_user(0);
					  }
					  
					  return user;
					  
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
	public ObjLoginUser getUserByActiveCode(String code){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		
		Connection conn = null;
		ObjLoginUser user = new ObjLoginUser();
		
		String query = "SELECT id_user "
				      +",run_user "
				      +",nombre_user "
				      +",app_user "
				      +",login_user "
				      +",mail_user "
				      +",pass_user "
				      +",id_perfil "
				      +",fnacimiento_user "
				      +",anexo_user "
				      +",id_cliente "
				      +",direccion_user "
				      +",id_departamento "
				      +",id_cargo "
				      +",id_area "
				      +",id_division "
				      +",fingreso_user "
				      +",id_comuna "
				      +",id_ciudad "
				      +",id_region "
				      +",id_pais "
				      +",id_puestoCotiz "
				      +",id_global "
				      +",id_tipocontrato "
				      +",id_tipopago "
				      +",id_jornada "
				      +",otp_user "
				      +",rol_user "
				      +",fcreacion_user "
				      +",screacion_user "
				      +",fmod_user "
				      +",smod_user "
				      +",felimna_user "
				      +",selimina_user "
				      +",elimina_user "
				      +",estado_user "
				      +",idsam_user "
				      +",id_zona "
				      +",exist_delivery "
				      +",keyactiveotp_user "
				      +",id_sdiv "
				      +",if_user "
				      +",lang_user "
				      +",numero_user "
				      +",dpto_user "
				      +",casilla_user "
				  +"FROM man_login_user  "
				  +"WHERE  "
				  +"keyactiveotp_user = '"+ code + "'"
				  +" AND otp_user = 0";
				
				 try {
					  conn = dataSource.getConnection();
					  PreparedStatement ps = conn.prepareStatement(query);
					  logger.debug(query);
					  ResultSet rs = ps.executeQuery();
					  if (rs.next()) {
						  user.setId_user(rs.getInt("id_user"));
						  user.setRun(rs.getString("run_user"));
						  user.setNombre_user(rs.getString("nombre_user"));
						  user.setApp_user(rs.getString("app_user"));
						  user.setLogin_user(rs.getString("login_user"));
						  user.setMail_user(rs.getString("mail_user"));
						  user.setPass_user(rs.getString("pass_user"));
						  user.setId_perfil(rs.getInt("id_perfil"));
						  user.setStr_perfil(perfils.getStrPerfilById(rs.getInt("id_perfil")));
						  user.setNumero_user(rs.getString("numero_user"));
						  user.setDpto_user(rs.getString("dpto_user"));
						  user.setCasilla_user(rs.getString("casilla_user"));
						  if(rs.getDate("fnacimiento_user") != null){
							  user.setFnacimiento_user(format4.format(rs.getDate("fnacimiento_user")));
						  }
						  user.setAnexo_user(rs.getString("anexo_user"));
						  user.setId_cliente(rs.getInt("id_cliente"));
						  user.setDireccion_user(rs.getString("direccion_user"));
						  user.setId_departamento(rs.getInt("id_departamento"));
						  user.setId_cargo(rs.getInt("id_cargo"));
						  user.setId_area(rs.getInt("id_area"));
						  user.setId_division(rs.getInt("id_division"));
						  if(rs.getDate("fingreso_user") != null){
							  user.setFingreso_user(format4.format(rs.getDate("fingreso_user")));
						  }
						  user.setId_comuna(rs.getInt("id_comuna"));
						  user.setId_ciudad(rs.getInt("id_ciudad"));
						  user.setId_region(rs.getInt("id_region"));
						  user.setId_pais(rs.getInt("id_pais"));
						  user.setId_puesto_cotizacion(rs.getInt("id_puestoCotiz"));
						  user.setId_global(rs.getInt("id_global"));
						  user.setId_tipo_contrato(rs.getInt("id_tipocontrato"));
						  user.setId_tipo_pago(rs.getInt("id_tipopago"));
						  user.setId_jornada(rs.getInt("id_jornada"));
						  user.setOtp_user(rs.getInt("otp_user"));
						  user.setRol_user(rs.getString("rol_user"));
						  user.setFcreacion_user(format3.format(rs.getTime("fcreacion_user")));
						  user.setScreacion_user(rs.getInt("screacion_user"));
						  user.setStr_screacion_user(logins.getStrLoginById(rs.getInt("screacion_user")));
						  user.setFmod_user(format3.format(rs.getTime("fmod_user")));
						  user.setSmod_user(rs.getInt("smod_user"));
						  user.setStr_smod_user(logins.getStrLoginById(rs.getInt("smod_user")));
						  if(rs.getDate("felimna_user")!= null ){
							  user.setFelimina_user(format3.format(rs.getTime("felimna_user")));
							  user.setStr_selimina_user(logins.getStrLoginById(rs.getInt("selimina_user")));
						  }
						  user.setSelimina_user(rs.getInt("selimina_user"));
						  user.setElimina_user(rs.getInt("elimina_user"));
						  user.setEstado_user(rs.getInt("estado_user"));
						  user.setId_sam_user(rs.getInt("idsam_user"));
						  user.setId_zona(rs.getInt("id_zona"));
						  user.setExiste_delivery(rs.getString("exist_delivery"));
						  user.setKeyactiveotp_user(rs.getString("keyactiveotp_user"));
						  user.setId_sdiv(rs.getInt("id_sdiv"));
						  user.setIf_user(rs.getInt("if_user"));
						  user.setLang_user(rs.getString("lang_user"));
						  
					  }else{
						  user.setId_user(0);
					  }
					  
					  return user;
					  
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
	public ArrayList<ObjLoginUser> getListUsers(int cliente){
		
		Connection conn = null;
		ArrayList<ObjLoginUser> users = new ArrayList<ObjLoginUser>();
		
		String query = "SELECT id_user "
			      +",run_user "
			      +",nombre_user "
			      +",app_user "
			      +",login_user "
			      +",mail_user "
			      +",pass_user "
			      +",id_perfil "
			      +",fnacimiento_user "
			      +",anexo_user "
			      +",id_cliente "
			      +",direccion_user "
			      +",id_departamento "
			      +",id_cargo "
			      +",id_area "
			      +",id_division "
			      +",fingreso_user "
			      +",id_comuna "
			      +",id_ciudad "
			      +",id_region "
			      +",id_pais "
			      +",id_puestoCotiz "
			      +",id_global "
			      +",id_tipocontrato "
			      +",id_tipopago "
			      +",id_jornada "
			      +",otp_user "
			      +",rol_user "
			      +",fcreacion_user "
			      +",screacion_user "
			      +",fmod_user "
			      +",smod_user "
			      +",felimna_user "
			      +",selimina_user "
			      +",elimina_user "
			      +",estado_user "
			      +",idsam_user "
			      +",id_zona "
			      +",exist_delivery "
			      +",keyactiveotp_user "
			      +",id_sdiv "
			      +",if_user "
			      +",lang_user "
			      +",numero_user "
			      +",dpto_user "
			      +",casilla_user "
			  +"FROM man_login_user  "
			  +" WHERE elimina_user = 0";
			  if(cliente != 0){
				  query = query +" AND  "
				  +"id_cliente = "+cliente;
			  }
			  
			  
			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					  ObjLoginUser user = new ObjLoginUser();
					  user.setId_user(rs.getInt("id_user"));
					  user.setRun(rs.getString("run_user"));
					  user.setNombre_user(rs.getString("nombre_user"));
					  user.setApp_user(rs.getString("app_user"));
					  user.setLogin_user(rs.getString("login_user"));
					  user.setMail_user(rs.getString("mail_user"));
					  user.setPass_user(rs.getString("pass_user"));
					  user.setId_perfil(rs.getInt("id_perfil"));
					  user.setFnacimiento_user(format4.format(rs.getDate("fnacimiento_user")));
					  user.setAnexo_user(rs.getString("anexo_user"));
					  user.setId_cliente(rs.getInt("id_cliente"));
					  user.setDireccion_user(rs.getString("direccion_user"));
					  user.setId_departamento(rs.getInt("id_departamento"));
					  user.setId_cargo(rs.getInt("id_cargo"));
					  user.setId_area(rs.getInt("id_area"));
					  user.setId_division(rs.getInt("id_division"));
					  user.setNumero_user(rs.getString("numero_user"));
					  user.setDpto_user(rs.getString("dpto_user"));
					  user.setCasilla_user(rs.getString("casilla_user"));
					  
					  if(rs.getDate("fingreso_user") != null){
						  user.setFingreso_user(format4.format(rs.getDate("fingreso_user")));
					  }
					  user.setId_comuna(rs.getInt("id_comuna"));
					  user.setId_ciudad(rs.getInt("id_ciudad"));
					  user.setId_region(rs.getInt("id_region"));
					  user.setId_pais(rs.getInt("id_pais"));
					  user.setId_puesto_cotizacion(rs.getInt("id_puestoCotiz"));
					  user.setId_global(rs.getInt("id_global"));
					  user.setId_tipo_contrato(rs.getInt("id_tipocontrato"));
					  user.setId_tipo_pago(rs.getInt("id_tipopago"));
					  user.setId_jornada(rs.getInt("id_jornada"));
					  user.setOtp_user(rs.getInt("otp_user"));
					  user.setRol_user(rs.getString("rol_user"));
					  user.setFcreacion_user(format3.format(rs.getTime("fcreacion_user")));
					  user.setScreacion_user(rs.getInt("screacion_user"));
					  user.setFmod_user(format3.format(rs.getTime("fmod_user")));
					  user.setSmod_user(rs.getInt("smod_user"));
					  if(rs.getDate("felimna_user")!= null ){
						  user.setFelimina_user(format3.format(rs.getTime("felimna_user")));
					  }
					  user.setSelimina_user(rs.getInt("selimina_user"));
					  user.setElimina_user(rs.getInt("elimina_user"));
					  user.setEstado_user(rs.getInt("estado_user"));
					  user.setId_sam_user(rs.getInt("idsam_user"));
					  user.setId_zona(rs.getInt("id_zona"));
					  user.setExiste_delivery(rs.getString("exist_delivery"));
					  user.setKeyactiveotp_user(rs.getString("keyactiveotp_user"));
					  user.setId_sdiv(rs.getInt("id_sdiv"));
					  user.setIf_user(rs.getInt("if_user"));
					  user.setLang_user(rs.getString("lang_user"));
					  users.add(user);
				  }
				  
				  return users;
				  
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
	public ArrayList<ObjListManUser> getListUserByCliente(int cliente){
		
		Connection conn = null;
		ArrayList<ObjListManUser> users = new ArrayList<ObjListManUser>();
		
		String query = "SELECT "  
			     +"  l.id_user "
				 +" ,l.run_user "
			     +" ,l.nombre_user " 
			     +" ,l.app_user  "
			     +" ,l.login_user "
			     +" ,l.mail_user "
			     +" ,p.nombre_perfil " 
			     +" ,c.nombre_cargo "
			     +" ,a.nombre_area "
			     +" ,d.nombre_div "
			     +" ,l.otp_user "
			     +" ,l.estado_user "
			  +" FROM man_login_user l "  
			  +" LEFT JOIN man_perfil_login p ON l.id_perfil = p.id_perfil "  
			  +" LEFT JOIN man_cargo c ON l.id_cargo = c.id_cargo "
			  +" LEFT JOIN man_area a ON l.id_area = a.id_area "
			  +" LEFT JOIN man_divisiones d ON l.id_division = d.id_div  "
			  +" WHERE l.elimina_user = 0"
			  +" AND l.id_user NOT IN(3)  ";
			  if(cliente != 0){
				  query = query +" AND  "
				  +" l.id_cliente = "+cliente;
			  }
			  
			  
			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug(query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					  ObjListManUser user = new ObjListManUser();
					  user.setRun("<a href='JavaScript: handleDetalleUsuario("+rs.getInt("id_user")+");'><strong>"+rs.getString("run_user")+"</strong></a>");
					  user.setNombre(rs.getString("nombre_user"));
					  user.setApellido(rs.getString("app_user"));
					  user.setLoginUser("<a href='JavaScript: handleDetalleUsuario("+rs.getInt("id_user")+");'><strong>"+rs.getString("login_user")+"</strong></a>");
					  user.setEmail(rs.getString("mail_user"));
					  user.setPerfil(rs.getString("nombre_perfil"));
					  user.setCargo(rs.getString("nombre_cargo"));
					  user.setArea(rs.getString("nombre_area"));
					  user.setDivision(rs.getString("nombre_div"));
					  int otp = rs.getInt("otp_user");
					  if(otp == 1){
						  user.setOtp("ACTIVADO"); 
					  }else{
						  user.setOtp("PENDIENTE");
					  }
					  int estado = rs.getInt("estado_user");
					  if(estado == 1){
						  user.setEstado("ACTIVO");
					  }else{
						  user.setEstado("DESACTIVADO");
					  }
					  
					  
					  users.add(user);
				  }
				  
				  return users;
				  
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
	public int getIdMaxInsertId(int idSesion){
		int id = 0;
		Connection conn = null;
		
		String query = " SELECT MAX(id_user) as id "
				  +"	 FROM man_login_user "
				  +"	 WHERE "
				  +"	 screacion_user = "+ idSesion;
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {  
				  id = rs.getInt("id");
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
	public int getCountUsers(int idCliente){
		int id = 0;
		Connection conn = null;
		
		String query = " SELECT count(id_user) as id "
				  +"	 FROM man_login_user ";
				  if(idCliente != 0){
					  query = query + "	 WHERE "
					  +" id_cliente = " + idCliente;
				  }
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  id = rs.getInt("id");
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
	public String getStrLoginById(int idUser){
		String id = "";
		Connection conn = null;
		
		String query = " SELECT login_user "
				  +"	 FROM man_login_user "
				  +"	 WHERE "
				  +" id_user = " + idUser;
				  
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  id = rs.getString("login_user");
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
	public String getStrNameLoginById(int idUser){
		String id = "";
		Connection conn = null;
		
		String query = " SELECT nombre_user , app_user  "
				  +"	 FROM man_login_user "
				  +"	 WHERE "
				  +" id_user = " + idUser;
				  
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  id = rs.getString("nombre_user") + " "+ rs.getString("app_user");
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
	public int getExistLoginById(String login){
		int result = 0;
		Connection conn = null;
		
		String query = " SELECT login_user "
				  +"	 FROM man_login_user "
				  +"	 WHERE "
				  +" login_user = '" + login+"'";
				  
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {

				result = 1;
				
			}else{
				
				result = 0;
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
	public int getExistLoginByRun(String run){
		int result = 0;
		Connection conn = null;
		
		String query = " SELECT login_user "
				  +"	 FROM man_login_user "
				  +"	 WHERE "
				  +" run_user = '" + run+"'";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {

				result = 1;
				
			}else{
				
				result = 0;
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
	public int getExistLoginByMail(String mail){
		int result = 0;
		Connection conn = null;
		
		String query = " SELECT login_user "
				  +"	 FROM man_login_user "
				  +"	 WHERE "
				  +" mail_user = '" + mail+"'";
				  
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {

				result = 1;
				
			}else{
				
				result = 0;
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
	public int setUserManager(ObjLoginUser user){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    	AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
    	AccessServerMail serverMail = (AccessServerMail) context.getBean("AccessServerMail");
    	AccessPlantillasLogin bodyTemplate = (AccessPlantillasLogin) context.getBean("AccessPlantillasLogin");
    	AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
    	
    	
    	String pass = genPass.getPassword();
    	Connection conn = null;
    	
    	String insFechaNac = user.getFnacimiento_user();
    	
    	String query = "INSERT INTO man_login_user "
    				+"	(run_user "
			        +"	 ,nombre_user "
			        +"	 ,app_user "
			        +"	 ,login_user "
			        +"   ,mail_user "
			        +"   ,pass_user "
			        +"   ,id_perfil ";
    	
    			if(!insFechaNac.equals("")){
    				query = query  +"   ,fnacimiento_user ";
    			}	        
			        
    			query = query +"   ,anexo_user "
			        +"   ,id_cliente "
			        +"   ,direccion_user "
			        +"   ,numero_user "
			        +"   ,dpto_user "
			        +"   ,casilla_user "
			        +"   ,id_departamento "
			        +"   ,id_cargo "
			        +"   ,id_area "
			        +"   ,id_division "
			        +"   ,id_sdiv ";
			        //+"   ,fingreso_user "
			        if(user.getId_comuna() != 0){
	    				query = query  +"   ,id_comuna ";
	    			}
			        if(user.getId_ciudad() != 0){
						query = query+"   ,id_ciudad ";
	    			}
			        if(user.getId_region() != 0){
			        	query = query+"   ,id_region ";
	    			}
			        if(user.getId_pais() != 0){
			        	query = query+"   ,id_pais ";
	    			}
			        
			        query = query+"   ,id_puestoCotiz "
			        +"   ,id_global "
			        +"   ,id_tipocontrato "
			        +"   ,id_tipopago "
			        +"   ,id_jornada "
			        +"   ,otp_user "
			        +"   ,rol_user "
			        +"   ,fcreacion_user "
			        +"   ,screacion_user "
			        +"   ,fmod_user "
			        +"   ,smod_user "
			        +"   ,elimina_user "
			        +"   ,estado_user "
			        +"   ,idsam_user "
			        +"   ,id_zona "
			        +"   ,exist_delivery "
			        +"   ,keyactiveotp_user "
			        +"   ,if_user "
			        +"   ,lang_user) "
			        +" VALUES "
			        +"   ('"+user.getRun()+"' "
			        +"   ,'"+user.getNombre_user()+"' "
			        +"   ,'"+user.getApp_user()+"' "
			        +"   ,'"+user.getLogin_user()+"' "
			        +"   ,'"+user.getMail_user()+"' "
			        +"   ,'"+genPass.getEncriptaPass(pass)+"' "
			        +"   ,"+user.getId_perfil()+" ";
			        
    			if(!insFechaNac.equals("")){    
    				query  = query +"   ,'"+user.getFnacimiento_user()+"'  ";
    			}    
    				query = query  +"   ,'"+user.getAnexo_user()+"' "
			        +"   ,"+user.getId_cliente()+" "
			        +"   ,'"+user.getDireccion_user()+"' "
			        +"   ,'"+user.getNumero_user()+"' "
			        +"   ,'"+user.getDpto_user()+"' "
			        +"   ,'"+user.getCasilla_user()+"' "
			        +"   ,"+user.getId_departamento()+" "
			        +"   ,"+user.getId_cargo()+" "
			        +"   ,"+user.getId_area()+" "
			        +"   ,"+user.getId_division()+" "
			        +"   ,"+user.getId_sdiv()+" ";
			        //+"   ,'"+user.getFingreso_user()+"' "
			        if(user.getId_comuna() != 0){
	    				query = query  +"   ,"+user.getId_comuna()+" ";
	    			}
    				if(user.getId_ciudad() != 0){
	    				query = query  +"   ,"+user.getId_ciudad()+" ";
	    			}
    				if(user.getId_region() != 0){
	    				query = query  +"   ,"+user.getId_region()+" ";
	    			}
    				if(user.getId_pais() != 0){
	    				query = query  +"   ,"+user.getId_pais()+" ";
	    			}
			        
    				query = query+"   ,"+user.getId_puesto_cotizacion()+" "
			        +"   ,"+user.getId_global()+" "
			        +"   ,"+user.getId_tipo_contrato()+ " "
			        +"   ,"+user.getId_tipo_pago()+" "
			        +"   ,"+user.getId_jornada()+" "
			        +"   ,"+user.getOtp_user()+" "
			        +"   ,'"+user.getRol_user()+"' "
			        +"   ,'"+user.getFcreacion_user()+"' "
			        +"   ,"+user.getScreacion_user()+" "
			        +"   ,'"+user.getFmod_user()+"' "
			        +"   ,"+user.getSmod_user()+" "
			        +"   ,"+user.getElimina_user()+" "
			        +"   ,"+user.getEstado_user()+" "
			        +"   ,"+user.getId_sam_user()+" "
			        +"   ,"+user.getId_zona()+""
    				+"   ,'"+user.getExiste_delivery()+"'"
    				+"   ,'"+user.getKeyactiveotp_user()+"' "
    				+"   ,'"+user.getIf_user()+"'"
    				+"   ,'"+user.getLang_user()+"')";
	    	try {
				conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				logger.info(query);
				ps.executeUpdate();
				return 1;
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
	public int updatePasswordLoginBySistem(int idUser){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
		String pass = genPass.getPassword();
		
		/* Adutoria 
		 * 
		 */
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
		LoginAccess login = (LoginAccess) context.getBean("LoginAccess");
		String user = authentication.getName();
		ObjLoginUser sesion = login.getUserByLogin(user);
		
		String fechaAhora = format3.format(new Date());
		Connection conn = null;
		
		String query="UPDATE man_login_user "
					 +"  SET " 
					 +"      pass_user = '"+pass+"' "
					 +"     ,otp_user = 0 "
					 +"     ,fmod_user = '"+fechaAhora+"' "
					 +"     ,smod_user = "+sesion.getId_user()+" "
					 +" WHERE id_user = "+idUser+"";
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			return 1;
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
	public int updateLogin(ObjLoginUser user){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
		String pass = genPass.getPassword();
		
		Connection conn = null;
		
		String query =" UPDATE man_login_user "
					  +" SET run_user = '"+user.getRun()+"' "
					  +"    ,nombre_user = '"+user.getNombre_user()+"' "
					  +"    ,app_user = '"+user.getApp_user()+"' "
					  +"    ,login_user = '"+user.getLogin_user()+"' "
					  +"    ,mail_user = '"+user.getMail_user()+"' "
					  +"    ,id_perfil = "+user.getId_perfil()+" ";
				if(user.getFnacimiento_user()!= null){	  
					query = query  +"    ,fnacimiento_user = '"+user.getFnacimiento_user()+"' ";
				}	  
		query = query +"    ,anexo_user = '"+user.getAnexo_user()+"' "
					  +"    ,id_cliente = "+user.getId_cliente()+" "
					  +"    ,direccion_user = '"+user.getDireccion_user()+"' "
					  +"    ,numero_user = '"+user.getNumero_user()+"' "
					  +"    ,dpto_user = '"+user.getDpto_user()+"' "
					  +"    ,casilla_user = '"+user.getCasilla_user()+"' "
					  +"    ,id_departamento = "+user.getId_departamento()+" "
					  +"    ,id_cargo = "+user.getId_cargo()+" "
					  +"    ,id_area = "+user.getId_area()+" "
		 			  +"    ,id_division = "+user.getId_division()+" "
		 			  +"    ,id_sdiv = "+user.getId_sdiv()+" ";
				
				if(user.getFingreso_user()!= null){
					query = query +"    ,fingreso_user = '"+user.getFingreso_user()+"' ";
				}	  
					  
	   query = query  +"    ,id_ciudad = '"+user.getId_ciudad()+"' "
					  +"    ,id_region = '"+user.getId_region()+"' "
					  +"    ,id_pais = '"+user.getId_pais()+"' "
					  +"    ,id_puestoCotiz = "+user.getId_puesto_cotizacion()+" "
					  +"    ,id_global = "+user.getId_global()+" "
					  +"    ,id_tipocontrato = "+user.getId_tipo_contrato()+" "
					  +"    ,id_tipopago = "+user.getId_tipo_pago()+" "
					  +"    ,id_jornada = "+user.getId_jornada()+" "
					  +"    ,rol_user = '"+user.getRol_user()+"' "
					  +"    ,fmod_user = '"+user.getFmod_user()+"' "
					  +"    ,smod_user = "+user.getSmod_user()+" "
					  +"    ,estado_user = "+user.getEstado_user()+" "
					  +"    ,idsam_user = "+user.getId_sam_user()+" "
					  +"    ,id_zona = "+user.getId_zona()+" "
					  +"    ,exist_delivery = '"+user.getExiste_delivery()+"' "
					  +"    ,if_user = '"+user.getIf_user()+"' "
					  +"    ,lang_user = '"+user.getLang_user()+"' "
					 +" WHERE id_user = "+user.getId_user()+"";
	
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ps.executeUpdate();
			return 1;
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
	public int updatePasswordByUser(ObjLoginUser user){
		
		Connection conn = null;
		
		String query = " UPDATE man_login_user "
				  +"     SET "
				  //+ "	pass_user = '"+user.getPass_user()+"' "
				  //+" 	,otp_user = 0 "
				  +"    ,fmod_user = '"+user.getFmod_user()+"' "
				  +"    ,smod_user = "+user.getSmod_user()+" "
				  +"    ,keyactiveotp_user = '"+user.getKeyactiveotp_user()+"'"
				  +" 	 WHERE "
				  +" 	 id_user = "+ user.getId_user();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			return 1;
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
	public int activePasswordByUser(ObjLoginUser user){
		
		Connection conn = null;
		
		String query = " UPDATE man_login_user "
				  +"     SET pass_user = '"+user.getPass_user()+"' "
				  +" 	,otp_user = 1 "
				  +"    ,fmod_user = '"+user.getFmod_user()+"' "
				  +"    ,smod_user = "+user.getSmod_user()+" "
				  +"    ,keyactiveotp_user = '"+user.getKeyactiveotp_user()+"'"
				  +" 	 WHERE "
				  +" 	 id_user = "+ user.getId_user();
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			return 1;
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
	public int deleteUser(int idUser){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		/* Adutoria 
		 * 
		 */
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
		LoginAccess login = (LoginAccess) context.getBean("LoginAccess");
		String userSis = authentication.getName();
		ObjLoginUser sesion = login.getUserByLogin(userSis);
		
		String fechaAhora = format3.format(new Date());
		Connection conn = null;
		
		String query = " UPDATE man_login_user "
				  +"   SET "
				  +" 	      estado_user = 0 "
				  +" 	      ,felimna_user = '"+fechaAhora+"' "
				  +" 	      ,selimina_user = "+sesion.getId_user()+" "
				  +" 	      ,elimina_user = 1 "
				  +" 	 WHERE "
				  +" 	 id_user = "+idUser ; 
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			return 1;
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
