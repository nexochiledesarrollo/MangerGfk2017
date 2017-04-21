package cl.nexo.manager.imp.setup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.setup.AccessSetUp;
import cl.nexo.manager.obj.setup.ObjComboDetalle;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;


public class SetUp implements AccessSetUp {
	
	private static final Logger logger = Logger.getLogger(SetUp.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public ArrayList<ObjComboSelect2ValueInt> getListBase(String pace){
		Connection conn = null;
		
		ArrayList<ObjComboSelect2ValueInt> combos =  new ArrayList<ObjComboSelect2ValueInt>();
		String query="SELECT id_combo "
					  +"    ,des_combo "
					  +" FROM man_combo_box";
					
					query= query +" ORDER BY des_combo";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
				ObjComboSelect2ValueInt combo2 = new ObjComboSelect2ValueInt(); 
				 combo2.setId(0);
				 combo2.setText(pace);
				 combos.add(combo2);
			 
				 while(rs.next()) {
				 ObjComboSelect2ValueInt combo3 = new ObjComboSelect2ValueInt(); 
				 combo3.setId(rs.getInt("id_combo"));
				 combo3.setText(rs.getString("des_combo"));
				 combos.add(combo3);
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
	public ArrayList<ObjComboDetalle> getListDetalle(int combo, String lang){
		Connection conn = null;
		
		ArrayList<ObjComboDetalle> dets =  new ArrayList<ObjComboDetalle>();
		String query="SELECT d.id_detalle "
					 +"     ,d.id_combo "
					 +"     ,d.valor_detalle "
					 +"     ,d.des_detalle "
					 +"     ,d.id_lang "
					 +"     ,d.depende_detalle "
					 +"     ,d.combo_depende "
					 +"     ,d.orden_combo "
					 +"     ,(i.valor_detalle)as str_combo_depende  " 
					 +" FROM man_combo_box_detalle d "
					 +" LEFT JOIN man_combo_box_detalle i ON d.combo_depende = i.id_detalle "
					 +" WHERE "
					 + " d.id_combo = "+ combo +" "
					 + " AND d.id_lang = '"+lang+"'";
					
					
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ObjComboDetalle det = new ObjComboDetalle(); 
				 det.setId_detalle(rs.getInt("id_detalle"));
				 det.setId_combo(rs.getInt("id_combo"));
				 det.setValor_detalle(rs.getString("valor_detalle"));
				 det.setDes_detalle(rs.getString("des_detalle"));
				 det.setId_lang(rs.getString("id_lang"));
				 det.setDepende_detalle(rs.getString("depende_detalle"));
				 det.setCombo_depende(rs.getInt("combo_depende"));
				 det.setOrden_combo(rs.getInt("orden_combo"));
				 det.setNombre_combo("");
				 det.setStr_combo_depende(rs.getString("str_combo_depende"));
				 dets.add(det);
			  }
			  return dets;
			  
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
	public int deleteDetalleCombo(int id){
		Connection conn = null;
		String query = " DELETE FROM man_combo_box_detalle "
					  +" WHERE id_detalle = "+ id;
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			return 1;
			
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
