package cl.nexo.manager.imp.tipo.contrato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.tipo.contrato.AccessTipoContrato;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;


public class TipoContrato implements AccessTipoContrato {
	private static final Logger logger = Logger.getLogger(TipoContrato.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public ArrayList<ObjComboSelectValueInt> getCombo(){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combos = new ArrayList<ObjComboSelectValueInt>();
		
		String query="SELECT id_tipoc "
				+"	      ,nombre_tipoc "
				+"	FROM man_tipo_contrato  "
				+"	ORDER BY "
				+"	nombre_tipoc ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueInt combo = new ObjComboSelectValueInt();
				  combo.setValue(rs.getInt("id_tipoc"));
				  combo.setName(rs.getString("nombre_tipoc"));
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
