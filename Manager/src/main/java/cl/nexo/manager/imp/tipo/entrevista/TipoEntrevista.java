package cl.nexo.manager.imp.tipo.entrevista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.tipo.entrevista.AccessTipoEntrevista;
import cl.nexo.manager.imp.tipo.pago.TipoPago;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;

public class TipoEntrevista implements AccessTipoEntrevista {
	private static final Logger logger = Logger.getLogger(TipoEntrevista.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public ArrayList<ObjComboSelectValueInt> getCombo(int id){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combos = new ArrayList<ObjComboSelectValueInt>();
		
		String query="SELECT id_tipo_entrevista "
				+"	      ,nombre_tipo_entrevista "
				+"	FROM man_tipo_entrevista  "
				+"	WHERE id_area ="+id+" "
				+"	ORDER BY "
				+"	nombre_tipo_entrevista ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueInt combo = new ObjComboSelectValueInt();
				  combo.setValue(rs.getInt("id_tipo_entrevista"));
				  combo.setName(rs.getString("nombre_tipo_entrevista"));
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
