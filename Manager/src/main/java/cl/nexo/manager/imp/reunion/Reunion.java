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
import cl.nexo.manager.obj.reunion.ObjAprob;
import cl.nexo.manager.obj.reunion.ObjBitacoraAprob;
import cl.nexo.manager.obj.reunion.ObjReunionKickOff;


public class Reunion implements AccessReunion {

	private static final Logger logger = Logger.getLogger(Reunion.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	

	@Override
	public int aceptarReunion(ObjReunionKickOff reu) {

	
		
		Connection conn = null;
    	
    	String query = "INSERT INTO man_proyecto_manager_reunion_kick_off "
    				+"	(id_operacion "
			        +"   ,id_dir_campo"
			        +"   ,id_jefe_calidad"
			        +"   ,id_usr_procesamiento"
			        +"   ,id_usr_scripting,id_agenda  )"			        

			        +" VALUES "
			        +"   ("+reu.getId_operacion() +" "
			    	+"   ,"+reu.getDirector_campo().getId_user()+ ""
			    	+"   ,"+reu.getJefe_calidad().getId_user()+ ""
			    	+"   ,"+reu.getProcesamiemto().getId_user()+ ""
			    	+"   ,"+reu.getScripting().getId_user()+ "," + reu.getId_agenda() + " )";
			    	

    	
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
	public void updateCampo(int oper, String campo) {

		Connection conn = null;
    	
    	String query = "UPDATE man_proyecto_manager_aprobaciones "
    				+"	SET STATUS_CAMPO = '" + campo + "' where id_operacion=" + oper ;			        

    	logger.info(query);
    	
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
	
	
	@Override
	public void updateScripting(int oper, String campo) {

		Connection conn = null;
    	
    	String query = "UPDATE man_proyecto_manager_aprobaciones "
    				+"	SET STATUS_Scripting = '" + campo + "' where id_operacion=" + oper ;			        

    	logger.info(query);
    	
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
	
	@Override
	public void updateCalidad(int oper, String campo) {

		Connection conn = null;
    	
    	String query = "UPDATE man_proyecto_manager_aprobaciones "
    				+"	SET STATUS_Calidad = '" + campo + "' where id_operacion=" + oper ;			        

    	logger.info(query);
    	
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

	
	@Override
	public void updateTabulacion(int oper, String campo) {

		Connection conn = null;
    	
    	String query = "UPDATE man_proyecto_manager_aprobaciones "
    				+"	SET STATUS_Tabulacion = '" + campo + "' where id_operacion=" + oper ;			        

    	logger.info(query);
    	
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
	
	
	
	@Override
	public void createBitacora(int oper, String login, String observacion) {

		Connection conn = null;
    	
    	String query = "INSERT INTO man_proyecto_manager_bitacora_aprobacion (usuario,observacion,id_operacion) values ( "
    				+"	'" + login + "','" + observacion + "'," + oper + ")";		        

    	logger.info(query);
    	
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
	
	
	@Override
	public void createAprobacion(int oper) {

		Connection conn = null;
    	
    	String query = "INSERT INTO man_proyecto_manager_aprobaciones "
    			     + " (id_operacion,status_campo,status_scripting,status_calidad,status_tabulacion) "
    			     + "  values (" + oper  + ",'FALSE','FALSE','FALSE','FALSE')";
    			     

    	logger.info(query);
    	
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
	
	
	@Override
	public boolean getExistAprobaciones(int operacion) {
		boolean result = false;
		Connection conn = null;
		
		String query = " SELECT id_aprob "
				  +"	 FROM man_proyecto_manager_aprobaciones "
				  +"	 WHERE   "
				  +"     id_operacion = " + operacion  ;
				  
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(" --------- ******--------- " + query);
			int aprob=0;
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {
				aprob = rs.getInt("id_aprob");
			}

			
			if (aprob == 0){
				result = false;
			}else{
				result = true;
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
	public ArrayList<ObjBitacoraAprob> getListBitacoraAprobByid(int id_operacion) {
		logger.debug("LLENANDO LA TABLITA   - ---- ");
		Connection conn = null;
		ArrayList<ObjBitacoraAprob> bitacoras = new ArrayList<ObjBitacoraAprob>();
	
		
		String query = " SELECT * " 
			         + " FROM man_proyecto_manager_bitacora_aprobacion tb where id_operacion=" +  id_operacion; 

			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.info("LLENANDO LA TABLITA   - ---- " + query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					 
					  ObjBitacoraAprob bit = new ObjBitacoraAprob();
					  
					  bit.setId_operacion(rs.getInt("id_operacion"));
					  bit.setObservacion(rs.getString("observacion"));
					  bit.setFecha(rs.getString("fecha"));
					  bit.setUsuario(rs.getString("usuario"));
					  bitacoras.add(bit);
				  }
				  
				  return bitacoras;
				  
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
	public ObjAprob getAprobByid(int id_operacion) {
		
		Connection conn = null;
	
		String query = " SELECT * " 
			         + " FROM man_proyecto_manager_aprobaciones tb where id_operacion=" +  id_operacion; 

			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				 
				  ResultSet rs = ps.executeQuery();
				  ObjAprob apb = new ObjAprob();
				  while (rs.next()) {  
					 
					 
					  
					  apb.setId_operacion(rs.getInt("id_operacion"));
					  apb.setStatus_campo(rs.getBoolean("status_campo"));
					  apb.setStatus_scripting(rs.getBoolean("status_scripting"));
					  apb.setStatus_calidad(rs.getBoolean("status_calidad"));
					  apb.setStatus_tabulacion(rs.getBoolean("status_tabulacion"));
					  
				  }
				  
				  return apb;
				  
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
	public ObjAprob statusAprobaciones(int operacion) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		
		Connection conn = null;
		ObjLoginUser user = new ObjLoginUser();
		
		String query = "SELECT * "
				      
				  +"FROM man_proyecto_manager_aprobaciones  "
				  +"WHERE  "
				  +"id_operacion = "+operacion;
				
				 try {
					  conn = dataSource.getConnection();
					  PreparedStatement ps = conn.prepareStatement(query);
					  ResultSet rs = ps.executeQuery();
					  ObjAprob apro = new ObjAprob();
					 
					  while (rs.next()) {
						 apro.setStatus_campo(rs.getBoolean("status_campo"));
						 apro.setStatus_scripting(rs.getBoolean("status_scripting"));
						 apro.setStatus_calidad(rs.getBoolean("status_calidad"));
						 apro.setStatus_tabulacion(rs.getBoolean("status_tabulacion"));
					  }

					  return apro;
					  
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
