package cl.nexo.manager.imp.reunion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.agenda.AgendaAccess;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.reunion.AccessReunion;
import cl.nexo.manager.obj.agenda.ObjPersonaAgenda;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.reunion.ObjReunionKickOff;


public class Reunion implements AccessReunion {

	private static final Logger logger = Logger.getLogger(Reunion.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	

	@Override
	public int aceptarReunion(ObjReunionKickOff reu) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		Connection conn = null;
    	
    	String query = "INSERT INTO man_proyecto_manager_reunion_kick_off "
    				+"	(id_operacion "
			        +"	 ,f_inicio_campo "
			        +"	 ,f_fin_campo "
			        +"   ,f_ini_bbdd "
			        +"   ,f_fin_bbdd"
			        +"   ,f_entrega"
			        +"   ,id_dir_campo"
			        +"   ,id_jefe_calidad"
			        +"   ,id_usr_procesamiento"
			        +"   ,id_usr_scripting  )"			        

			        +" VALUES "
			        +"   ("+reu.getId_operacion() +" "
			    	+"   ,'"+reu.getF_ini_campo()+ "'"
			    	+"   ,'"+reu.getF_fin_campo()+ "'"
			    	+"   ,'"+reu.getF_ini_bbdd()+ "'"
			    	+"   ,'"+reu.getF_fin_bbdd()+ "'"
			    	+"   ,'"+reu.getF_entrega()+ "'"
			    	+"   ,"+reu.getDirector_campo().getId_user()+ ""
			    	+"   ,"+reu.getJefe_calidad().getId_user()+ ""
			    	+"   ,"+reu.getProcesamiemto().getId_user()+ ""
			    	+"   ,"+reu.getScripting().getId_user()+ " )";
			    	

    	
    	logger.info(query);
    	
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
	public boolean existeReunionByidOperacion(int operacion) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		
		Connection conn = null;
		ObjLoginUser user = new ObjLoginUser();
		
		String query = "SELECT count(*) as cantidad "
				      
				  +"FROM man_proyecto_manager_reunion_kick_off  "
				  +"WHERE  "
				  +"id_operacion = "+operacion;
				
				 try {
					  conn = dataSource.getConnection();
					  PreparedStatement ps = conn.prepareStatement(query);
					  ResultSet rs = ps.executeQuery();
					  boolean existe = false;
					  int cantidad = 0;
					  while (rs.next()) {
						 cantidad=rs.getInt("cantidad");
					  }
					  
					  if (cantidad!=0){
						  existe= true;
					  }
					  
					  
					  return existe;
					  
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
