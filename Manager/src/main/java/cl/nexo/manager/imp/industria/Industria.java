package cl.nexo.manager.imp.industria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.objenesis.ObjenesisSerializer;

import cl.nexo.manager.access.industria.AccessIndustria;
import cl.nexo.manager.obj.menu.ObjModuloMenuManager;
import cl.nexo.manager.obj.tools.ObjComboSelect2GroupValueInt;


public class Industria implements AccessIndustria {
	private static final Logger logger = Logger.getLogger(Industria.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@Override
	public ArrayList<ObjComboSelect2GroupValueInt> getIndustria(String lang, String place){
		Connection conn = null;
		
		ArrayList<ObjComboSelect2GroupValueInt> combos = new ArrayList<ObjComboSelect2GroupValueInt>();
		String query = "SELECT id_ind "
					  +"      ,id_sector "
					  +"      ,sap_ind "
					  +"      ,des_ind "
					  +"      ,id_lang "
					  +"  FROM man_industria "
					  +" WHERE  "
					  +" id_lang = '"+ lang +"' "
					  +" ORDER BY des_ind";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  ObjComboSelect2GroupValueInt combo2 = new ObjComboSelect2GroupValueInt();
			  combo2.setId(0);
			  combo2.setText(place); 
			  combos.add(combo2);
			
			 while(rs.next()) {
				  ObjComboSelect2GroupValueInt combo = new ObjComboSelect2GroupValueInt();
				  combo.setId(rs.getInt("id_ind"));
				  combo.setText(rs.getInt("sap_ind") +" :: "+ rs.getString("des_ind"));
				  if(this.getExistIndustriaSub(rs.getInt("id_ind"), lang) == 1){
				  
					  combo.setChildren(this.getIndustriaSub(rs.getInt("id_ind"), lang));
				  }
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
	public ArrayList<ObjComboSelect2GroupValueInt> getIndustriaSub(int ind, String lang){
		Connection conn = null;
		
		ArrayList<ObjComboSelect2GroupValueInt> combos = new ArrayList<ObjComboSelect2GroupValueInt>();
		String query = "SELECT id_ind_sub "
					  +"     ,id_ind "
					  +"     ,sap_ind_sub "
					  +"     ,des_ind_sub "
					  +" 	  ,id_lang "
					  +" FROM man_industria_sub "
					  +" WHERE id_ind =  "+ ind +" "
					  +" AND id_lang = '"+ lang +"' "
					  +" ORDER BY des_ind_sub";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelect2GroupValueInt combo = new ObjComboSelect2GroupValueInt();
				  combo.setId(rs.getInt("id_ind_sub"));
				  combo.setText(rs.getInt("sap_ind_sub") +" :: "+rs.getString("des_ind_sub"));
				  if(this.getExistIndustriaCategoria(rs.getInt("id_ind_sub"), lang)==1){
					  combo.setChildren(this.getIndustriaCategoria(rs.getInt("id_ind_sub"), lang));
				  }
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
	public ArrayList<ObjComboSelect2GroupValueInt> getIndustriaCategoria(int sub, String lang){
		Connection conn = null;
		
		ArrayList<ObjComboSelect2GroupValueInt> combos = new ArrayList<ObjComboSelect2GroupValueInt>();
		String query = "SELECT id_ind_categoria "
					   +"   ,id_ind_sub "
					   +"   ,sap_ind_categoria "
					   +"   ,des_ind_categoria "
					   +"   ,id_lang "
					  +" FROM man_industria_categoria "
					  +" WHERE id_ind_sub =  "+ sub +" "
					  +" AND id_lang = '"+ lang +"' "
					  +" ORDER BY des_ind_categoria";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelect2GroupValueInt combo = new ObjComboSelect2GroupValueInt();
				  combo.setId(rs.getInt("id_ind_categoria"));
				  combo.setText(rs.getInt("sap_ind_categoria") +" :: "+rs.getString("des_ind_categoria"));
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
	public int getExistIndustriaSub(int id, String lang){
		int result = 0;
		Connection conn = null;
		
		String query = "SELECT id_ind_sub "
				  +"     ,id_ind "
				  +"     ,sap_ind_sub "
				  +"     ,des_ind_sub "
				  +" 	  ,id_lang "
				  +" FROM man_industria_sub "
				  +" WHERE id_ind =  "+ id +" "
				  +" AND id_lang = '"+ lang +"' "
				  +" ORDER BY des_ind_sub";
				  
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {

				result = 1;
				
			}else{
				
				result = 0;
			}
			
			return result;
			
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
	public int getExistIndustriaCategoria(int id, String lang){
		int result = 0;
		Connection conn = null;
		
		String query = "SELECT id_ind_categoria "
				   +"   ,id_ind_sub "
				   +"   ,sap_ind_categoria "
				   +"   ,des_ind_categoria "
				   +"   ,id_lang "
				  +" FROM man_industria_categoria "
				  +" WHERE id_ind_sub =  "+ id +" "
				  +" AND id_lang = '"+ lang +"' "
				  +" ORDER BY des_ind_categoria";
				  
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {

				result = 1;
				
			}else{
				
				result = 0;
			}
			
			return result;
			
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
	public String getNombreSubIndustriaById(int id, String lang){
		String result = "";
		Connection conn = null;
		

					  
		String query = "SELECT id_ind_sub "
				  +"     ,id_ind "
				  +"     ,sap_ind_sub "
				  +"     ,des_ind_sub "
				  +" 	  ,id_lang "
				  +" FROM man_industria_sub "
				  +" WHERE id_ind_sub =  "+ id +" "
				  +" AND id_lang = '"+ lang +"' "
				  +" ORDER BY des_ind_sub";		  
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {

				result = rs.getString("des_ind_sub");
				
			}else{
				if(result != ""){
				result = this.getNombreSubIndustriaById(id, lang);
				}
					
			}
			
			return result;
			
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
	public String getNombreCategoriaIndustriaById(int id, String lang){
		String result = "";
		Connection conn = null;
		
		String query = "SELECT id_ind_categoria "
				   +"   ,id_ind_sub "
				   +"   ,sap_ind_categoria "
				   +"   ,des_ind_categoria "
				   +"   ,id_lang "
				  +" FROM man_industria_categoria "
				  +" WHERE id_ind_categoria =  "+ id +" "
				  +" AND id_lang = '"+ lang +"' "
				  +" ORDER BY des_ind_categoria";
				  
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {

				result = rs.getString("des_ind_categoria");
				
			}else{
				
				result = "";
					
			}
			
			return result;
			
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
	public String getNombreIndustriaById(int id, String lang){
		String result = "";
		Connection conn = null;
		
//		String query = "SELECT id_ind_categoria "
//				   +"   ,id_ind_sub "
//				   +"   ,sap_ind_categoria "
//				   +"   ,des_ind_categoria "
//				   +"   ,id_lang "
//				  +" FROM man_industria_categoria "
//				  +" WHERE id_ind_sub =  "+ id +" "
//				  +" AND id_lang = '"+ lang +"' "
//				  +" ORDER BY des_ind_categoria";
		
		String query = "SELECT id_ind "
					  +"      ,id_sector "
					  +"      ,sap_ind "
					  +"      ,des_ind "
					  +"      ,id_lang "
					  +"  FROM man_industria "
					  +" WHERE  "
					  +" id_lang = '"+ lang +"' "
					  +" AND id_ind = '"+ id +"' ";
				  
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {

				result = rs.getString("des_ind");
				
			}else{
				
				result = this.getNombreSubIndustriaById(id, lang);
				if(result == ""){
					result = this.getNombreCategoriaIndustriaById(id, lang);
				}
			}
			
			return result;
			
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
