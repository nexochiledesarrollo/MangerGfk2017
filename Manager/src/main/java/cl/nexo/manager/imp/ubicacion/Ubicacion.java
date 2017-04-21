package cl.nexo.manager.imp.ubicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.ubicacion.AccessUbicacion;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;


public class Ubicacion implements AccessUbicacion {
	
	private static final Logger logger = Logger.getLogger(Ubicacion.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@Override
	public ArrayList<ObjComboSelectValueInt> getPais(){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combos = new ArrayList<ObjComboSelectValueInt>();
		
		String query="SELECT id_pais "
				+"	      ,nombre_pais "
				+"	FROM man_pais  "
				+"	ORDER BY "
				+"	nombre_pais ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueInt combo = new ObjComboSelectValueInt();
				  combo.setValue(rs.getInt("id_pais"));
				  combo.setName(rs.getString("nombre_pais"));
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
	public ArrayList<ObjComboSelectValueInt> getRegion(int pais){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combos = new ArrayList<ObjComboSelectValueInt>();
		
		String query="SELECT id_region "
				+"	      ,nombre_region "
				+"	FROM man_region  "
				+"	WHERE id_pais =  " + pais + " "
				+"	ORDER BY "
				+"	nombre_region ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueInt combo = new ObjComboSelectValueInt();
				  combo.setValue(rs.getInt("id_region"));
				  combo.setName(rs.getString("nombre_region"));
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
	public ArrayList<ObjComboSelectValueInt> getCiudad(int region){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combos = new ArrayList<ObjComboSelectValueInt>();
		
		String query="SELECT id_ciudad "
				+"	      ,nombre_ciudad "
				+"	FROM man_ciudad  "
				+"	WHERE id_region =  " + region + " "
				+"	ORDER BY "
				+"	nombre_ciudad ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueInt combo = new ObjComboSelectValueInt();
				  combo.setValue(rs.getInt("id_ciudad"));
				  combo.setName(rs.getString("nombre_ciudad"));
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
	public ArrayList<ObjComboSelectValueInt> getComuna(int ciudad){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combos = new ArrayList<ObjComboSelectValueInt>();
		
		String query="SELECT id_comuna "
				+"	      ,nombre_comuna "
				+"	FROM man_comuna  "
				+"	WHERE id_ciudad =  " + ciudad + " "
				+"	ORDER BY "
				+"	nombre_comuna ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueInt combo = new ObjComboSelectValueInt();
				  combo.setValue(rs.getInt("id_comuna"));
				  combo.setName(rs.getString("nombre_comuna"));
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
	
}
