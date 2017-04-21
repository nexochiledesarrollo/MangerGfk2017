package cl.nexo.manager.imp.producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.producto.AccessProducto;
import cl.nexo.manager.obj.proyecto.ObjEstudioProducto;
import cl.nexo.manager.obj.tools.ObjComboSelect2GroupValueInt;


public class Producto implements AccessProducto {
	
	private static final Logger logger = Logger.getLogger(Producto.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@Override
	public ArrayList<ObjEstudioProducto> getArrayProducto(String value, String uiid,int idOp){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessGeneralTools generals = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		ArrayList<ObjEstudioProducto> prods = new ArrayList<ObjEstudioProducto>();
		
		int existMoreProduct = generals.existCharOfString(value, ",");
		logger.debug("String: "+value+ " -- Existe: "+ existMoreProduct);
		
		if(existMoreProduct == 1){
			 String[] resValue = value.split(",");
			 for (int i = 0; i < resValue.length; i++) {
				 ObjEstudioProducto prod1 = new ObjEstudioProducto();
				 prod1.setId_operacion(idOp);
				 prod1.setId_producto(Integer.parseInt((String)resValue[i]));
				 prod1.setUiid(uiid);
				 prods.add(prod1);
				 logger.debug("Producto Insertado : "+(String)resValue[i]);
			}
		}else{
			ObjEstudioProducto prod0 = new ObjEstudioProducto();
			prod0.setId_operacion(idOp);
			prod0.setId_producto(Integer.parseInt(value));
			prod0.setUiid(uiid);
			prods.add(prod0);
			logger.debug("Producto Insertado : "+value);
		}
		
		
		
		return prods;
	}
	@Override
	public ArrayList<ObjEstudioProducto> getProductoEstudioById(int id){
		Connection conn = null;
		
		ArrayList<ObjEstudioProducto> combos = new ArrayList<ObjEstudioProducto>();
		String query = "SELECT id_operacion "
					+"	      ,id_producto "
					+"	  FROM man_proyecto_manager_producto "
					+" 	WHERE  "
					+" 		id_operacion ="+ id +" "
					+" 	ORDER BY id_producto ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjEstudioProducto combo = new ObjEstudioProducto();
				  combo.setId_operacion(rs.getInt("id_operacion"));
				  combo.setId_producto(rs.getInt("id_producto"));
				  
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
	public ArrayList<ObjComboSelect2GroupValueInt> getProducto(String lang){
		Connection conn = null;
		
		ArrayList<ObjComboSelect2GroupValueInt> combos = new ArrayList<ObjComboSelect2GroupValueInt>();
		String query = "SELECT id_prod "
					  +"       ,sap_prod "
					  +" 	      ,des_prod "
					  +" 		  ,id_lang "
					  +" 	FROM man_producto "
					  +" 	WHERE  "
					  +" 		id_lang ='"+ lang +"' "
					  +" 	ORDER BY des_prod ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelect2GroupValueInt combo = new ObjComboSelect2GroupValueInt();
				  combo.setId(rs.getInt("id_prod"));
				  combo.setText(rs.getString("des_prod"));
				  if(this.getExistProductoSub(rs.getInt("id_prod"), lang)==1){
					  	combo.setChildren(this.getProductoSub(rs.getInt("id_prod"), lang));
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
	public ArrayList<ObjComboSelect2GroupValueInt> getProductoSub(int id, String lang){
		Connection conn = null;
		
		ArrayList<ObjComboSelect2GroupValueInt> combos = new ArrayList<ObjComboSelect2GroupValueInt>();
		String query = "SELECT id_prod_sub "
					  +"      ,id_prod "
					  +"	      ,sap_prod_sub "
					  +"	      ,des_prod_sub "
					  +"	      ,id_lang "
					  +"	FROM man_producto_sub "
					  +"	WHERE  "
					  +"		id_prod ="+ id +" "
					  +"	AND id_lang ='"+ lang +"' "
					  +"	ORDER BY des_prod_sub "; 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelect2GroupValueInt combo = new ObjComboSelect2GroupValueInt();
				  combo.setId(rs.getInt("id_prod_sub"));
				  combo.setText(rs.getString("des_prod_sub"));
				  if(this.getExistProductoCategoria(rs.getInt("id_prod_sub"), lang)==1){
					  	combo.setChildren(this.getProductoCategoria(rs.getInt("id_prod_sub"), lang));
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
	public ArrayList<ObjComboSelect2GroupValueInt> getProductoCategoria(int id, String lang){
		Connection conn = null;
		
		ArrayList<ObjComboSelect2GroupValueInt> combos = new ArrayList<ObjComboSelect2GroupValueInt>();
		String query = "SELECT id_prod_categoria "
					  +"      ,id_prod_sub "
					  +"      ,sap_prod_sub "
					  +"      ,des_prod_categoria "
					  +"      ,id_lang "
					  +"  FROM man_producto_categoria"
					  +" WHERE id_prod_sub =  "+ id +" "
					  +" AND id_lang = '"+ lang +"' "
					  +" ORDER BY des_prod_categoria";
				
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelect2GroupValueInt combo = new ObjComboSelect2GroupValueInt();
				  combo.setId(rs.getInt("id_prod_categoria"));
				  combo.setText(rs.getString("des_prod_categoria"));
				  if(this.getExistProductoCategoriaSub(rs.getInt("id_prod_categoria"), lang)==1){
					  	combo.setChildren(this.getProductoCategoriaSub(rs.getInt("id_prod_categoria"), lang));
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
	public ArrayList<ObjComboSelect2GroupValueInt> getProductoCategoriaSub(int id, String lang){
		Connection conn = null;
		
		ArrayList<ObjComboSelect2GroupValueInt> combos = new ArrayList<ObjComboSelect2GroupValueInt>();
		String query = " SELECT id_prod_cat_sub "
					  +"      ,id_prod_categoria "
					  +"	      ,sap_prod_cat_sub "
					  +"	      ,des_prod_cat_sub "
					  +"		  ,id_lang "
					  +"	FROM man_producto_categoria_sub  "
					  +"	WHERE  "
					  +"		id_lang = '"+ lang +"' "
					  +"		AND id_prod_categoria = "+ id +" "
					  +"	ORDER BY des_prod_cat_sub ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelect2GroupValueInt combo = new ObjComboSelect2GroupValueInt();
				  combo.setId(rs.getInt("id_prod_cat_sub"));
				  combo.setText(rs.getString("des_prod_cat_sub"));
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
	public int getExistProductoSub(int id, String lang){
		int result = 0;
		Connection conn = null;
		
		String query = "SELECT id_prod_sub "
				  +"      ,id_prod "
				  +"	      ,sap_prod_sub "
				  +"	      ,des_prod_sub "
				  +"	      ,id_lang "
				  +"	FROM man_producto_sub "
				  +"	WHERE  "
				  +"		id_prod ="+ id +" "
				  +"	AND id_lang ='"+ lang +"' "
				  +"	ORDER BY des_prod_sub "; 
				  
		
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
	public int getExistProductoCategoria(int id, String lang){
		int result = 0;
		Connection conn = null;
		
		String query = "SELECT id_prod_categoria "
				  +"      ,id_prod_sub "
				  +"      ,sap_prod_sub "
				  +"      ,des_prod_categoria "
				  +"      ,id_lang "
				  +"  FROM man_producto_categoria"
				  +" WHERE id_prod_sub =  "+ id +" "
				  +" AND id_lang = '"+ lang +"' "
				  +" ORDER BY des_prod_categoria";
				  
		
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
	public int getExistProductoCategoriaSub(int id, String lang){
		int result = 0;
		Connection conn = null;
		
		String query = " SELECT id_prod_cat_sub "
				  +"      ,id_prod_categoria "
				  +"	      ,sap_prod_cat_sub "
				  +"	      ,des_prod_cat_sub "
				  +"		  ,id_lang "
				  +"	FROM man_producto_categoria_sub  "
				  +"	WHERE  "
				  +"		id_lang = '"+ lang +"' "
				  +"		AND id_prod_categoria = "+ id +" "
				  +"	ORDER BY des_prod_cat_sub ";
				  
		
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
	public int setProductoOperacion(ArrayList<ObjEstudioProducto> prods, int id){
		logger.debug("setProductoOperacion size: " + prods.size() );
		
		for(ObjEstudioProducto p : prods ){
			
			int result = this.setProductoOperacion2(p, id);
			logger.debug("Se inserta producto: "+ p.getId_producto() + " -- En operacion id: "+id + " -- Resultado Insert: " + result);
		}
		
		return 1;
	}
	
	@Override
	public int setProductoOperacion2(ObjEstudioProducto prod, int id){
		
		Connection conn = null;
		String query = "INSERT INTO man_proyecto_manager_producto "
					   +"        (id_operacion "
					   +"        ,id_producto) "
					   +"  VALUES "
					   +"        ("+id+" "
					   +"        ,"+prod.getId_producto()+" )";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			return 1;
			
    	} catch (SQLException e) {
    		//return 2;
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
