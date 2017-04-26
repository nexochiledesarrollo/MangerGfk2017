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
		
		String query = "SELECT "
				+ " id_usuario,"
				+ " id_operacion,"
				+ " FORMAT(dia_asig,'dd/MM/yyyy', 'en-us') dia_asig ,"
				+ " sum(horas_asig) as total_horas  "  
			    + " FROM man_proyecto_manager_horas_recurso_detalle h,man_login_user u "
			    + " where u.id_user=h.id_usuario "
			    + " and "
			    + " dia_asig >= '" + desde + "' and  dia_asig <= '" + hasta +"'"
			    + " and u.id_division= " + div
			    + " and u.id_sdiv= " + sub_d
			    + " group by id_usuario,id_operacion,dia_asig "
			    + " order by id_usuario,dia_asig" ; 
			 
		 logger.info("sql " + query);
			  
			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug(query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					  ObjLoginUserHoras us = new ObjLoginUserHoras();
					  us.setHoras_ocupadas(rs.getInt("total_horas"));
					  us.setHoras_disponibles(9-rs.getInt("total_horas"));
					  us.setUsuario(usuario.getUserById(rs.getInt("id_usuario")));
					  us.setId_operacion(rs.getInt("id_operacion"));;
					  
					  try {
						  us.setFecha(rs.getString("dia_asig"));
					  } catch (SQLException e) {
						  logger.info(e);
					  }
					  
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



	
	
}
