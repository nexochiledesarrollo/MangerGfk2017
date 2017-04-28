package cl.nexo.manager.imp.asignaUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;

import cl.nexo.manager.access.apoderado.ApoderadoAccess;
import cl.nexo.manager.access.asignacionPersonal.AccessAsignacionPersonal;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.obj.agenda.ObjPersonaAgenda;
import cl.nexo.manager.obj.apoderado.ObjListApoderado;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjLoginUserHoras;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueString;


public class AsignaUsuario implements AccessAsignacionPersonal {
	
	private static final Logger logger = Logger.getLogger(AsignaUsuario.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ArrayList<ObjLoginUserHoras> getListUserHorasByFechas(String desde,String hasta,int div,int sub_d ) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess usuario = (LoginAccess) context.getBean("LoginAccess");

		Connection conn = null;
		ArrayList<ObjLoginUserHoras> usuarios = new ArrayList<ObjLoginUserHoras>();

		String query =  " Select  a.id_user, sum(a.total_horas) as total_horas,a.id_division from ( "
				+ " SELECT  u.id_user, h.id_operacion,sum(h.horas_asig) as total_horas,u.id_division" 
				+ " FROM man_login_user u"
				+ " left JOIN man_proyecto_manager_horas_recurso_detalle h  ON h.id_usuario=u.id_user "
				+ " where "
				+ " dia_asig >= '" + desde + "' and  dia_asig <= '" + hasta +"'"
				+ " and u.id_division= " + div
				+ " and u.id_sdiv= " + sub_d
				+ " group by u.id_user,h.id_operacion,u.id_division "
				+ " Union all "
				+ " SELECT  u.id_user, 0,0 as total_horas,u.id_division " 
				+ " FROM man_login_user u"
				+ " where "
				+ " u.id_division= " + div
				+ " and u.id_sdiv= " + sub_d
				+ ") as a  group by a.id_user,a.id_division " ;
		 
		 logger.info("sql " + query);
			  
			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug(query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					  ObjLoginUserHoras us = new ObjLoginUserHoras();
					  us.setHoras_ocupadas(rs.getInt("total_horas"));
					  us.setUsuario(usuario.getUserById(rs.getInt("id_user")));
					  us.setAsigna("<a href='JavaScript: showModalAsigna("+rs.getInt("id_user")+ "," + rs.getInt("total_horas")+ " );'><strong>Asignar</strong></a>");
					  usuarios.add(us);
				  }
				  
				  return usuarios;
				  
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
	public ArrayList<ObjLoginUserHoras> getAsignadosEstudio(int estudio) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess usuario = (LoginAccess) context.getBean("LoginAccess");

		Connection conn = null;
		ArrayList<ObjLoginUserHoras> usuarios = new ArrayList<ObjLoginUserHoras>();

		String query =  " select id_usuario,sum(horas_asig) as total "
				+ "       from man_proyecto_manager_horas_recurso_detalle  "
				+ "       where id_operacion= " +  estudio + " group by id_usuario" ;
		 
		 logger.info("sql " + query);
			  
			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.info(query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					  ObjLoginUserHoras us = new ObjLoginUserHoras();
					  us.setUsuario(usuario.getUserById(rs.getInt("id_usuario")));
					  us.setHoras_ocupadas(rs.getInt("total"));
					  usuarios.add(us);
				  }
				  logger.info("LISTA " + usuarios);
				  return usuarios;
				  
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
	public ArrayList<ObjLoginUserHoras> getAsignadosEstudioDias(int estudio,int user) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess usuario = (LoginAccess) context.getBean("LoginAccess");

		Connection conn = null;
		ArrayList<ObjLoginUserHoras> usuarios = new ArrayList<ObjLoginUserHoras>();

		String query =  " select id_usuario,horas_asig,dia_asig  "
				+ "       from man_proyecto_manager_horas_recurso_detalle  "
				+ "       where id_operacion= " +  estudio +  " and id_usuario=" + user   ;
		 
		 logger.info("sql " + query);
			  
			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.info(query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					  ObjLoginUserHoras us = new ObjLoginUserHoras();
					  us.setUsuario(usuario.getUserById(rs.getInt("id_usuario")));
					  us.setHoras_ocupadas(rs.getInt("horas_asig"));
					  us.setFecha(rs.getString("dia_asig"));
					  usuarios.add(us);
				  }
				  logger.info("LISTA " + usuarios);
				  return usuarios;
				  
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
	public int buscaHorasDipsUserDia(String dia,int user ) {

		Connection conn = null;
		int horas = 0;

		String query =  " SELECT id_usuario,sum(horas_asig) total_horas,dia_asig "
						+ " FROM man_proyecto_manager_horas_recurso_detalle h "
						+ " WHERE id_usuario= " + user 
						+ " and dia_asig='" + dia + "'  "
						+ " group by id_usuario,dia_asig ";
				
		 
		 logger.info("sql " + query);
			  
			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug(query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					 horas = rs.getInt("total_horas");
				  }
				  
				  return horas;
				  
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
	public int buscaHorasDipsUserDiaEstudio(String dia,int user,int estudio ) {

		Connection conn = null;
		int horas = 0;

		String query =  " SELECT id_usuario,horas_asig,dia_asig "
						+ " FROM man_proyecto_manager_horas_recurso_detalle h "
						+ " WHERE id_usuario= " + user 
						+ " and dia_asig='" + dia + "' and id_operacion= " + estudio;
					
				
		 
		 logger.info("sql " + query);
			  
			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug(query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					 horas = rs.getInt("horas_asig");
				  }
				  
				  return horas;
				  
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
	public ArrayList<ObjComboSelectValueString> getListRangoFechas(String desde,String hasta){
		Connection conn = null;
		ArrayList<ObjComboSelectValueString> combos = new ArrayList<ObjComboSelectValueString>();
		
		String query =  " EXEC [rango_fechas] '" + desde + "','"  + hasta +"'" ;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueString combo = new ObjComboSelectValueString();
				  combo.setId(rs.getString("FECHA"));
				  combo.setText(rs.getString("FECHA"));
				  logger.info(rs.getString("FECHA"));
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
	public void asignaHorasUserEstudio(int horas, String dia, int estudio,int user,int accion) {
		
		Connection conn = null;
		
		String query =" UPDATE man_proyecto_manager_horas_recurso_detalle  "
					  
					  +"   SET horas_asig = " + horas + " "
					  +"   WHERE id_usuario = " + user + " and "
					  +"   id_operacion= " + estudio + " and "
					  +"   dia_asig = '" + dia +"'"; 
					  
		
		if (accion==2){
			
			query =" INSERT INTO man_proyecto_manager_horas_recurso_detalle "
					+ "      (id_operacion,id_usuario,horas_asig,dia_asig) VALUES (  "
					  
						  +"   " + estudio + ", "
						  +"   " + user + ", "
						  +"   " + horas + ", "
						  +"   '" + dia + "')"; 
		
		}
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
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
