package cl.nexo.manager.imp.lang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.lang.AccessLang;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueString;


public class Lang implements AccessLang{
	private static final Logger logger = Logger.getLogger(Lang.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public ArrayList<ObjComboSelectValueString> getCombo(){
		Connection conn = null;
		ArrayList<ObjComboSelectValueString> combos = new ArrayList<ObjComboSelectValueString>();
		
		String query="SELECT lang_lang "
				+"	      ,nombre_lang "
				+"	FROM man_lang  "
				+"	ORDER BY "
				+"	nombre_lang ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueString combo = new ObjComboSelectValueString();
				  combo.setId(rs.getString("lang_lang"));
				  combo.setText(rs.getString("nombre_lang"));
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
