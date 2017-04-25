package cl.nexo.manager.imp.agenda;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.agenda.AgendaAccess;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.obj.agenda.ObjAgenda;
import cl.nexo.manager.obj.agenda.ObjPersonaAgenda;
import cl.nexo.manager.obj.login.ObjLoginUser;


public class Agenda implements AgendaAccess {

	private static final Logger logger = Logger.getLogger(Agenda.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int setAgendado(ObjPersonaAgenda per) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		Connection conn = null;
    	
    	String query = "INSERT INTO man_proyecto_manager_inv_agenda_kick_off "
    				+"	(id_agenda "
			        +"	 ,id_user "
			        + "  ,email,asiste  )"			        

			        +" VALUES "
			        +"   ("+per.getid_agenda() +" "
			        +"   ,"+per.getUsuario().getId_user() + ""
			        +"   ,'"+per.getUsuario().getMail_user()+ "','False' )";
			    	

    	
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
	public ArrayList<ObjPersonaAgenda> getListAgendadosByid(int id_agenda) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		Connection conn = null;
		ArrayList<ObjPersonaAgenda> agendados = new ArrayList<ObjPersonaAgenda>();
		LoginAccess usuario = (LoginAccess) context.getBean("LoginAccess");
		
		String query = " SELECT * " 
			         + " FROM man_proyecto_manager_inv_agenda_kick_off tb where status=1 and id_agenda=" +  id_agenda; 

			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug("LLENANDO LA TABLITA   - ---- " + query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					  ObjLoginUser us  = new ObjLoginUser();
					  
					  us=usuario.getUserById(rs.getInt("id_user"));
					  ObjPersonaAgenda per = new ObjPersonaAgenda();
					  
					  per.setid_agenda(rs.getInt("id_agenda"));
					  per.setUsuario(us);
					  per.setEmail(us.getMail_user());
					  per.setId_usuario(us.getId_user());
					  
					  if(rs.getBoolean("asiste")){
						  per.setAsiste("<input type='checkbox' checked  name='chkusr_asist' id='chkusr_asist'  onClick='if(this.checked == true){JavaScript: asistenteSeleccionado("+us.getId_user()+","  + per.getid_agenda() +")} else{ JavaScript: asistenteNoSeleccionado("+us.getId_user()+","  + per.getid_agenda() +")}'>");
					  }else{
						  per.setAsiste("<input type='checkbox'  name='chkusr_asist' id='chkusr_asist'  onClick='if(this.checked == true){JavaScript: asistenteSeleccionado("+us.getId_user()+","  + per.getid_agenda() +")} else{ JavaScript: asistenteNoSeleccionado("+us.getId_user()+","  + per.getid_agenda() +")}'>");
					  }
					      agendados.add(per);
				  }
				  
				  return agendados;
				  
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
	public int DeleteAgendado(int user, int agenda) {

		
		Connection conn = null;
    	
    	String query = "UPDATE man_proyecto_manager_inv_agenda_kick_off "
    				+"	SET status=0 where id_agenda= " + agenda + " and id_user= " + user;

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
	public boolean getExistUserAgenda(int user, int agenda) {
		boolean result = false;
		Connection conn = null;
		
		String query = " SELECT id_user "
				  +"	 FROM man_proyecto_manager_inv_agenda_kick_off "
				  +"	 WHERE STATUS=1 AND  "
				  +" id_user = " + user + " and id_agenda= " + agenda ;
				  
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(" --------- ******--------- " + query);
			int usuario=0;
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {
				usuario = rs.getInt("id_user");
			}

			
			if (usuario == 0){
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
	public int SetAsistencia(int user, int agenda,int accion) {

		
		Connection conn = null;
		
		boolean asistencia = false;
		
		if (accion==1){
			asistencia = true;
		}
    	
    	String query = "UPDATE man_proyecto_manager_inv_agenda_kick_off "
    				+"	SET asiste='" + asistencia + "' where status=1 and id_agenda= " + agenda + " and id_user= " + user;
			       		        

    	
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
	public int createAgenda(ObjAgenda agen) {

		Connection conn = null;
		ResultSet rs;
		int idColVar = 0;
    	
    	String query = "INSERT INTO man_proyecto_manager_agenda_kick_off "
    				+"	(id_operacion "
			        +"	 ,id_user "
			        +"	 ,fecha "
			        +"   ,hora "
			        +"   ,lugar,estado_agenda  )"			        

			        +" VALUES "
			        +"   ("+agen.getId_operacion() +" "
			        +"   ,"+ agen.getId_usuario() + ""
			    	+"   ,'"+agen.getFecha()+ "'"
			    	+"   ,'"+agen.getHora() + "'"
			    	+"   ,'"+agen.getLugar()+ "' ,14 ) ";

    	logger.info(query);
    	
    	try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			logger.info(query);
			ps.executeUpdate();
			
			rs=ps.getGeneratedKeys();
			
			while (rs.next()) {
				  idColVar = Integer.parseInt(rs.getBigDecimal(1).toString());     
			}

    	} catch (SQLException e) {
    		
			throw new RuntimeException(e);
			
		} finally {
			
			if (conn != null) {
				try {
					conn.close();
					
				} catch (SQLException e) {}
			}
		}
		return idColVar;

	}

	
	@Override
	public void modificaAgenda(ObjAgenda agen) {

		Connection conn = null;
		
    	
    	String query = "UPDATE man_proyecto_manager_agenda_kick_off "
			        +"	 set fecha = '" +agen.getFecha()+ "'"
			        +"   ,hora = '" +agen.getHora() + "'"
			        +"   ,lugar= '" +agen.getLugar() + "'  WHERE id_agend= " + agen.getId_agenda();	        

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
	public int aceptarAgenda(int agenda) {

		
		Connection conn = null;
    	
    	String query = "UPDATE man_proyecto_manager_agenda_kick_off "
    				+"	SET estado_agenda=15 where id_agend= " + agenda;

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
	public int rechazarAgenda(int agenda) {

		
		Connection conn = null;
    	
    	String query = "UPDATE man_proyecto_manager_agenda_kick_off "
    				+"	SET estado_agenda=16 where id_agend= " + agenda;

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
	public ObjAgenda getAgendaAbiertaByidOperacion(int operacion) {

		Connection conn = null;
				
		String query = " SELECT * " 
			         + " FROM man_proyecto_manager_agenda_kick_off where estado_agenda=14 and id_operacion=" +  operacion; 

			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug(query);
				  ResultSet rs = ps.executeQuery();
				  ObjAgenda agen = new ObjAgenda();
				  
				  while (rs.next()) {
					  agen.setId_agenda(rs.getInt("id_agend"));
					  agen.setId_operacion(rs.getInt("id_operacion"));
					  agen.setFecha(rs.getString("fecha"));
					  agen.setLugar(rs.getString("lugar"));
					  agen.setHora(rs.getString("hora"));
					  agen.setId_estado_agenda(rs.getInt("estado_agenda"));
				  }
				  
				  return agen;
				  
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
	public ObjAgenda getAgendaDefinitivaByidOperacion(int operacion) {
		
		Connection conn = null;
				
		String query = " SELECT * " 
			         + " FROM man_proyecto_manager_agenda_kick_off where estado_agenda=15 and id_operacion=" +  operacion; 

			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug(query);
				  ResultSet rs = ps.executeQuery();
				  ObjAgenda agen = new ObjAgenda();
				  
				  while (rs.next()) {
					  agen.setId_agenda(rs.getInt("id_agend"));
					  agen.setId_operacion(rs.getInt("id_operacion"));
					  agen.setFecha(rs.getString("fecha"));
					  agen.setLugar(rs.getString("lugar"));
					  agen.setHora(rs.getString("hora"));
					  agen.setId_estado_agenda(rs.getInt("estado_agenda"));
				  }
				  
				  return agen;
				  
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
