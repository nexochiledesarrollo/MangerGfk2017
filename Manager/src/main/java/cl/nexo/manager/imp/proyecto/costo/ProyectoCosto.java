package cl.nexo.manager.imp.proyecto.costo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.proyecto.costo.AccessProyectoCosto;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;


public class ProyectoCosto implements AccessProyectoCosto{
	private static final Logger logger = Logger.getLogger(ProyectoCosto.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@Override
	public ArrayList<ObjComboSelect2ValueInt> getCombo(int tipo, int id){
		/*
		 * tipo = 0 -- todo
		 * tipo = 1 -- Categoria
		 * 
		 * */
		
		Connection conn = null;
		ArrayList<ObjComboSelect2ValueInt> combos = new ArrayList<ObjComboSelect2ValueInt>();
		
		String query="SELECT id_costo_lista "
				+"	      ,nombre_costo_lista "
				+"	FROM man_proyecto_tipo_costo_activo ";
				
		if(tipo == 1){
			query= query+"	 "
					+ " WHERE  "
					+ " id_categoria_costo = "+id+" ";
		}
		
		query= query+"	ORDER BY "
				+"	nombre_costo_lista";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 while(rs.next()) {
					  ObjComboSelect2ValueInt combo = new ObjComboSelect2ValueInt();
					  combo.setId(rs.getInt("id_costo_lista"));
					  combo.setText(rs.getString("nombre_costo_lista"));
					  combos.add(combo);
				  }
			 }
			  return combos;
			  
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException localSQLException2) {}
			}
		}
	}
}
