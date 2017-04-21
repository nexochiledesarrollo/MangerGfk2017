package cl.nexo.manager.imp.traza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.industria.AccessIndustria;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.traza.AccessTraza;
import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudioFiltros;
import cl.nexo.manager.obj.traza.ObjTrazaManager;
import cl.nexo.manager.obj.traza.ObjTrazaManagerEdit;


public class Traza implements AccessTraza{
	
	private static final Logger logger = Logger.getLogger(Traza.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@Override
	public int setTraza(ObjTrazaManager traza){
		int result = 0;
		Connection conn = null;
		String query = " INSERT INTO man_traza_workflow "
           +" (fecha_traza "
           +" ,id_usuario "
           +" ,id_operacion "
           +" ,id_flujo "
           +" ,id_tarea "
           +" ,id_modulo "
           +" ,nombre_traza "
           +" ,detalle_traza "
           +" ,id_estado ) "
           +" VALUES "
           +" ('"+traza.getFecha_traza()+"',"+traza.getId_usuario()+","+traza.getId_operacion()+","+traza.getId_flujo()+","+traza.getId_tarea()+","+traza.getId_modulo()+",'"+traza.getNombre_traza()+"','"+traza.getDetalle_traza()+"',"+traza.getId_estado()+"); ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);

			logger.debug(query);
			
			ps.executeUpdate();
			
			
			result = 1;
			
			
			return result;
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
	public ArrayList<ObjTrazaManagerEdit> getListTrazaByIdOp(int id){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessIndustria inds = (AccessIndustria) context.getBean("AccessIndustria");
		
		ArrayList<ObjTrazaManagerEdit> re = new ArrayList<ObjTrazaManagerEdit>();
		
		Connection conn = null;
		
		String query=" SELECT t.id_traza "
					 +"     ,t.fecha_traza "
					 +"     ,t.id_usuario "
					 +"     ,t.id_operacion "
					 +"     ,t.id_flujo "
					 +"     ,t.id_tarea "
					 +"     ,t.id_modulo "
					 +"     ,t.nombre_traza "
					 +"     ,t.detalle_traza "
					 +"     ,t.id_estado "
					 +"     ,e.nombre_estado "
					 +"     ,f.nombre_flujo "
					 +"     ,mm.nombre_modulo "
					 +" FROM man_traza_workflow t "
					 +" LEFT JOIN man_estados e ON e.cod_estado = t.id_estado "
					 +" LEFT JOIN man_flujo f ON f.cod_flujo = t.id_flujo "
					 +" LEFT JOIN man_menu_modulo mm ON mm.id_modulo = t.id_modulo "
					 +" WHERE t.id_operacion = " + id + " "
					 
					 +" ORDER BY t.id_traza ";
				  
				  
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  ObjTrazaManagerEdit res = new ObjTrazaManagerEdit();
				  res.setId_traza(rs.getInt("id_traza"));
				  res.setFecha_traza(format3.format(rs.getTimestamp("fecha_traza")));
				  res.setStr_usuario(logins.getStrLoginById(rs.getInt("id_usuario")));
				  res.setId_operacion(rs.getInt("id_operacion"));
				  res.setId_flujo(rs.getInt("id_flujo"));
				  res.setNombre_flujo(rs.getString("nombre_flujo"));
				  res.setId_tarea(rs.getInt("id_tarea"));
				  res.setId_modulo(rs.getInt("id_modulo"));
				  res.setNombre_modulo(rs.getString("nombre_modulo"));
				  res.setNombre_traza(rs.getString("nombre_traza"));
				  res.setDetalle_traza(rs.getString("detalle_traza"));
				  res.setId_estado(rs.getInt("id_estado"));
				  res.setNombre_estado(rs.getString("nombre_estado"));
				  
				re.add(res);  
			  }
			  
			  
			  return re;
			  
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
