package cl.nexo.manager.imp.division;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.division.AccessDivision;

import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;

public class Division implements AccessDivision {
	
	private static final Logger logger = Logger.getLogger(Division.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public ArrayList<ObjComboSelectValueInt> getComboDivision(){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combos = new ArrayList<ObjComboSelectValueInt>();
		
		String query="SELECT id_div "
				+"	      ,nombre_div "
				+"	FROM man_divisiones  "
				+"	ORDER BY "
				+"	orden_div ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueInt combo = new ObjComboSelectValueInt();
				  combo.setValue(rs.getInt("id_div"));
				  combo.setName(rs.getString("nombre_div"));
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
	public ArrayList<ObjComboSelectValueInt> getComboSubDivision(int div){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combos = new ArrayList<ObjComboSelectValueInt>();
		
		String query="SELECT id_sdiv "
				+"	      ,nombre_sdiv "
				+"	FROM man_sub_division  "
				+"	WHERE id_div = "+div+"  "
				+"	ORDER BY "
				+"	nombre_sdiv ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueInt combo = new ObjComboSelectValueInt();
				  combo.setValue(rs.getInt("id_sdiv"));
				  combo.setName(rs.getString("nombre_sdiv"));
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
