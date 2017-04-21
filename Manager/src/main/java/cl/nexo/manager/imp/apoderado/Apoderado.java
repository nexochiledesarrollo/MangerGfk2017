package cl.nexo.manager.imp.apoderado;

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

import cl.nexo.manager.access.apoderado.ApoderadoAccess;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.obj.apoderado.ObjListApoderado;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;


public class Apoderado implements ApoderadoAccess {
	
	private static final Logger logger = Logger.getLogger(Apoderado.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");

	
	
	@Override
	public ArrayList<ObjListApoderado> getListApoderados() {

		Connection conn = null;
		ArrayList<ObjListApoderado> apoderados = new ArrayList<ObjListApoderado>();
		
		String query = "SELECT "  
			     +"  tb.cedula "
				 +" ,tb.nombres "
			     +" ,tb.apellidos " 
			     +" ,tb.cargo  "
			     +" ,tb.email "
			     +" FROM man_apoderado1 tb where estatus=0 " ; 
			 
			  
			  
			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug(query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					  ObjListApoderado apoderado = new ObjListApoderado();
					  apoderado.setCedula("<a href='JavaScript: handleDetalleApoderado("+rs.getString("cedula")+");'><strong>"+rs.getString("cedula")+"</strong></a>");
					  //user.setNombre(rs.getString("nombre_user"));
					  //user.setApellido(rs.getString("app_user"));
					  //user.setLoginUser("<a href='JavaScript: handleDetalleUsuario("+rs.getInt("id_user")+");'><strong>"+rs.getString("login_user")+"</strong></a>");
					  //user.setEmail(rs.getString("mail_user"));
					  //user.setPerfil(rs.getString("nombre_perfil"));
					  //user.setCargo(rs.getString("nombre_cargo"));
					  //user.setArea(rs.getString("nombre_area"));
					  //user.setDivision(rs.getString("nombre_div"));
					  
					  
					  //apoderado.setCedula(rs.getString("cedula"));
					  apoderado.setNombres(rs.getString("nombres"));
					  apoderado.setApellido(rs.getString("apellidos"));
					  apoderado.setCargo(rs.getString("cargo"));
					  apoderado.setEmail(rs.getString("email"));
					
					  
					  apoderados.add(apoderado);
				  }
				  
				  return apoderados;
				  
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
	public ObjListApoderado getApoderadoById(int id) {
		
		Connection conn = null;
	
		String query = "SELECT "  
			     +"  tb.cedula "
				 +" ,tb.nombres "
			     +" ,tb.apellidos " 
			     +" ,tb.cargo  "
			     +" ,tb.email "
			     +" FROM man_apoderado1 tb where cedula ='"+ id + "'";
		
		logger.info("SQL " + query);
		
		  try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			  logger.info("APODERADO1 " );
				  ObjListApoderado apoderado = new ObjListApoderado();
				  logger.info("APODERADO2 " );
				  while (rs.next()) {  
				  apoderado.setCedula(rs.getString("cedula"));
				  logger.info("APODERADO3 " );
				  apoderado.setNombres(rs.getString("nombres"));
				  logger.info("APODERADO4 " );
				  apoderado.setApellido(rs.getString("apellidos"));
				  logger.info("APODERADO5 " );
				  apoderado.setCargo(rs.getString("cargo"));
				  logger.info("APODERADO6 " );
				  apoderado.setEmail(rs.getString("email"));
				  logger.info("APODERADO7 " );
				  logger.info("APODERADO " + rs.getString("cedula"));
				  } 
			  return apoderado;
			  
		} catch (Exception e) {
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
	public int updateApoderado(ObjListApoderado apd) {


		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		Connection conn = null;
		
		String query =" UPDATE man_apoderado1  "
					  
					  +"   SET nombres = '"+apd.getNombres()+"' "
					  +"    ,apellidos = '"+apd.getApellido()+"' "
					  +"    ,cargo = '"+apd.getCargo()+"' "
					  +"    ,email = '"+apd.getEmail()+"' "
					  +"     WHERE cedula = '"+apd.getCedula()+"'";
	
		
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
	public int deleteApoderado(String cedula){
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
		
		String query = " UPDATE man_apoderado1 "
				  +"     SET "
				  +" 	 estatus = 1 "
				  +" 	,usuario = '"+sesion.getApp_user()+"' "
				  +" 	 WHERE "
				  +" 	 cedula = '"+cedula+"'" ; 
		
		logger.info("SQL DELETE " + query);
		
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
	public int crearApoderado(ObjListApoderado apd) {

			ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	    	AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
	    	AccessServerMail serverMail = (AccessServerMail) context.getBean("AccessServerMail");
	    	AccessPlantillasLogin bodyTemplate = (AccessPlantillasLogin) context.getBean("AccessPlantillasLogin");
	    	AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");

	    	String pass = genPass.getPassword();
	    	Connection conn = null;
	    	
	    	
	    	
	    	String query = "INSERT INTO man_apoderado1 "
	    				+"	(cedula "
				        +"	 ,nombres "
				        +"	 ,apellidos "
				        +"	 ,cargo "
				        +"   ,email,estatus) "
	    	
	    			
				        +" VALUES "
				        +"   ('"+apd.getCedula()+"' "
				        +"   ,'"+apd.getNombres()+"' "
				        +"   ,'"+apd.getApellido()+"' "
				        +"   ,'"+apd.getCargo()+"' "
				        +"   ,'"+apd.getEmail()+"',0) ";
				        
	    			
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
		

	
}
