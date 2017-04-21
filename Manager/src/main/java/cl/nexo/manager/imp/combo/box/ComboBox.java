package cl.nexo.manager.imp.combo.box;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.nexo.manager.access.combo.box.AccessComboBox;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueString;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;


public class ComboBox implements AccessComboBox {
	
	private static final Logger logger = Logger.getLogger(ComboBox.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public ArrayList<ObjComboSelect2ValueString> getCombo(int id, String lang, int depende,String place, int comboDepende){
		Connection conn = null;
		ArrayList<ObjComboSelect2ValueString> combos = new ArrayList<ObjComboSelect2ValueString>();
		
		String query="SELECT id_detalle "
					 +"     ,id_combo "
					 +"     ,valor_detalle "
					 +"     ,des_detalle "
					 +"     ,id_lang "
					 +"     ,depende_detalle "
					 +" FROM man_combo_box_detalle "
					 +" WHERE id_lang = '"+lang+"' "
					 +" AND id_combo = "+id+" ";
					if(depende != 0){ 
						query= query +"	AND depende_detalle =  " + depende +" "
									 +" AND combo_depende =  " + comboDepende +" ";
					}
					query= query +" ORDER BY valor_detalle";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			ObjComboSelect2ValueString combo2 = new ObjComboSelect2ValueString();
			  combo2.setId("0");
			  combo2.setText(place);
			  combos.add(combo2);
			 while(rs.next()) {
				 ObjComboSelect2ValueString combo = new ObjComboSelect2ValueString();
				  combo.setId(rs.getString("valor_detalle"));
				  combo.setText(rs.getString("des_detalle"));
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
	
	public ObjComboSelect2ValueInt getValueComboDetalle(String id, int combo, String lang){
		Connection conn = null;
		ObjComboSelect2ValueInt combo3 = new ObjComboSelect2ValueInt();
		
		String query="SELECT id_detalle "
					 +"     ,id_combo "
					 +"     ,valor_detalle "
					 +"     ,des_detalle "
					 +"     ,id_lang "
					 +"     ,depende_detalle "
					 +" FROM man_combo_box_detalle "
					 +" WHERE id_lang = '"+lang+"' "
					 +"	AND combo_depende =  " + combo +" "
					 +" AND depende_detalle =  '" + id +"' ";
					
					query= query +" ORDER BY valor_detalle";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  
			 if(rs.next()) {
				  combo3.setId(rs.getInt("valor_detalle"));
				  combo3.setText(rs.getString("des_detalle"));
				 
			  }
			  return combo3;
			  
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
