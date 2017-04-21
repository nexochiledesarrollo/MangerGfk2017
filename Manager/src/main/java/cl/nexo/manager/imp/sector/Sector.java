package cl.nexo.manager.imp.sector;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.sector.AccessSector;
import cl.nexo.manager.imp.login.LoginUser;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;

public class Sector implements AccessSector {
	
	private static final Logger logger = Logger.getLogger(Sector.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@Override
	public ArrayList<ObjComboSelect2ValueInt> getCombo(){
		
		Connection conn = null;
		ArrayList<ObjComboSelect2ValueInt> combos = new ArrayList<ObjComboSelect2ValueInt>();
		
		String query="SELECT id_sector "
					     +" ,nombre_sector "
					  +" FROM man_sector "
					  +" ORDER BY nombre_sector ";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			     while(rs.next()) {
					  ObjComboSelect2ValueInt combo = new ObjComboSelect2ValueInt();
					  combo.setId(rs.getInt("id_sector"));
					  combo.setText(escapeHtml(rs.getString("nombre_sector")));
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
