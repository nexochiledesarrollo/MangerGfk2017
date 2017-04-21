package cl.nexo.manager.imp.agenda;

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
import cl.nexo.manager.access.login.LoginAccess;
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
    	
    	String query = "INSERT INTO man_proyecto_manager_agenda_kick_off "
    				+"	(id_operacion "
			        +"	 ,id_user "
			        +"	 ,fecha "
			        //+",hora "
			        +"   ,lugar "
			        + "  ,email,asiste  )"			        

			        +" VALUES "
			        +"   ("+per.getId_operacion() +" "
			        +"   ,"+per.getUsuario().getId_user() + ""
			    	+"   ,'"+per.getFecha()+ "'"
			    	//+"   ,'"+per.get+ "'"
			    	+"   ,'"+per.getLugar()+ "'"
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
	public ArrayList<ObjPersonaAgenda> getListAgendadosByidOperacion(int id_operacion) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		Connection conn = null;
		ArrayList<ObjPersonaAgenda> agendados = new ArrayList<ObjPersonaAgenda>();
		LoginAccess usuario = (LoginAccess) context.getBean("LoginAccess");
		
		String query = " SELECT * " 
			         + " FROM man_proyecto_manager_agenda_kick_off tb where status=1 and id_operacion=" +  id_operacion; 

			  try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug(query);
				  ResultSet rs = ps.executeQuery();
				  while (rs.next()) {  
					  ObjLoginUser us  = new ObjLoginUser();
					  
					  us=usuario.getUserById(rs.getInt("id_user"));
					  ObjPersonaAgenda per = new ObjPersonaAgenda();
					  //apoderado.setCedula("<a href='JavaScript: handleDetalleApoderado("+rs.getString("cedula")+");'><strong>"+rs.getString("cedula")+"</strong></a>");
					  per.setId_operacion(rs.getInt("id_operacion"));
					  per.setUsuario(us);
					  per.setFecha(rs.getString("fecha"));
					  per.setLugar(rs.getString("lugar"));
					  per.setEmail(us.getMail_user());
					  per.setHora("Algo");
					  per.setId_usuario(us.getId_user());
					  if(rs.getBoolean("asiste")){
						  per.setAsiste("<input type='checkbox' checked  name='chkusr_asist' id='chkusr_asist'  onClick='if(this.checked == true){JavaScript: asistenteSeleccionado("+us.getId_user()+","  + per.getId_operacion() +")} else{ JavaScript: asistenteNoSeleccionado("+us.getId_user()+","  + per.getId_operacion() +")}'>");
					  }else{
						  per.setAsiste("<input type='checkbox'  name='chkusr_asist' id='chkusr_asist'  onClick='if(this.checked == true){JavaScript: asistenteSeleccionado("+us.getId_user()+","  + per.getId_operacion() +")} else{ JavaScript: asistenteNoSeleccionado("+us.getId_user()+","  + per.getId_operacion() +")}'>");
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
	public int DeleteAgendado(int user, int operacion) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		Connection conn = null;
    	
    	String query = "UPDATE man_proyecto_manager_agenda_kick_off "
    				+"	SET status=0 where id_operacion= " + operacion + " and id_user= " + user;
			       		        

    	
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
	public boolean getExistUserAgenda(int user, int operacion) {
		boolean result = false;
		Connection conn = null;
		
		String query = " SELECT id_user "
				  +"	 FROM man_proyecto_manager_agenda_kick_off "
				  +"	 WHERE STATUS=1 AND  "
				  +" id_user = " + user + " and id_operacion= " + operacion ;
				  
		
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
	public int SetAsistencia(int user, int operacion,int accion) {

		
		Connection conn = null;
		
		boolean asistencia = false;
		
		if (accion==1){
			asistencia = true;
		}
    	
    	String query = "UPDATE man_proyecto_manager_agenda_kick_off "
    				+"	SET asiste='" + asistencia + "' where status=1 and id_operacion= " + operacion + " and id_user= " + user;
			       		        

    	
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


	
	
	
	
	
	
	
	
	
	
	
	

	
}
