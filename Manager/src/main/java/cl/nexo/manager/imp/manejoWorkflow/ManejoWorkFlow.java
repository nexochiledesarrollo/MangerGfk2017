package cl.nexo.manager.imp.manejoWorkflow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import cl.nexo.manager.access.agenda.AgendaAccess;
import cl.nexo.manager.access.apoderado.ApoderadoAccess;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.instructivo.InstructivoAccess;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.manejoworkflow.ManejoWorkflowAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.access.tarea.AccessTarea;
import cl.nexo.manager.obj.agenda.ObjPersonaAgenda;
import cl.nexo.manager.obj.apoderado.ObjListApoderado;
import cl.nexo.manager.obj.instructivo.ObjInstructivo;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;



public class ManejoWorkFlow implements ManejoWorkflowAccess {

	private static final Logger logger = Logger.getLogger(ManejoWorkFlow.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int SetUltimoEstado(int operacion, int actividad, int estado,
			int usuario) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		Connection conn = null;
    	
    	String query = "UPDATE man_workflow_estudio "
    				+"	SET id_estado=" + estado + ", fecha=GETDATE(),id_user=" + usuario + "  where id_operacion= " + operacion + " and id_actividad= " + actividad;

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
	public int buscarIdWorkPorActividad(int operacion, int actividad) {

		Connection conn = null;
	
		String query = "SELECT "  
			     +" tb.id_workflow "
                 +" FROM man_workflow_estudio tb where id_operacion ="+ operacion + " and id_actividad= " + actividad;
		
		logger.info("SQL " + query);
		
		  try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();
			 
			  int id_work = 0;
				  while (rs.next()) {  
					  id_work=rs.getInt("id_workflow");  
				  } 
			  return id_work;
			  
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
	public int SetBitacoraActividad(int id_workflow, String observacion,
			int usuario) {
		

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		Connection conn = null;
    	
    	String query = "INSERT INTO man_workflow_estudio_bitacora (id_workflow,observacion,id_user,fecha) "
    				 +" VALUES ( " + id_workflow + ",'" + observacion + "'," + usuario + ",getDate())";

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
	public int createWorkActividad(int operacion, int actividad, int estado,
			int usuario) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		Connection conn = null;
    	
    	String query = "INSERT INTO man_workflow_estudio (id_operacion,id_actividad,id_estado,id_user,fecha) "
    				 +" VALUES ( " + operacion + "," + actividad + "," + estado + "," + usuario + ",getDate())";

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
	public void genericWorkActividad(int operacion, int actividad, String observacion,
			int nuevo_estado,int id_user) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		ManejoWorkflowAccess work = (ManejoWorkflowAccess) context.getBean("ManejoWorkflowAccess");

        int id_workFlow;
  
        ///Buscar si Existe el Registro de WorkFlow para esa Actividad
        id_workFlow= work.buscarIdWorkPorActividad(operacion, actividad);
        
        if (id_workFlow==0){ /// no existe 
            // Inicio Workflow para esa actividad
        	work.createWorkActividad(operacion, actividad, nuevo_estado, id_user);
        	 // Busco el Nuevo Codigo
        	id_workFlow= work.buscarIdWorkPorActividad(operacion, actividad);
        }
        
        
	        // Regstro el Ultimo Estado
        	work.SetUltimoEstado(operacion, actividad, nuevo_estado, id_user); 
	    	// Resgistro la Bitacora
        	work.SetBitacoraActividad(id_workFlow, observacion, id_user);
	    	
		
		
	}
	
	
	
	

	

	
}
