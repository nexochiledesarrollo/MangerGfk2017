package cl.nexo.manager.imp.proyecto;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.producto.AccessProducto;
import cl.nexo.manager.access.proyecto.AccessCotizacion;
import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudioProducto;
import cl.nexo.manager.obj.proyecto.ObjResultCreaCotOp;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;


public class Cotizacion implements AccessCotizacion {
	
	private static final Logger logger = Logger.getLogger(Cotizacion.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@Override
	public ArrayList<ObjComboSelect2ValueInt> getCombo(int id,String place){
		Connection conn = null;
		ArrayList<ObjComboSelect2ValueInt> combos = new ArrayList<ObjComboSelect2ValueInt>();
		
		String query="SELECT o.id_operacion "
					+"		  ,c.id_cotizacion	"
					+"		  ,c.codigo_cotizacion	"
					+"	      ,o.cod_cotizacion "
					+"	      ,o.nombre_operacion "
					+"	FROM man_proyecto_manager_medicion o"
					+" INNER JOIN man_proyecto_manager_cotizacion c ON o.id_cotizacion = c.id_cotizacion ";
					if(id != 0){ 
						query= query +"	WHERE o.id_cliente =   " + id +" ";
					}
					query= query +" ORDER BY o.id_operacion";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			  ObjComboSelect2ValueInt combo2 = new ObjComboSelect2ValueInt();
			  combo2.setId(0);
			  combo2.setText(place);
			  combos.add(combo2);
			 while(rs.next()) {
				  ObjComboSelect2ValueInt combo = new ObjComboSelect2ValueInt();
				  combo.setId(rs.getInt("id_cotizacion"));
				  combo.setText(rs.getString("id_operacion")+"-"+rs.getString("cod_cotizacion")+" --> "+rs.getString("nombre_operacion"));
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
	public int getIdCotizacionByUid(String uuid){
		int id = 0;
		Connection conn = null;
		
		String query = " SELECT id_cotizacion "
				  +"	 FROM man_proyecto_manager_cotizacion "
				  +"	 WHERE "
				  +" codigo_cotizacion = '" + uuid +"' ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  id = rs.getInt("id_cotizacion");
			  }
			  
			  return id;
			  
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
	public int getCodCotizacionByUid(int id){
		int id2 = 0;
		
		Connection conn = null;
		
		String query = " SELECT ISNULL(MAX(cod_cotizacion), 0)as result   "
					   +"	FROM man_proyecto_manager_medicion m "
					   +"	INNER JOIN man_proyecto_manager_cotizacion c ON m.id_cotizacion = c.id_cotizacion "
					   +"	WHERE c.id_cotizacion =" + id;
		
		try {
			  String aux = "";
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();	
			  if (rs.next()) {  
				  aux = rs.getString("result");
				  logger.debug("Result: "+ aux);
				  if(aux != null || aux.isEmpty()){
					  aux = "0";
					}
				  id2 = Integer.parseInt(aux) + 1;
				  logger.debug("Codigo Operación: "+ id2);
			  }
			  
			  return id2;
			  
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
	public int getCountCotizacion(){
		int id2 = 0;
		
		Connection conn = null;
		
		String query = " SELECT COUNT(cod_cotizacion)as result   "
					   +"	FROM man_proyecto_manager_medicion  "
					   +"	WHERE elimina_medicion = 0" ;
		
		try {
			  String aux = "";
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();	
			  if (rs.next()) {  
				  id2 = rs.getInt("result");
				  logger.debug("Count: "+ id2);
			  }
			  
			  return id2;
			  
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
	public int setNewCotizacion(String uuid, String nombre){
		
		Connection conn = null;
		String query="INSERT INTO man_proyecto_manager_cotizacion "
				     +"      (codigo_cotizacion "
				     +"      ,nombre_cotizacion)  "
				     +" VALUES  "
				     +"      ('"+uuid+"'  "
				     +"      ,'"+nombre+"')";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			return this.getIdCotizacionByUid(uuid);
			
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
	
	@Override
	public ObjResultCreaCotOp newCotizacion(ObjEstudio estudio){
		ObjResultCreaCotOp cod = new ObjResultCreaCotOp();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 AccessGeneralTools generals = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 AccessEstudio estudios = (AccessEstudio) context.getBean("AccessEstudio");
		 AccessProducto prods = (AccessProducto) context.getBean("AccessProducto");
		
		 	 
		 	 int id_cotizacion = 0;
			 String uuid = generals.getNewUuId();
			 
			 id_cotizacion = estudio.getId_cotizacion();
			 
			 logger.debug("Numero de Cotización2: "+ id_cotizacion);
		 
			 if( id_cotizacion == 0){
				 id_cotizacion = this.setNewCotizacion(uuid, estudio.getNombre_operacion());
				 logger.debug("Se genera cotización: " + id_cotizacion);
			 }else{
				 id_cotizacion = estudio.getId_cotizacion();
				 logger.debug("Se Mantiene cotización: " + id_cotizacion); 
			 }
			 estudio.setUid_operacion(uuid);
			 estudio.setId_cotizacion(id_cotizacion);
		
			 ObjResultCreaCotOp id_operacion = estudios.setOperacion(estudio);
			 
			 estudio.setId_operacion(id_operacion.getId_operacion());
			 
			 ObjResultCreaCotOp id_detalle = estudios.setDetalleOperacion(estudio);
			 
			 
			 
			 int result_prod = prods.setProductoOperacion(estudio.getProductos(), id_operacion.getId_operacion());
			 
			 cod.setId_cotizacion(id_cotizacion);
			 cod.setUuid(uuid);
			 cod.setId_operacion(id_operacion.getId_operacion());
			 cod.setId_detalle_operacion(id_detalle.getId_detalle_operacion());
			 cod.setCodigo_cotizacion(id_cotizacion + "-" + estudio.getCodigo_cotizacion());
		 
			 
		 
		 return cod;
	}
}
