package cl.nexo.manager.imp.proyecto;

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

import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.producto.AccessProducto;
import cl.nexo.manager.access.proyecto.AccessAsignUsers;
import cl.nexo.manager.access.proyecto.AccessCuestionario;
import cl.nexo.manager.obj.asignacion.usuario.ObjAsignUser;
import cl.nexo.manager.obj.asignacion.usuario.ObjAsignUserDetalle;
import cl.nexo.manager.obj.asignacion.usuario.ObjFiltroAsignUser;
import cl.nexo.manager.obj.proyecto.ObjEstudio;

public class AsignUsers implements AccessAsignUsers {
	private static final Logger logger = Logger.getLogger(AsignUsers.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@Override
	public ArrayList<ObjAsignUserDetalle> getDetalleAsignUser(ObjFiltroAsignUser fil){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessProducto prods = (AccessProducto) context.getBean("AccessProducto");
		AccessCuestionario cuests = (AccessCuestionario) context.getBean("AccessCuestionario");
		
		ArrayList<ObjAsignUserDetalle> ess = new ArrayList<ObjAsignUserDetalle>();
		Connection conn = null;
		
		String query = " SELECT d.id_operacion "
					  +"      ,d.id_usuario "
					  +"	      ,d.horas_asig "
					  +"	      ,d.dia_asig "
					  +"	  FROM man_proyecto_manager_horas_recurso_detalle d "
					  +"	  INNER JOIN man_login_user l on d.id_usuario = l.id_user "
					  +"	  WHERE "
					  +"	  d.dia_asig >= '"+fil.getDesde()+ "' "
					  +"	  AND d.dia_asig <= '"+fil.getHasta()+ "' "
					  +"	  AND id_usuario = "+fil.getUser()+" ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug("query_estudio: "+query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  ObjAsignUserDetalle es = new ObjAsignUserDetalle();
				  es.setId_usuario(rs.getInt("id_usuario"));
				  es.setId_operacion(rs.getInt("id_operacion"));
				  es.setFecha(format3.format(rs.getDate("dia_asig")));
				  es.setCantidad(rs.getInt("horas_asig"));
				 
				 ess.add(es);
				  
			  }
			  
			  
			
			  
			  
			  return ess;
			  
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
	public ArrayList<ObjAsignUser> getListAsignUser(ObjFiltroAsignUser fil){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessProducto prods = (AccessProducto) context.getBean("AccessProducto");
		AccessCuestionario cuests = (AccessCuestionario) context.getBean("AccessCuestionario");
		
		ArrayList<ObjAsignUser> ess = new ArrayList<ObjAsignUser>();
		Connection conn = null;
		
		String query = " SELECT d.id_operacion "
					  +"	      ,d.id_usuario "
					  +"	      ,d.horas_asig "
					  +"		  ,l.nombre_user "
					  +"		  ,l.app_user "
					  +"		  ,l.id_cargo "
					  +"		  ,c.nombre_cargo "
					  +"	FROM man_proyecto_manager_horas_recurso d "
					  +"	INNER JOIN man_login_user l on d.id_usuario = l.id_user "
					  +"	INNER JOIN man_cargo c on c.id_cargo = l.id_cargo ";
					  
					  if(fil.getDiv() != 0 || fil.getSub() != 0){
									  
							query = query +" WHERE "; 
					  }
					  if(fil.getDiv() != 0 && fil.getSub() == 0){
							query = query +" l.id_division = "+fil.getDiv()+" ";
					  }
					  if(fil.getDiv() != 0 && fil.getSub() != 0){
							query = query +" l.id_sdiv = "+fil.getSub()+" ";
					  }
						
						
							 
					  query = query +"ORDER BY l.id_cargo, l.app_user, l.nombre_user ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug("getListAsignUser: "+query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  ObjAsignUser es = new ObjAsignUser();
					  
				  	  fil.setUser(rs.getInt("id_usuario"));
				  
				  	  es.setId_operacion(rs.getInt("id_operacion"));
					  es.setId_usuario(rs.getInt("id_usuario"));
					  es.setStr_usuario(rs.getString("app_user") +", "+rs.getString("nombre_user"));
					  es.setCargo_usuario(rs.getInt("id_cargo"));
					  es.setStr_cargo(rs.getString("nombre_cargo"));
					  es.setTotal_asignado(rs.getInt("horas_asig"));
					  es.setDetalle(this.getDetalleAsignUser(fil));
				 ess.add(es);
				  
			  }
			  
			  return ess;
			  
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
