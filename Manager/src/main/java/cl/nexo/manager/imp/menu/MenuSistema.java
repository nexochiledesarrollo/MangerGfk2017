package cl.nexo.manager.imp.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import cl.nexo.manager.access.menu.AccessMenu;
import cl.nexo.manager.obj.menu.ObjMenuManager;
import cl.nexo.manager.obj.menu.ObjModuloMenuManager;
import cl.nexo.manager.obj.menu.ObjUnidadMenuManager;


public class MenuSistema implements AccessMenu {
	private static final Logger logger = Logger.getLogger(MenuSistema.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public ObjMenuManager getMenuSistema(int tipo, int cliente, int perfil){
		Connection conn = null;
		
		ObjMenuManager menu = new ObjMenuManager();
		
		String query = " SELECT id_menu "
				  +"	      ,id_cliente "
				  +"	      ,tipo_menu "
				  +"	      ,nombre_menu "
				  +"	FROM man_menu_sistema "
				  +"	WHERE  "
				  +"	tipo_menu = 1 "
				  +"	AND id_cliente = "+ cliente;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			  if(rs.next()) {
				menu.setId_menu(rs.getInt("id_menu"));
				menu.setId_cliente(rs.getInt("id_cliente"));
				menu.setTipo_menu(rs.getInt("tipo_menu"));
				menu.setNombre_menu(rs.getString("nombre_menu"));
				menu.setUnidades(this.getUnidades(rs.getInt("id_menu"), perfil));
			  }
			  
			  return menu;
			  
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
	public ArrayList<ObjUnidadMenuManager> getUnidades(int menu, int perfil){
		Connection conn = null;
		
		ArrayList<ObjUnidadMenuManager> unidades = new ArrayList<ObjUnidadMenuManager>();
		
		String query = "SELECT " 
				  +"	DISTINCT(u.id_unidad) "
				  +"		,u.id_menu "
				  +"		,u.nombre_unidad "
				  +"		,u.icono_unidad "
				  +"		,u.orden_unidad "
				  +"		,u.estado_unidad "
				  +"		,u.box_unidad "
				  +"	FROM man_menu_unidad u "
				  +"	INNER JOIN man_menu_modulo m ON u.id_unidad = m.id_unidad "
				  +"	INNER JOIN man_nav_perfil_modulo nv ON m.id_modulo = nv.id_modulo "
				  +"	INNER JOIN man_perfil_login p ON p.id_perfil = nv.id_perfil "
				  +"	WHERE "
				  +"	u.id_menu = "+menu+" "
				  +"	AND p.id_perfil ="+perfil+" "
				  +"	AND u.estado_unidad = 1 "
				  +"	ORDER BY u.orden_unidad";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjUnidadMenuManager uni = new ObjUnidadMenuManager();
				  uni.setId_unidad(rs.getInt("id_unidad"));
				  uni.setId_menu(rs.getInt("id_menu"));
				  uni.setNombre_unidad(rs.getString("nombre_unidad"));
				  uni.setIcono_unidad(rs.getString("icono_unidad"));
				  uni.setOrden_unidad(rs.getInt("orden_unidad"));
				  uni.setEstado_unidad(rs.getInt("estado_unidad"));
				  uni.setBox_unidad(rs.getInt("box_unidad"));
				  uni.setModulos(this.getModulos(rs.getInt("id_unidad"), perfil));
				  unidades.add(uni);
			  }
			  
			  return unidades;
			  
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
	public ArrayList<ObjModuloMenuManager> getModulos(int unidad, int perfil){
		Connection conn = null;
		
		ArrayList<ObjModuloMenuManager> modulos = new ArrayList<ObjModuloMenuManager>();
		String query = "SELECT m.id_modulo "
				  +"	      ,m.id_unidad "
				  +"	      ,m.nombre_modulo "
				  +"	      ,m.icono_modulo "
				  +"	      ,m.accion_modulo "
				  +"	      ,m.orden_modulo "
				  +"	      ,m.estado_modulo "
				  +"	      ,m.codigo_modulo "
				  +"		  ,p.id_perfil "
				  +"		  ,p.nombre_perfil "
				  +"		  ,nv.id_permiso "
				  +"	FROM man_menu_modulo m "
				  +"	INNER JOIN man_nav_perfil_modulo nv ON m.id_modulo = nv.id_modulo "
				  +"	INNER JOIN man_perfil_login p ON p.id_perfil = nv.id_perfil "
				  +"	WHERE "
				  +"	m.id_unidad = "+unidad+" "
				  +"	AND p.id_perfil ="+perfil+" "
				  +"	AND m.menu_modulo = 1 "
				  +"	AND m.estado_modulo = 1 "
				  + "   ORDER BY m.orden_modulo";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjModuloMenuManager mod = new ObjModuloMenuManager();
				  mod.setId_modulo(rs.getInt("id_modulo"));
				  mod.setId_unidad(rs.getInt("id_unidad"));
				  mod.setNombre_modulo(rs.getString("nombre_modulo"));
				  mod.setIcono_modulo(rs.getString("icono_modulo"));
				  mod.setAccion_modulo(rs.getString("accion_modulo"));
				  mod.setOrden_modulo(rs.getInt("orden_modulo"));
				  mod.setEstado_modulo(rs.getInt("estado_modulo"));
				  mod.setCodigo_modulo(rs.getInt("codigo_modulo"));
				  
				  modulos.add(mod);
			  }
			  
			  return modulos;
			  
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
