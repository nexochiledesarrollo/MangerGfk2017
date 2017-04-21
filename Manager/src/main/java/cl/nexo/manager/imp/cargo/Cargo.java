package cl.nexo.manager.imp.cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.cargo.AccessCargo;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;


public class Cargo implements AccessCargo {
	private static final Logger logger = Logger.getLogger(Cargo.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public ArrayList<ObjComboSelectValueInt> getCombo(int subdiv){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combos = new ArrayList<ObjComboSelectValueInt>();
		
		
		
		String query="SELECT id_cargo "
				+"	      ,nombre_cargo "
				+"	FROM man_cargo  ";
				if(subdiv != 0){
					query = query	+" WHERE id_sdiv = "+subdiv+"  ";
				}
			    query = query +"	ORDER BY "
				+"	nombre_cargo ";
		
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueInt combo = new ObjComboSelectValueInt();
				  combo.setValue(rs.getInt("id_cargo"));
				  combo.setName(rs.getString("nombre_cargo"));
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
